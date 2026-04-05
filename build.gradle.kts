plugins {
    java
    id("org.springframework.boot") version "4.0.5"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.doodle"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(26)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc:4.0.5")
    implementation("org.springframework.boot:spring-boot-starter-validation:4.0.5")
    implementation("org.springframework.boot:spring-boot-starter-webmvc:4.0.5")
    compileOnly("org.projectlombok:lombok:1.18.44")
    runtimeOnly("org.postgresql:postgresql:42.7.10")
    annotationProcessor("org.projectlombok:lombok:1.18.44")
    testImplementation("org.springframework.boot:spring-boot-starter-test:4.0.5")
    testImplementation("org.springframework.boot:spring-boot-testcontainers:4.0.5")
    testImplementation("org.testcontainers:junit-jupiter:1.21.0")
    testImplementation("org.testcontainers:postgresql:1.21.0")
    testCompileOnly("org.projectlombok:lombok:1.18.44")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:6.0.3")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.44")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.8")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test:4.0.5")
    testImplementation("org.springframework.boot:spring-boot-test:4.0.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test:4.0.5")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.6")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
