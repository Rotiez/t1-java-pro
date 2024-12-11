plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.zaxxer:HikariCP")
    implementation("org.postgresql:postgresql")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}
