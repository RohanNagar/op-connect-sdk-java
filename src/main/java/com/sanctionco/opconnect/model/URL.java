package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a URL contained in an item.
 */
public class URL {
  private final String label;
  private final String url;
  private final Boolean primary;

  @JsonCreator
  URL(@JsonProperty("label") String label,
      @JsonProperty("href") String url,
      @JsonProperty("primary") Boolean primary) {
    this.label = label;
    this.url = url;
    this.primary = primary;
  }

  /**
   * Get the label associated with this URL.
   *
   * @return the label of the URL
   */
  public String getLabel() {
    return label;
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
    return Objects.equals(url, url1.url)
        && Objects.equals(primary, url1.primary)
        && Objects.equals(label, url1.label);
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, url, primary);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", URL.class.getSimpleName() + "[", "]")
        .add("label='" + label + "'")
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
    return new URL("", url, true);
  }

  /**
   * Create a new non-primary URL from the given string.
   *
   * @param url the string URL
   * @return a new instance of {@code URL}
   */
  public static URL standard(String url) {
    return new URL("", url, false);
  }
}
