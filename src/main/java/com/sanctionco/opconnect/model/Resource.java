package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class Resource {
  private final ResourceType type;
  private final Vault vault;
  private final Item item;
  private final Integer itemVersion;

  @JsonCreator
  public Resource(@JsonProperty("type") ResourceType type,
                  @JsonProperty("vault") Vault vault,
                  @JsonProperty("item") Item item,
                  @JsonProperty("itemVersion") Integer itemVersion) {
    this.type = type;
    this.vault = vault;
    this.item = item;
    this.itemVersion = itemVersion;
  }

  public ResourceType getType() {
    return type;
  }

  public Vault getVault() {
    return vault;
  }

  public Item getItem() {
    return item;
  }

  public Integer getItemVersion() {
    return itemVersion;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Resource resource = (Resource) o;
    return type == resource.type && Objects.equals(vault, resource.vault) && Objects.equals(item, resource.item) && Objects.equals(itemVersion, resource.itemVersion);
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
