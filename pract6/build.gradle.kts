plugins {
	java
	id("org.springframework.boot") version "3.4.0" apply false
	id("io.spring.dependency-management") version "1.1.6" apply false
}

allprojects {
	extra["lombokVersion"] = "1.18.30"
	extra["openApiVersion"] = "2.7.0"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "java")

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-web")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
		implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("openApiVersion")}")
		implementation("org.projectlombok:lombok:${property("lombokVersion")}")
		annotationProcessor("org.projectlombok:lombok:${property("lombokVersion")}")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
