subprojects {

    apply {
        plugin("java")
    }

    group = "edu.t1"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}