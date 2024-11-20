plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-context:5.3.29")
    implementation("org.springframework:spring-jdbc:5.3.29")
    implementation("com.zaxxer:HikariCP:5.0.1")
    runtimeOnly("org.postgresql:postgresql:42.6.0")
    implementation("org.liquibase:liquibase-core:4.23.1")
    implementation("org.yaml:snakeyaml:2.0")
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("org.slf4j:slf4j-simple:1.7.32")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}

// Test dependencies
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.testcontainers:testcontainers:1.17.6")
    testImplementation("org.testcontainers:junit-jupiter:1.17.6")
    testImplementation("org.testcontainers:postgresql:1.17.6")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<JavaExec>("run") {
    mainClass.set("edu.t1.pract4.Pract4Application")
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "edu.t1.pract4.Pract4Application"
        )
    }
}
