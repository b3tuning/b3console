Import build-tool as a submodule of a project and simplify the build.gradle file:

```
//////////////////////////////////////////////////////////////////////////
// Include a buildscript block at the top of the build.gradle file
buildscript {
	// include the standard repositories:
	repositories {
		mavenCentral()
	// (optional) include classpaths for non-standard plugins that may be needed:
	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:1.2.5.RELEASE")
		classpath("net.saliman:gradle-cobertura-plugin:2.2.8")
	}
}

// Include project specific properties:
ext {
	projectName = 'config-tool-test'
	projectTitle = 'B3Tuning Module V1: Config Tool Test'
	major = 0
	minor = 1
}
// include base.gradle, this has most of the standard gradle configuration in it:
apply from: 'build-tool/base.gradle'

// include plugins as needed, many are configured in build-tool gradle files:
apply from: "${javaPlugin}"
apply from: "${coberturaPlugin}"
apply from: "${sonarPlugin}"
apply plugin: 'spring-boot'

// include necessary dependencies, many can use standard version set from base.gradle
dependencies {
	compile("org.springframework.boot:spring-boot-starter-web:${springBootVersion}")

	testCompile("junit:junit:${junitVersion}")
}
```