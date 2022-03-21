package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a Vault in 1Password. See the
 * <a href="https://support.1password.com/connect-api-reference/#vault-object">documentation</a>
 * for more details.
 */
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

  Vault(Builder builder) {
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

  /**
   * Get the ID of the vault.
   *
   * @return the ID of the vault
   */
  public String getId() {
    return id;
  }

  /**
   * Get the name of the vault.
   *
   * @return the name of the vault
   */
  public String getName() {
    return name;
  }

  /**
   * Get the description of the vault.
   *
   * @return the description of the vault
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get the version of the vault metadata.
   *
   * @return the version of the vault metadata
   */
  @JsonProperty("attribute_version")
  public Integer getAttributeVersion() {
    return attributeVersion;
  }

  /**
   * Get the version of the vault contents.
   *
   * @return the version of the vault contents
   */
  @JsonProperty("content_version")
  public Integer getContentVersion() {
    return contentVersion;
  }

  /**
   * Get the number of active items in the vault.
   *
   * @return the number of active items in the vault
   */
  public Integer getItems() {
    return items;
  }

  /**
   * The type of vault. One of {@code EVERYONE} (the team shared vault), {@code PERSONAL}
   * (the private vault for the Connect server), or {@code USER_CREATED} (a vault created
   * by a user).
   *
   * @return the type of the vault
   */
  public String getType() {
    return type;
  }

  /**
   * Get the {@link Instant} that the vault was created.
   *
   * @return the time that the vault was created
   */
  @JsonProperty("created_at")
  public Instant getCreatedAt() {
    return createdAt;
  }

  /**
   * Get the {@link Instant} that the vault was last updated.
   *
   * @return the time that the vault was last updated
   */
  @JsonProperty("updated_at")
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

    @JsonProperty("attribute_version")
    @JsonAlias("attributeVersion")
    public Builder withAttributeVersion(Integer attributeVersion) {
      this.attributeVersion = attributeVersion;
      return this;
    }

    @JsonProperty("content_version")
    @JsonAlias("contentVersion")
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

    @JsonProperty("created_at")
    @JsonAlias("createdAt")
    public Builder withCreatedAt(Instant createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    @JsonProperty("updated_at")
    @JsonAlias("updatedAt")
    public Builder withUpdatedAt(Instant updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public Vault build() {
      return new Vault(this);
    }
  }
}
