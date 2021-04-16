package com.sanctionco.opconnect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class OPConnectClientBuilder {
  private String endpoint = null;
  private String accessToken = null;

  private OPConnectClientBuilder() {
  }

  public static OPConnectClientBuilder builder() {
    return new OPConnectClientBuilder();
  }

  public OPConnectClientBuilder withEndpoint(String endpoint) {
    this.endpoint = Objects.requireNonNull(endpoint, "The endpoint must not be null.");

    return this;
  }

  public OPConnectClientBuilder withAccessToken(String accessToken) {
    this.accessToken = Objects.requireNonNull(accessToken, "The access token must not be null.");

    return this;
  }

  public OPConnectClient build() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());

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

    return httpClient.build();
  }
}
