package com.sanctionco.opconnect.model.health;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a 1Password Connect Server dependency.
 */
public class Dependency {
  private final String service;
  private final String status;
  private final String message;

  @JsonCreator
  Dependency(@JsonProperty("service") String service,
             @JsonProperty("status") String status,
             @JsonProperty("message") String message) {
    this.service = service;
    this.status = status;
    this.message = message;
  }

  /**
   * The name of the dependency service.
   *
   * @return the name
   */
  public String getService() {
    return service;
  }

  /**
   * The current status of the dependency.
   *
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * The latest message that describes the status of the dependency.
   *
   * @return the message description
   */
  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Dependency that = (Dependency) o;
    return Objects.equals(service, that.service);
  }

  @Override
  public int hashCode() {
    return Objects.hash(service);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Dependency.class.getSimpleName() + "[", "]")
        .add("service='" + service + "'")
        .add("status='" + status + "'")
        .add("message='" + message + "'")
        .toString();
  }
}
