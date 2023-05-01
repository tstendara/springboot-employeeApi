

plugins {
	java
	id("org.springframework.boot") version "3.1.0-M2"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com."
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

tasks.named<Jar>("jar") {
	manifest {
		attributes["Main-Class"] = "com.EmployeeApi.EmployeeAPIApplication"
	}
	archiveFileName.set("EmployeeAPI.jar")
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("junit:junit:4.13.1")
	testImplementation("junit:junit:4.13.1")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
