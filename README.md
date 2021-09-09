# 1Password Connect Java SDK

<a href="https://search.maven.org/artifact/com.sanctionco.opconnect/opconnect-java">
  <img src="https://img.shields.io/maven-central/v/com.sanctionco.opconnect/opconnect-java.svg?colorB=brightgreen&label=maven%20central" alt="Maven Central">
</a>
<a href="http://javadoc.io/doc/com.sanctionco.opconnect/opconnect-java">
  <img src="http://javadoc.io/badge/com.sanctionco.opconnect/opconnect-java.svg" alt="Javadoc">
</a>

An unofficial SDK for interacting with the
[1Password Connect API](https://support.1password.com/connect-api-reference).
Tested with the latest version of the 1Password Connect Server (v1.3.1).

**NOTE: Until version 1.0.0, any new release may contain breaking changes!**

Still a work in progress, feel free to submit pull requests or issues if you encounter
bugs or need additional enhancements.

## Installation

Add this library as a dependency in your `pom.xml`:

```xml
<dependency>
  <groupId>com.sanctionco.opconnect</groupId>
  <artifactId>opconnect-java</artifactId>
  <version>0.4.0</version>
</dependency>
```

## Setup

Before you can use the 1Password Connect API, make sure that you follow
step 1 and 2 of the
[1Password Secrets Automation Setup Guide](https://support.1password.com/secrets-automation/)
in order to set up a local 1Password Connect server with Docker or Kubernetes.

## Usage

Create a client:

```java
OPConnectClient client = OPConnectClient.builder()
    .withEndpoint("http://localhost:8080")
    .withAccessToken("OP_ACCESS_TOKEN")
    .build();
```

Below are examples of how to call each of the API methods,
in both blocking and non-blocking form.

### List vaults

```java
List<Vault> vaults = client.listVaults().join();
```

```java
client.listVaults().whenComplete((vaults, throwable) -> {
  // vaults variable contains the list of vaults
});
```

#### With a Filter

```java
List<Vault> vaults = client
    .listVaults(Filter.name().contains("My Vault"))
    .join();
```

```java
client.listVaults(Filter.name().contains("My Vault"))
    .whenComplete((vaults, throwable) -> {
      // vaults variable contains the list of vaults
    });
```

### Get vault details

```java
Vault vault = client.getVault("VAULTID").join();
```

```java
client.getVault("VAULTID").whenComplete((vault, throwable) -> {
  // vault variable contains the vault
});
```

### List items

```java
List<Item> items = client.listItems("VAULTID").join();
```

```java
client.listItems("VAULTID").whenComplete((items, throwable) -> {
  // items variable contains the list of items
});
```

#### With a Filter

```java
Filter filter = Filter.title().contains("Login");

List<Item> loginItems = client.listItems("VAULTID", filter).join();
```

```java
Filter filter = Filter.title()
    .contains("Test")
    .or(Filter.title().equals("Test2"))

client.listItems("VAULTID", filter).whenComplete((testItems, throwable) -> {
  // testItems variable contains the list of items
});
```

### Add an item

```java
Item itemToCreate = Item.builder()
    .withTitle("My Login Item")
    .withCategory(Category.LOGIN)
    .withVaultId("VAULTID")
    .withField(Field.username("myemail@test.com").build())
    .withField(Field.generatedPassword(
        GeneratorRecipe.letters().ofLength(30)).build())
    .withUrl(URL.primary("https://www.test.com"))
    .build();
    
Item createdItem = client.createItem("VAULTID", itemToCreate).join();
```

```java
Item itemToCreate = Item.builder()
    .withTitle("My Login Item")
    .withCategory(Category.LOGIN)
    .withVaultId("VAULTID")
    .withField(Field.username("myemail@test.com").build())
    .withField(Field.generatedPassword(
        GeneratorRecipe.letters().ofLength(30)).build())
    .withUrl(URL.primary("https://www.test.com"))
    .build();
    
client.createItem("VAULTID", itemToCreate).whenComplete((item, throwable) -> {
  // item variable contains the created item
});
```

### Get item details

```java
Item item = client.getItem("VAULTID", "ITEMID").join();
```

```java
client.getItem("VAULTID", "ITEMID").whenComplete((item, throwable) -> {
  // item variable contains the item
});
```

### Replace an item

```java
Item existing = client.getItem("VAULTID", "ITEMID").join();
    
Item replacement = Item.builder()
    .fromItem(existing)
    .withTitle("New title")
    .build();

Item replaced = client.replaceItem("VAULTID", "ITEMID", replacement).join();
```

```java
client.getItem("VAULTID", "ITEMID").thenCompose(item -> {
  Item replacement = Item.builder()
    .fromItem(item)
    .withTitle("New title")
    .build();
  
  return client.replaceItem("VAULTID", "ITEMID", replacement);
}).whenComplete((item, throwable) -> {
    // item variable contains the replaced item
});
```

### Move an item to the trash

```java
client.deleteItem("VAULTID", "ITEMID").join();
```

```java
client.deleteItem("VAULTID", "ITEMID").whenComplete((unused, throwable) -> {
  // delete does not return the item
});
```

### Change item details

```java
Item patched = client
    .patchItem("VAULTID", "ITEMID", Patch.remove().withPath("/title").build())
    .join();
```

```java
client.patchItem("VAULTID", "ITEMID", Patch.remove().withPath("/title").build())
    .whenComplete((item, throwable) -> {
      // item variable contains the patched item
    });
```

### List Files

```java
List<File> files = client
    .listFiles("VAULTID", "ITEMID")
    .join();
```

```java
client.listFiles("VAULTID", "ITEMID")
    .whenComplete((files, throwable) -> {
      // files variable contains the list of files
    });
```

#### With Inline Content

```java
List<File> files = client
    .listFiles("VAULTID", "ITEMID", true)
    .join();
```

```java
client.listFiles("VAULTID", "ITEMID", true)
    .whenComplete((files, throwable) -> {
      // files variable contains the list of files
    });
```

### Get a File

```java
File file = client.getFile("VAULTID", "ITEMID", "FILEID").join();
```

```java
client.getFile("VAULTID", "ITEMID", "FILEID")
    .whenComplete((file, throwable) -> {
      // file variable contains the file details
    });
```

#### With Inline Content

```java
File file = client.getFile("VAULTID", "ITEMID", "FILEID", true).join();
```

```java
client.getFile("VAULTID", "ITEMID", "FILEID", true)
    .whenComplete((file, throwable) -> {
      // file variable contains the file details
    });
```

### Get the Content of a File

```java
String content = client
    .getFileContent("VAULTID", "ITEMID", "FILEID")
    .join();
```

```java
client.getFileContent("VAULTID", "ITEMID", "FILEID")
    .whenComplete((content, throwable) -> {
      // content variable contains the file content
    });
```

### List API activity

```java
List<APIRequest> requests = client.listAPIActivity().join();

// Limit to 5 requests
List<APIRequest> limitedRequests = client.listAPIActivity(5).join();

// Get 10 requests, starting at index 2
List<APIRequest> limitedAndOffsetRequests = client.listAPIActivity(10, 2).join();
```

```java
client.listAPIActivity().whenComplete((requests, throwable) -> {
  // requests variable contains the list of requests
});
```

### Get Health Details

```java
ConnectServer serverHealth = client.health().join();
```

```java
client.health().whenComplete((serverHealth, throwable) -> {
  // serverHealth variable contains the server health details
});
```

### Heartbeat

```java
client.heartbeat().join(); // throws exception if heartbeat fails
```

```java
client.heartbeat().exceptionally(throwable -> {
  // execution reaches here if heartbeat failed
});
```

### Get Connect Server Metrics

```java
String metrics = client.metrics().join();
```

```java
client.metrics().whenComplete((metrics, throwable) -> {
  // metrics variable contains the metrics in text form
});
```
