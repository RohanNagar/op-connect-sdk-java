package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a file that is stored alongside an {@link Item}.
 */
@JsonDeserialize(builder = File.Builder.class)
public class File {
  private final String id;
  private final String name;
  private final int size;
  private final String contentPath;
  private final String content;
  private final Section section;

  private File(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.size = builder.size;
    this.contentPath = builder.contentPath;
    this.content = builder.content;
    this.section = builder.section;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getSize() {
    return size;
  }

  public String getContentPath() {
    return contentPath;
  }

  public String getContent() {
    return content;
  }

  public Section getSection() {
    return section;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    File file = (File) o;
    return Objects.equals(id, file.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", File.class.getSimpleName() + "[", "]")
        .add("id='" + id + "'")
        .add("name='" + name + "'")
        .add("size=" + size)
        .add("contentPath='" + contentPath + "'")
        .add("content='" + content + "'")
        .add("section=" + section)
        .toString();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String id;
    private String name;
    private int size;
    private String contentPath;
    private String content;
    private Section section;

    public Builder withId(String id) {
      this.id = id;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withSize(int size) {
      this.size = size;
      return this;
    }

    @JsonProperty("content_path")
    public Builder withContentPath(String contentPath) {
      this.contentPath = contentPath;
      return this;
    }

    public Builder withContent(String content) {
      this.content = content;
      return this;
    }

    public Builder withSection(Section section) {
      this.section = section;
      return this;
    }

    public File build() {
      return new File(this);
    }
  }
}
