rootProject.name = "spring-cloud-project-root-service"

include(":spring-cloud-project-core-service")
include(":spring-cloud-project-config-service")
include(":spring-cloud-project-discovery-service")
include(":spring-cloud-project-store-service")
include(":spring-cloud-project-property")


File(settingsDir, "../spring-cloud-project-core-service").also {
    project(":spring-cloud-project-core-service").projectDir = it
}

File(settingsDir, "../spring-cloud-project-config-service").also {
    project(":spring-cloud-project-config-service").projectDir = it
}

File(settingsDir, "../spring-cloud-project-discovery-service").also {
    project(":spring-cloud-project-discovery-service").projectDir = it
}

File(settingsDir, "../spring-cloud-project-property").also {
    project(":spring-cloud-project-property").projectDir = it
}

File(settingsDir, "../spring-cloud-project-store-service").also {
    project(":spring-cloud-project-store-service").projectDir = it
}

File(settingsDir, "../spring-cloud-project-property").also {
    project(":spring-cloud-project-property").projectDir = it
}
