package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a URL contained in an item.
 */
public class URL {
  private final String url;
  private final Boolean primary;

  @JsonCreator
  URL(@JsonProperty("href") String url,
      @JsonProperty("primary") Boolean primary) {
    this.url = url;
    this.primary = primary;
  }

  /**
   * Get the actual string URL.
   *
   * @return the string URL
   */
  @JsonProperty("href")
  public String getUrl() {
    return url;
  }

  /**
   * Get whether this URL is a primary URL address or not.
   *
   * @return true if this URL is primary, false otherwise
   */
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

  /**
   * Create a new primary URL from the given string.
   *
   * @param url the string URL
   * @return a new instance of {@code URL}
   */
  public static URL primary(String url) {
    return new URL(url, true);
  }

  /**
   * Create a new non-primary URL from the given string.
   *
   * @param url the string URL
   * @return a new instance of {@code URL}
   */
  public static URL standard(String url) {
    return new URL(url, false);
  }
}
