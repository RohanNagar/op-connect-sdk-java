package com.sanctionco.connect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Item {
  private final String id;
  private final String title;
  private final Vault vault;
  private final Category category;
  private final List<URL> urls;
  private final Boolean favorite;
  private final List<String> tags;
  private final Integer version;
  private final Boolean trashed;
  private final Instant createdAt;
  private final Instant updatedAt;
  private final String lastEditedBy;
  private final List<Section> sections;
  private final List<Field> fields;

  @JsonCreator
  public Item(@JsonProperty("id") String id,
              @JsonProperty("title") String title,
              @JsonProperty("vault") Vault vault,
              @JsonProperty("category") Category category,
              @JsonProperty("urls") List<URL> urls,
              @JsonProperty("favorite") Boolean favorite,
              @JsonProperty("tags") List<String> tags,
              @JsonProperty("version") Integer version,
              @JsonProperty("trashed") Boolean trashed,
              @JsonProperty("createdAt") Instant createdAt,
              @JsonProperty("updatedAt") Instant updatedAt,
              @JsonProperty("lastEditedBy") String lastEditedBy,
              @JsonProperty("sections") List<Section> sections,
              @JsonProperty("fields") List<Field> fields) {
    this.id = id;
    this.title = title;
    this.vault = vault;
    this.category = category;
    this.urls = urls;
    this.favorite = favorite;
    this.tags = tags;
    this.version = version;
    this.trashed = trashed;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.lastEditedBy = lastEditedBy;
    this.sections = sections;
    this.fields = fields;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Vault getVault() {
    return vault;
  }

  public Category getCategory() {
    return category;
  }

  public List<URL> getUrls() {
    return urls;
  }

  public Boolean getFavorite() {
    return favorite;
  }

  public List<String> getTags() {
    return tags;
  }

  public Integer getVersion() {
    return version;
  }

  public Boolean getTrashed() {
    return trashed;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public String getLastEditedBy() {
    return lastEditedBy;
  }

  public List<Section> getSections() {
    return sections;
  }

  public List<Field> getFields() {
    return fields;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Item item = (Item) o;
    return Objects.equals(id, item.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Item.class.getSimpleName() + "[", "]")
        .add("id='" + id + "'")
        .add("title='" + title + "'")
        .add("vault=" + vault)
        .add("category=" + category)
        .add("urls=" + urls)
        .add("favorite=" + favorite)
        .add("tags=" + tags)
        .add("version=" + version)
        .add("trashed=" + trashed)
        .add("createdAt=" + createdAt)
        .add("updatedAt=" + updatedAt)
        .add("lastEditedBy='" + lastEditedBy + "'")
        .add("sections=" + sections)
        .add("fields=" + fields)
        .toString();
  }
}
