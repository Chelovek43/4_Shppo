plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    val springVersion = "6.2.7"

    // Spring Core и Context
    implementation("org.springframework:spring-context:6.1.5")
    implementation("org.springframework:spring-aop:6.1.5")
    implementation("org.aspectj:aspectjweaver:1.9.20")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

