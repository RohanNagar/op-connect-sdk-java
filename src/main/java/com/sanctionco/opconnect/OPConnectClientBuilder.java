package com.sanctionco.opconnect;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * A builder class for {@link OPConnectClient}.
 */
public class OPConnectClientBuilder {
  private String endpoint = null;
  private String accessToken = null;
  private Long timeout = null;

  private OPConnectClientBuilder() {
  }

  /**
   * Creates a new {@code OPConnectClientBuilder} instance.
   *
   * @return the new {@code OPConnectClientBuilder} instance
   */
  public static OPConnectClientBuilder builder() {
    return new OPConnectClientBuilder();
  }

  /**
   * Set the endpoint to use when connecting to 1Password Connect.
   *
   * @param endpoint the endpoint in URL format
   * @return this
   */
  public OPConnectClientBuilder withEndpoint(String endpoint) {
    Objects.requireNonNull(endpoint, "The endpoint must not be null.");

    this.endpoint = ensureTrailingSlashExists(endpoint);

    return this;
  }

  /**
   * Set the access token to use when making requests to 1Password Connect.
   *
   * @param accessToken the access token
   * @return this
   */
  public OPConnectClientBuilder withAccessToken(String accessToken) {
    this.accessToken = Objects.requireNonNull(accessToken, "The access token must not be null.");

    return this;
  }

  /**
   * Set the timeout in milliseconds. Default timeout is 10000 milliseconds (10 seconds).
   *
   * @param timeout the timeout in milliseconds
   * @return this
   */
  public OPConnectClientBuilder withTimeoutInMilliseconds(long timeout) {
    this.timeout = timeout;

    return this;
  }

  /**
   * Builds a new instance of {@link OPConnectClient}.
   *
   * @return the built client
   */
  public OPConnectClient build() {
    Objects.requireNonNull(endpoint,
        "You must provide an endpoint with the builder.withEndpoint() method"
            + " in order to build an OPConnectClient.");
    Objects.requireNonNull(accessToken,
        "You must provide an access token with the"
            + " builder.withAccessToken() method in order to build a OPConnectClient.");

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(this.endpoint)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(JacksonConverterFactory.create(mapper))
        .client(buildHttpClient(this.accessToken))
        .build();

    return retrofit.create(OPConnectClient.class);
  }

  private OkHttpClient buildHttpClient(String accessToken) {
    String authorization = "Bearer " + accessToken;

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    httpClient.addInterceptor((chain) -> {
      Request original = chain.request();

      // Add the authorization header
      Request request = original.newBuilder()
          .header("Authorization", authorization)
          .header("Content-type", "application/json")
          .method(original.method(), original.body())
          .build();

      return chain.proceed(request);
    });

    if (timeout != null) {
      httpClient
          .connectTimeout(timeout, TimeUnit.MILLISECONDS)
          .readTimeout(timeout, TimeUnit.MILLISECONDS)
          .writeTimeout(timeout, TimeUnit.MILLISECONDS);
    }

    return httpClient.build();
  }

  /**
   * Ensures that the given URL ends with a trailing slash ('/').
   *
   * @param url the url to verify contains a trailing slash
   * @return the original url with a trailing slash added if necessary
   */
  static String ensureTrailingSlashExists(String url) {
    return url.endsWith("/")
        ? url
        : url + "/";
  }
}
