package com.sanctionco.opconnect.model;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents SCIM-style filter that can be used to filter list requests server-side.
 */
public class Filter {
  private final String filter;

  private Filter(Builder builder) {
    if (builder.operator.equals(Operator.PRESENT)) {
      filter = String.format("%s pr", builder.property);
    } else {
      filter = String.format("%s %s \"%s\"",
          builder.property, builder.operator.getOpText(), builder.value);
    }
  }

  private Filter(Filter other, Filter and, Filter or) {
    if (and == null && or == null) {
      filter = other.filter;
    } else if (and != null) {
      filter = String.format("(%s and %s)", other.filter, and.filter);
    } else {
      filter = String.format("(%s or %s)", other.filter, or.filter);
    }
  }

  /**
   * Get the string value of the filter.
   *
   * @return the string value of the filter
   */
  public String getFilter() {
    return filter;
  }

  /**
   * Create a new {@code Filter}, concatenating the provided
   * filter using the {@code and} operator.
   *
   * @param other the other filter to concatenate to this one
   * @return the new filter
   */
  public Filter and(Filter other) {
    return new Filter(this, other, null);
  }

  /**
   * Create a new {@code Filter}, concatenating the provided
   * filter using the {@code or} operator.
   *
   * @param other the other filter to concatenate to this one
   * @return the new filter
   */
  public Filter or(Filter other) {
    return new Filter(this, null, other);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Filter filter1 = (Filter) o;
    return Objects.equals(filter, filter1.filter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filter);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Filter.class.getSimpleName() + "[", "]")
        .add("filter='" + filter + "'")
        .toString();
  }

  /**
   * Create a new title Filter builder.
   *
   * @return a new {@code Filter.Builder}
   */
  public static Builder title() {
    return new Builder("title");
  }

  /**
   * Create a new name Filter builder.
   *
   * @return a new {@code Filter.Builder}
   */
  public static Builder name() {
    return new Builder("name");
  }

  /**
   * Create a new Filter builder on the given property.
   *
   * @param property the property to filter on
   * @return a new {@code Filter.Builder}
   */
  public static Builder onProperty(String property) {
    return new Builder(property);
  }

  /**
   * The builder class used to build a new {@link Filter}.
   */
  public static class Builder {
    private final String property;
    private Operator operator;
    private String value;

    private Builder(String property) {
      this.property = property;
    }

    private Filter withOpAndValue(Operator op, String value) {
      this.operator = op;
      this.value = value;
      return new Filter(this);
    }

    /**
     * Builds a new {@code Filter} with the 'eq' operator.
     * Filters based on the property exactly matching the given value.
     *
     * @param value the value that the property should be equal to
     * @return the new {@code Filter}
     */
    public Filter equals(String value) {
      return withOpAndValue(Operator.EQUALS, value);
    }

    /**
     * Builds a new {@code Filter} with the 'co' operator.
     * Filters based on the property containing the given value.
     *
     * @param value the value that the property should contain
     * @return the new {@code Filter}
     */
    public Filter contains(String value) {
      return withOpAndValue(Operator.CONTAINS, value);
    }

    /**
     * Builds a new {@code Filter} with the 'sw' operator.
     * Filters based on the property starting with the given value.
     *
     * @param value the value that the property should start with
     * @return the new {@code Filter}
     */
    public Filter startsWith(String value) {
      return withOpAndValue(Operator.STARTS_WITH, value);
    }

    /**
     * Builds a new {@code Filter} with the 'pr' operator.
     * Filters based on the property being present.
     *
     * @return the new {@code Filter}
     */
    public Filter present() {
      return withOpAndValue(Operator.PRESENT, "");
    }

    /**
     * Builds a new {@code Filter} with the 'gt' operator.
     * Filters based on the property being alphanumerically greater than
     * the given value.
     *
     * @param value the value that the property should be greater than
     * @return the new {@code Filter}
     */
    public Filter greaterThan(String value) {
      return withOpAndValue(Operator.GREATER_THAN, value);
    }

    /**
     * Builds a new {@code Filter} with the 'ge' operator.
     * Filters based on the property being alphanumerically greater than
     * or equal to the given value.
     *
     * @param value the value that the property should be greater than or equal to
     * @return the new {@code Filter}
     */
    public Filter greaterThanOrEqual(String value) {
      return withOpAndValue(Operator.GREATER_THAN_OR_EQUAL, value);
    }

    /**
     * Builds a new {@code Filter} with the 'lt' operator.
     * Filters based on the property being alphanumerically less than
     * the given value.
     *
     * @param value the value that the property should be less than
     * @return the new {@code Filter}
     */
    public Filter lessThan(String value) {
      return withOpAndValue(Operator.LESS_THAN, value);
    }

    /**
     * Builds a new {@code Filter} with the 'le' operator.
     * Filters based on the property being alphanumerically less than
     * or equal to the given value.
     *
     * @param value the value that the property should be less than or equal to
     * @return the new {@code Filter}
     */
    public Filter lessThanOrEqual(String value) {
      return withOpAndValue(Operator.LESS_THAN_OR_EQUAL, value);
    }
  }

  private enum Operator {
    EQUALS("eq"),
    CONTAINS("co"),
    STARTS_WITH("sw"),
    PRESENT("pr"),
    GREATER_THAN("gt"),
    GREATER_THAN_OR_EQUAL("ge"),
    LESS_THAN("lt"),
    LESS_THAN_OR_EQUAL("le");

    private final String opText;

    Operator(String opText) {
      this.opText = opText;
    }

    String getOpText() {
      return opText;
    }
  }
}
