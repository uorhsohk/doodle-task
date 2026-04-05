package com.doodle.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureRestTestClient
class MeetingSchedulingJourneyTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:18.3-alpine");

    @Autowired
    RestTestClient restTestClient;

    @Test
    @SuppressWarnings("unchecked")
    void aliceSchedulesMeetingWithBob() {
        long aliceId = idOf(post("/api/users", Map.of("email", "alice@example.com", "name", "Alice")));
        long bobId   = idOf(post("/api/users", Map.of("email", "bob@example.com",   "name", "Bob")));

        var slot = post("/api/slots", Map.of(
                "userId", aliceId,
                "startTime", "2026-04-10T09:00:00",
                "endTime", "2026-04-10T10:00:00"));
        assertThat(slot.get("status")).isEqualTo("FREE");

        long meetingId = idOf(post("/api/meetings", Map.of(
                "slotId", idOf(slot),
                "title", "sample title",
                "description", "sample description",
                "participantUserIds", List.of(bobId))));

        var busySlots = restTestClient.get()
                .uri("/api/slots/user/" + aliceId + "?status=BUSY")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class).returnResult().getResponseBody();
        assertThat(busySlots).hasSize(1);

        restTestClient.patch()
                .uri("/api/meetings/" + meetingId + "/participants")
                .body(Map.of("userId", bobId, "status", "ACCEPTED"))
                .exchange()
                .expectStatus().isNoContent();

        var participants = (List<Map<String, Object>>) get("/api/meetings/" + meetingId).get("participants");
        assertThat(participants).hasSize(1);
        assertThat(idOf(participants.getFirst())).isEqualTo(bobId);
        assertThat(participants.getFirst().get("status")).isEqualTo("ACCEPTED");
    }

    private Map<String, Object> post(String uri, Map<String, Object> body) {
        return (Map<String, Object>) restTestClient.post()
                .uri(uri)
                .body(body)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Map.class).returnResult().getResponseBody();
    }

    private Map<String, Object> get(String uri) {
        return (Map<String, Object>) restTestClient.get()
                .uri(uri)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Map.class).returnResult().getResponseBody();
    }

    private long idOf(Map<String, Object> body) {
        return ((Number) body.get("id")).longValue();
    }
}
