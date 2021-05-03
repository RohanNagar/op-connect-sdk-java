package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents an item that is stored in a {@link Vault}.
 */
@JsonDeserialize(builder = Item.Builder.class)
public class Item {
  private final String id;
  private final String title;
  private final VaultId vault;
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

  private Item(Builder builder) {
    this.id = builder.id;
    this.title = builder.title;
    this.vault = builder.vaultId;
    this.category = builder.category;
    this.urls = builder.urls;
    this.favorite = builder.favorite;
    this.tags = builder.tags;
    this.version = builder.version;
    this.trashed = builder.trashed;
    this.createdAt = builder.createdAt;
    this.updatedAt = builder.updatedAt;
    this.lastEditedBy = builder.lastEditedBy;
    this.sections = builder.sections;
    this.fields = builder.fields;
  }

  /**
   * Get the unique ID of the item.
   *
   * @return the id of this item
   */
  public String getId() {
    return id;
  }

  /**
   * Get the title of the item.
   *
   * @return the title of this item
   */
  public String getTitle() {
    return title;
  }

  /**
   * Get the {@link VaultId} indicating the vault that this item belongs to.
   *
   * @return the vault id that this item belongs to
   */
  public VaultId getVault() {
    return vault;
  }

  /**
   * Get the category of the item.
   *
   * @return the category of this item
   */
  public Category getCategory() {
    return category;
  }

  /**
   * Get the list of URLs associated with the item.
   *
   * @return the list of URLs associated with this item
   */
  public List<URL> getUrls() {
    return urls;
  }

  /**
   * Get whether or not the item has been marked as a favorite.
   *
   * @return true is this item is a favorite, false otherwise
   */
  public Boolean getFavorite() {
    return favorite;
  }

  /**
   * Get the list of tags associated with the item.
   *
   * @return the list of tags associated with this item
   */
  public List<String> getTags() {
    return tags;
  }

  /**
   * Get the version of the item.
   *
   * @return the version of this item
   */
  public Integer getVersion() {
    return version;
  }

  /**
   * Get whether or not this item is in the trash.
   *
   * @return true if this item is in the trash, false otherwise
   */
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

  public static Builder login() {
    return builder().withCategory(Category.LOGIN);
  }

  public static Builder password() {
    return builder().withCategory(Category.PASSWORD);
  }

  public static class Builder {
    private String id;
    private String title;
    private VaultId vaultId;
    private Category category;
    private List<URL> urls = new ArrayList<>();
    private Boolean favorite = false;
    private List<String> tags = new ArrayList<>();
    private Integer version;
    private Boolean trashed = false;
    private Instant createdAt;
    private Instant updatedAt;
    private String lastEditedBy;
    private List<Section> sections = new ArrayList<>();
    private List<Field> fields = new ArrayList<>();

    public Builder fromItem(Item item) {
      this.id = item.id;
      this.title = item.title;
      this.vaultId = item.vault;
      this.category = item.category;
      this.urls = item.urls;
      this.favorite = item.favorite;
      this.tags = item.tags;
      this.version = item.version;
      this.trashed = item.trashed;
      this.createdAt = item.createdAt;
      this.updatedAt = item.updatedAt;
      this.lastEditedBy = item.lastEditedBy;
      this.sections = item.sections;
      this.fields = item.fields;

      return this;
    }

    public Builder withId(String id) {
      this.id = id;
      return this;
    }

    public Builder withTitle(String title) {
      this.title = title;
      return this;
    }

    public Builder withVault(Vault vault) {
      this.vaultId = new VaultId(vault.getId());
      return this;
    }

    public Builder withVaultId(VaultId vaultId) {
      this.vaultId = vaultId;
      return this;
    }

    public Builder withVaultId(String id) {
      this.vaultId = new VaultId(id);
      return this;
    }

    public Builder withCategory(Category category) {
      this.category = category;
      return this;
    }

    public Builder withUrls(List<URL> urls) {
      this.urls.addAll(urls);
      return this;
    }

    public Builder withUrl(URL url) {
      this.urls.add(url);
      return this;
    }

    public Builder withFavorite(Boolean favorite) {
      this.favorite = favorite;
      return this;
    }

    public Builder withTags(List<String> tags) {
      this.tags.addAll(tags);
      return this;
    }

    public Builder withTag(String tag) {
      this.tags.add(tag);
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
      this.sections.addAll(sections);
      return this;
    }

    public Builder withSection(Section section) {
      this.sections.add(section);
      return this;
    }

    public Builder withFields(List<Field> fields) {
      this.fields.addAll(fields);
      return this;
    }

    public Builder withField(Field field) {
      this.fields.add(field);
      return this;
    }

    public Item build() {
      return new Item(this);
    }
  }
}
