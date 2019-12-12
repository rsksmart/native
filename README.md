# cloudflare-altbn128-jni

### Usage

Add dependency

#### Maven

```
<dependency>
	<groupId>com.bakaoh</groupId>
	<artifactId>altbn128</artifactId>
	<version>1.0</version>
	<type>pom</type>
</dependency>
```

#### Gradle

```
compile 'com.bakaoh:altbn128:1.0'
```

### Build

```
$ git clone https://github.com/bakaoh/cloudflare-altbn128-jni
$ cd cloudflare-altbn128-jni
$ cd src/jni && make macos
$ cd ../.. && ./gradlew jar
```