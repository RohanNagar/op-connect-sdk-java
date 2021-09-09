package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

@JsonDeserialize(builder = Vault.Builder.class)
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

  public Vault(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.description = builder.description;
    this.attributeVersion = builder.attributeVersion;
    this.contentVersion = builder.contentVersion;
    this.items = builder.items;
    this.type = builder.type;
    this.createdAt = builder.createdAt;
    this.updatedAt = builder.updatedAt;
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

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String id;
    private String name;
    private String description;
    private Integer attributeVersion;
    private Integer contentVersion;
    private Integer items;
    private String type;
    private Instant createdAt;
    private Instant updatedAt;

    public Builder withId(String id) {
      this.id = id;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder withAttributeVersion(Integer attributeVersion) {
      this.attributeVersion = attributeVersion;
      return this;
    }

    public Builder withContentVersion(Integer contentVersion) {
      this.contentVersion = contentVersion;
      return this;
    }

    public Builder withItems(Integer items) {
      this.items = items;
      return this;
    }

    public Builder withType(String type) {
      this.type = type;
      return this;
    }

    public Builder withCreatedAt(Instant createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder withUpdatedAt(Instant updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public Vault build() {
      return new Vault(this);
    }
  }
}
