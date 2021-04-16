package com.sanctionco.connect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

public class Vault {
  private final String id;
  private final String name;
  private final String description;
  private final Integer attributeVersion;
  private final Integer contentVersion;
  private final Integer items;
  private final String type;
  private final Instant createdAt;
  private final Instant updatedAt;

  @JsonCreator
  public Vault(@JsonProperty("id") String id,
               @JsonProperty("name") String name,
               @JsonProperty("description") String description,
               @JsonProperty("attributeVersion") Integer attributeVersion,
               @JsonProperty("contentVersion") Integer contentVersion,
               @JsonProperty("items") Integer items,
               @JsonProperty("type") String type,
               @JsonProperty("createdAt") Instant createdAt,
               @JsonProperty("updatedAt") Instant updatedAt) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.attributeVersion = attributeVersion;
    this.contentVersion = contentVersion;
    this.items = items;
    this.type = type;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Integer getAttributeVersion() {
    return attributeVersion;
  }

  public Integer getContentVersion() {
    return contentVersion;
  }

  public Integer getItems() {
    return items;
  }

  public String getType() {
    return type;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vault vault = (Vault) o;
    return id.equals(vault.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Vault.class.getSimpleName() + "[", "]")
        .add("id='" + id + "'")
        .add("name='" + name + "'")
        .add("description='" + description + "'")
        .add("attributeVersion=" + attributeVersion)
        .add("contentVersion=" + contentVersion)
        .add("items=" + items)
        .add("type='" + type + "'")
        .add("createdAt=" + createdAt)
        .add("updatedAt=" + updatedAt)
        .toString();
  }
}
