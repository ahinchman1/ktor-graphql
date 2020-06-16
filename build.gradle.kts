import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    application
}

repositories {
    mavenCentral()
    jcenter()
}

val ktorVersion = "1.3.2"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.ktor:ktor-server-netty:$ktorVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")

    implementation("org.slf4j:slf4j-simple:1.6.2")
    implementation("io.github.microutils:kotlin-logging:1.7.9")

    implementation("com.graphql-java:graphql-java:13.0")
    implementation("com.github.excitement-engineer:ktor-graphql:1.0.0")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.9.1")
    implementation("com.github.pgutkowski:kgraphql:0.2.5")

    implementation("com.ryanharter.ktor:ktor-moshi:1.0.1")

    testImplementation("junit:junit:4.11")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Jar> {
    manifest {
        attributes("Main-Class" to "MainKt")
    }

    from(configurations.compile.get().map { if (it.isDirectory) it else zipTree(it) })
}

application {
    mainClassName = "com.ray.wenderlich.favrwtutorials.MainKt"
}