plugins {
    kotlin("multiplatform")
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("plugin.jpa") version "1.9.25"
}

kotlin {
    jvm()
    sourceSets {
        jvmMain.dependencies {
            implementation("org.springframework.boot:spring-boot-starter-data-jpa")
            implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
            // implementation("org.springframework.boot:spring-boot-starter-security")
            implementation("org.springframework.boot:spring-boot-starter-validation")
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
            implementation("org.jetbrains.kotlin:kotlin-reflect")
            implementation("io.jsonwebtoken:jjwt-api:0.11.5")
            implementation("com.h2database:h2:2.3.232")
            runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
            runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
        }

        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
