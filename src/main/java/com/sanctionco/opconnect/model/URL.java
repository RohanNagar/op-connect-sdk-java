package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class URL {
  private final String url;
  private final Boolean primary;

  @JsonCreator
  public URL(@JsonProperty("href") String url,
             @JsonProperty("primary") Boolean primary) {
    this.url = url;
    this.primary = primary;
  }

  @JsonProperty("href")
  public String getUrl() {
    return url;
  }

  public Boolean getPrimary() {
    return primary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    URL url1 = (URL) o;
    return Objects.equals(url, url1.url) && Objects.equals(primary, url1.primary);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, primary);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", URL.class.getSimpleName() + "[", "]")
        .add("url='" + url + "'")
        .add("primary=" + primary)
        .toString();
  }
}
