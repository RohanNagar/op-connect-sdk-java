# 1Password Connect Java SDK

An unofficial SDK for interacting with the
[1Password Connect API](https://support.1password.com/connect-api-reference).

Still a work in progress, feel free to submit pull requests or issues if you encounter
bugs or need additional enhancements.

Until version 1.0.0, any new release may contain breaking changes.

Maven artifact coming soon.

Create a client:

```java
OPConnectClient client = OPConnectClientBuilder.builder()
    .withEndpoint("http://localhost:8080")
    .withAccessToken("OP_ACCESS_TOKEN")
    .build();
```
