package com.sanctionco.connect.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@JsonDeserialize(builder = Item.Builder.class)
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

  private Item(String id,
               String title,
               Vault vault,
               Category category,
               List<URL> urls,
               Boolean favorite,
               List<String> tags,
               Integer version,
               Boolean trashed,
               Instant createdAt,
               Instant updatedAt,
               String lastEditedBy,
               List<Section> sections,
               List<Field> fields) {
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

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String id;
    private String title;
    private Vault vault;
    private Category category;
    private List<URL> urls;
    private Boolean favorite;
    private List<String> tags;
    private Integer version;
    private Boolean trashed;
    private Instant createdAt;
    private Instant updatedAt;
    private String lastEditedBy;
    private List<Section> sections;
    private List<Field> fields;

    public Builder withId(String id) {
      this.id = id;

      return this;
    }

    public Builder withTitle(String title) {
      this.title = title;

      return this;
    }

    public Builder withVault(Vault vault) {
      this.vault = vault;

      return this;
    }

    public Builder withCategory(Category category) {
      this.category = category;

      return this;
    }

    public Builder withUrls(List<URL> urls) {
      this.urls = urls;

      return this;
    }

    public Builder withFavorite(Boolean favorite) {
      this.favorite = favorite;

      return this;
    }

    public Builder withTags(List<String> tags) {
      this.tags = tags;

      return this;
    }

    public Builder withVersion(Integer version) {
      this.version = version;

      return this;
    }

    public Builder withTrashed(Boolean trashed) {
      this.trashed = trashed;

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

    public Builder withLastEditedBy(String lastEditedBy) {
      this.lastEditedBy = lastEditedBy;

      return this;
    }

    public Builder withSections(List<Section> sections) {
      this.sections = sections;

      return this;
    }

    public Builder withFields(List<Field> fields) {
      this.fields = fields;

      return this;
    }

    public Item build() {
      return new Item(
          id, title, vault, category, urls, favorite, tags, version, trashed,
          createdAt, updatedAt, lastEditedBy, sections, fields);
    }
  }
}
