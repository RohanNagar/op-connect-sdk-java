package com.sanctionco.opconnect.model.apiactivity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents an actor (1Password connect server) that performed an {@link APIRequest}.
 *
 * <p>See the <a href="https://support.1password.com/connect-api-reference/#apirequest-object">
 * APIRequest</a> documentation for more details.
 */
public class Actor {
  private final String id;
  private final String account;
  private final String jti;
  private final String userAgent;
  private final String ip;

  @JsonCreator
  Actor(@JsonProperty("id") String id,
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

  /**
   * Get the ID of the {@code Actor}.
   *
   * @return the id of the actor (connect sever)
   */
  public String getId() {
    return id;
  }

  /**
   * Get the 1Password account ID.
   *
   * @return the id of the 1Password account the actor belongs to
   */
  public String getAccount() {
    return account;
  }

  /**
   * Get the Access Token ID.
   *
   * @return the id of the access token used to authenticate the request
   */
  public String getJti() {
    return jti;
  }

  /**
   * Get the user-agent string.
   *
   * @return the user agent string specified in the request
   */
  public String getUserAgent() {
    return userAgent;
  }

  /**
   * Get the IP address.
   *
   * @return the ip address the request originated from
   */
  public String getIp() {
    return ip;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Actor actor = (Actor) o;
    return Objects.equals(id, actor.id)
        && Objects.equals(account, actor.account)
        && Objects.equals(jti, actor.jti)
        && Objects.equals(userAgent, actor.userAgent)
        && Objects.equals(ip, actor.ip);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, account, jti, userAgent, ip);
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
