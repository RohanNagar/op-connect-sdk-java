package com.sanctionco.opconnect.model.apiactivity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

public class APIRequest {
  private final String requestId;
  private final Instant timestamp;
  private final APIRequestAction action;
  private final APIRequestResult result;
  private final Actor actor;
  private final Resource resource;

  @JsonCreator
  public APIRequest(@JsonProperty("requestId") String requestId,
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

  public String getRequestId() {
    return requestId;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public APIRequestAction getAction() {
    return action;
  }

  public APIRequestResult getResult() {
    return result;
  }

  public Actor getActor() {
    return actor;
  }

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
