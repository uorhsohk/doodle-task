package com.doodle.task.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Meeting {

    private Long id;
    private Long slotId;
    private String title;
    private String description;
    private List<MeetingParticipant> participants = new ArrayList<>();

    public Meeting(Long id, Long slotId, String title, String description, List<MeetingParticipant> participants) {
        this.id = id;
        this.slotId = slotId;
        this.title = title;
        this.description = description;
        this.participants = participants != null ? participants : new ArrayList<>();
    }
}
