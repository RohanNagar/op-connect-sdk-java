package com.sanctionco.opconnect.model.apiactivity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sanctionco.opconnect.model.Id;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a resource that was specified in an {@link APIRequest}.
 */
public class Resource {
  private final ResourceType type;
  private final Id vaultId;
  private final Id itemId;
  private final Integer itemVersion;

  @JsonCreator
  Resource(@JsonProperty("type") ResourceType type,
           @JsonProperty("vault") Id vaultId,
           @JsonProperty("item") Id itemId,
           @JsonProperty("itemVersion") Integer itemVersion) {
    this.type = type;
    this.vaultId = vaultId;
    this.itemId = itemId;
    this.itemVersion = itemVersion;
  }

  /**
   * Get the type of the resource.
   *
   * @return the type of the resource requested
   */
  public ResourceType getType() {
    return type;
  }

  /**
   * Get the ID of the of the vault requested.
   *
   * @return an object containing the ID of the vault requested
   */
  public Id getVaultId() {
    return vaultId;
  }

  /**
   * Get the ID of the item requested.
   *
   * @return an object containing the ID of the item requested
   */
  public Id getItemId() {
    return itemId;
  }

  /**
   * Get the version of the item requested.
   *
   * @return the version of the item requested
   */
  public Integer getItemVersion() {
    return itemVersion;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Resource resource = (Resource) o;
    return type == resource.type
        && Objects.equals(vaultId, resource.vaultId)
        && Objects.equals(itemId, resource.itemId)
        && Objects.equals(itemVersion, resource.itemVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, vaultId, itemId, itemVersion);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Resource.class.getSimpleName() + "[", "]")
        .add("type=" + type)
        .add("vaultId=" + vaultId)
        .add("itemId=" + itemId)
        .add("itemVersion=" + itemVersion)
        .toString();
  }
}
