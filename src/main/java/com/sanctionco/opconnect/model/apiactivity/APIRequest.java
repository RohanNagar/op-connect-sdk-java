package com.sanctionco.opconnect.model.apiactivity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents an {@code APIRequest} made to 1Password.
 */
public class APIRequest {
  private final String requestId;
  private final Instant timestamp;
  private final APIRequestAction action;
  private final APIRequestResult result;
  private final Actor actor;
  private final Resource resource;

  @JsonCreator
  APIRequest(@JsonProperty("requestId") String requestId,
             @JsonProperty("timestamp") Instant timestamp,
             @JsonProperty("action") APIRequestAction action,
             @JsonProperty("result") APIRequestResult result,
             @JsonProperty("actor") Actor actor,
             @JsonProperty("resource") Resource resource) {
    this.requestId = requestId;
    this.timestamp = timestamp;
    this.action = action;
    this.result = result;
    this.actor = actor;
    this.resource = resource;
  }

  /**
   * Get the id associated with this request.
   *
   * @return the id for the request
   */
  public String getRequestId() {
    return requestId;
  }

  /**
   * Get the time this request was made.
   *
   * @return the time the request was made
   */
  public Instant getTimestamp() {
    return timestamp;
  }

  /**
   * Get the action requested.
   *
   * @return the type of action requested
   */
  public APIRequestAction getAction() {
    return action;
  }

  /**
   * Get the result of the request.
   *
   * @return the result of the request
   */
  public APIRequestResult getResult() {
    return result;
  }

  /**
   * Get the actor who made the request.
   *
   * @return the {@link Actor} that made the request
   */
  public Actor getActor() {
    return actor;
  }

  /**
   * Get the resource requested.
   *
   * @return the {@link Resource requested}
   */
  public Resource getResource() {
    return resource;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    APIRequest that = (APIRequest) o;
    return Objects.equals(requestId, that.requestId)
        && Objects.equals(timestamp, that.timestamp)
        && action == that.action
        && result == that.result
        && Objects.equals(actor, that.actor)
        && Objects.equals(resource, that.resource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestId, timestamp, action, result, actor, resource);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", APIRequest.class.getSimpleName() + "[", "]")
        .add("requestID='" + requestId + "'")
        .add("timestamp=" + timestamp)
        .add("action=" + action)
        .add("result=" + result)
        .add("actor=" + actor)
        .add("resource=" + resource)
        .toString();
  }
}
