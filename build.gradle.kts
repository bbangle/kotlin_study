plugins {
    // 1. 인텔리제이 2024.2.6과 가장 호환성이 좋은 코틀린 버전입니다.
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"

    // 2. 2026년 기준 가장 안정적인 스프링 부트 3.x 버전입니다. (Java 17 이상 필수)
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "mvc"

java {
    toolchain {
        // 3. 스프링 부트 3 버전부터는 최소 자바 17이 필요합니다.
        // 설치하신 Corretto-19가 있다면 자동으로 인식하여 실행합니다.
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // 4. 공식 라이브러리 명칭으로 수정하였습니다.
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // 5. 경로 오류(tools...)를 올바른 경로(com.fasterxml...)로 수정하였습니다.
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    // 코틀린 1.9.x 버전에 맞는 컴파일러 설정입니다.
    jvmToolchain(17)
}

tasks.withType<Test> {
    useJUnitPlatform()
}