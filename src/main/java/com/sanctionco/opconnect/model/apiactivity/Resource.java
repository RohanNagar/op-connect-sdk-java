package com.sanctionco.opconnect.model.apiactivity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sanctionco.opconnect.model.ItemId;
import com.sanctionco.opconnect.model.VaultId;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a resource that was specified in an {@link APIRequest}.
 */
public class Resource {
  private final ResourceType type;
  private final VaultId vault;
  private final ItemId item;
  private final Integer itemVersion;

  @JsonCreator
  public Resource(@JsonProperty("type") ResourceType type,
                  @JsonProperty("vault") VaultId vault,
                  @JsonProperty("item") ItemId item,
                  @JsonProperty("itemVersion") Integer itemVersion) {
    this.type = type;
    this.vault = vault;
    this.item = item;
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
  public VaultId getVault() {
    return vault;
  }

  /**
   * Get the ID of the item requested.
   *
   * @return an object containing the ID of the item requested
   */
  public ItemId getItem() {
    return item;
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
        && Objects.equals(vault, resource.vault)
        && Objects.equals(item, resource.item)
        && Objects.equals(itemVersion, resource.itemVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, vault, item, itemVersion);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Resource.class.getSimpleName() + "[", "]")
        .add("type=" + type)
        .add("vault=" + vault)
        .add("item=" + item)
        .add("itemVersion=" + itemVersion)
        .toString();
  }
}
