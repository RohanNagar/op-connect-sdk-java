package com.sanctionco.opconnect.model.apiactivity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class Actor {
  private final String id;
  private final String account;
  private final String jti;
  private final String userAgent;
  private final String ip;

  @JsonCreator
  public Actor(@JsonProperty("id") String id,
               @JsonProperty("account") String account,
               @JsonProperty("jti") String jti,
               @JsonProperty("userAgent") String userAgent,
               @JsonProperty("ip") String ip) {
    this.id = id;
    this.account = account;
    this.jti = jti;
    this.userAgent = userAgent;
    this.ip = ip;
  }

  public String getId() {
    return id;
  }

  public String getAccount() {
    return account;
  }

  public String getJti() {
    return jti;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public String getIp() {
    return ip;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Actor actor = (Actor) o;
    return Objects.equals(id, actor.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Actor.class.getSimpleName() + "[", "]")
        .add("id='" + id + "'")
        .add("account='" + account + "'")
        .add("jti='" + jti + "'")
        .add("userAgent='" + userAgent + "'")
        .add("ip='" + ip + "'")
        .toString();
  }
}
