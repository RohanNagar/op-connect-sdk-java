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
  <version>0.2.0</version>
</dependency>
```

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

### Add an item

```java
Item itemToCreate = Item.builder()
    .withTitle("My Login Item")
    .withCategory(Category.LOGIN)
    .withVaultId("VAULTID")
    .withField(Field.username("myemail@test.com").build())
    .withField(Field.password("testpassword").build())
    .build();
    
Item createdItem = client.createItem("VAULTID", itemToCreate).join();
```

```java
Item itemToCreate = Item.builder()
    .withTitle("My Login Item")
    .withCategory(Category.LOGIN)
    .withVaultId("VAULTID")
    .withField(Field.username("myemail@test.com").build())
    .withField(Field.generatedPassword().build())
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
