plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.liquibase:liquibase-core")
    implementation("com.zaxxer:HikariCP")
    implementation("org.postgresql:postgresql")
}
