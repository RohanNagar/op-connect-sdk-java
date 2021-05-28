package com.sanctionco.opconnect.model.health;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents details on the 1Password Connect Server, including
 * its dependencies.
 */
public class ConnectServer {
  private final String name;
  private final String version;
  private final List<Dependency> dependencies;

  @JsonCreator
  ConnectServer(@JsonProperty("name") String name,
                @JsonProperty("version") String version,
                @JsonProperty("dependencies") List<Dependency> dependencies) {
    this.name = name;
    this.version = version;
    this.dependencies = dependencies;
  }

  /**
   * The name of the Connect server.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * The current version of the Connect server.
   *
   * @return the current version
   */
  public String getVersion() {
    return version;
  }

  /**
   * The list of {@link Dependency} objects that the Connect server depends on.
   *
   * @return the list of {@link Dependency} objects
   */
  public List<Dependency> getDependencies() {
    return dependencies;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ConnectServer that = (ConnectServer) o;
    return Objects.equals(name, that.name)
        && Objects.equals(version, that.version)
        && Objects.equals(dependencies, that.dependencies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, version, dependencies);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ConnectServer.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("version='" + version + "'")
        .add("dependencies=" + dependencies)
        .toString();
  }
}
