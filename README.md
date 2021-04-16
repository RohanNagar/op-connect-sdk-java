# 1Password Connect Java SDK

<a href="https://search.maven.org/artifact/com.sanctionco.opconnect/opconnect-java">
  <img src="https://img.shields.io/maven-central/v/com.sanctionco.opconnect/opconnect-java.svg?colorB=brightgreen&label=maven%20central" alt="Maven Central">
</a>
<a href="http://javadoc.io/doc/com.sanctionco.opconnect/opconnect-java">
  <img src="http://javadoc.io/badge/com.sanctionco.opconnect/opconnect-java.svg" alt="Javadoc">
</a>

An unofficial SDK for interacting with the
[1Password Connect API](https://support.1password.com/connect-api-reference).

**NOTE: Until version 1.0.0, any new release may contain breaking changes!**

Still a work in progress, feel free to submit pull requests or issues if you encounter
bugs or need additional enhancements.

## Installation

Add this library as a dependency in your `pom.xml`:

```xml
<dependency>
  <groupId>com.sanctionco.opconnect</groupId>
  <artifactId>opconnect-java</artifactId>
  <version>0.1.0</version>
</dependency>
```

## Usage

Create a client:

```java
OPConnectClient client = OPConnectClientBuilder.builder()
    .withEndpoint("http://localhost:8080")
    .withAccessToken("OP_ACCESS_TOKEN")
    .build();
```

List available vaults (blocking):

```java
Vault vault = client.listVaults().join();
```

List available vaults (asynchronously):

```java
client.listVaults().whenComplete((vaults, throwable) -> {
  // vaults variable contains the result
});
```
