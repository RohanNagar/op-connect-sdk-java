package com.sanctionco.opconnect.model;

/**
 * Represents a title filter that can be used to filter
 * items server-side.
 */
public class Filter {
  private final String filter;

  Filter(Builder builder) {
    if (builder.operator.equals(Operator.PRESENT)) {
      filter = "title pr";
    } else {
      filter = String.format("title %s \"%s\"", builder.operator.getOpText(), builder.value);
    }
  }

  Filter(Filter other, Filter and, Filter or) {
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
   * filter using the `and` operator.
   *
   * @param other the other filter to concatenate to this one
   * @return the new filter
   */
  public Filter and(Filter other) {
    return new Filter(this, other, null);
  }

  /**
   * Create a new {@code Filter}, concatenating the provided
   * filter using the `or` operator.
   *
   * @param other the other filter to concatenate to this one
   * @return the new filter
   */
  public Filter or(Filter other) {
    return new Filter(this, null, other);
  }

  /**
   * Create a new title Filter builder.
   *
   * @return a new {@code Filter.Builder}
   */
  public static Builder title() {
    return new Builder();
  }

  public static class Builder {
    private Operator operator;
    private String value;

    Filter withOpAndValue(Operator op, String value) {
      this.operator = op;
      this.value = value;
      return new Filter(this);
    }

    /**
     * Builds a new {@code Filter} with the 'eq' operator.
     * Filters based on the title exactly matching the given value.
     *
     * @param value the value that the title should be equal to
     * @return the new {@code Filter}
     */
    public Filter equals(String value) {
      return withOpAndValue(Operator.EQUALS, value);
    }

    /**
     * Builds a new {@code Filter} with the 'co' operator.
     * Filters based on the title containing the given value.
     *
     * @param value the value that the title should contain
     * @return the new {@code Filter}
     */
    public Filter contains(String value) {
      return withOpAndValue(Operator.CONTAINS, value);
    }

    /**
     * Builds a new {@code Filter} with the 'sw' operator.
     * Filters based on the title starting with the given value.
     *
     * @param value the value that the title should start with
     * @return the new {@code Filter}
     */
    public Filter startsWith(String value) {
      return withOpAndValue(Operator.STARTS_WITH, value);
    }

    /**
     * Builds a new {@code Filter} with the 'pr' operator.
     * Filters based on the title being present.
     *
     * @return the new {@code Filter}
     */
    public Filter present() {
      return withOpAndValue(Operator.PRESENT, "");
    }

    /**
     * Builds a new {@code Filter} with the 'gt' operator.
     * Filters based on the title being alphanumerically greater than
     * the given value.
     *
     * @param value the value that the title should be greater than
     * @return the new {@code Filter}
     */
    public Filter greaterThan(String value) {
      return withOpAndValue(Operator.GREATER_THAN, value);
    }

    /**
     * Builds a new {@code Filter} with the 'ge' operator.
     * Filters based on the title being alphanumerically greater than
     * or equal to the given value.
     *
     * @param value the value that the title should be greater than or equal to
     * @return the new {@code Filter}
     */
    public Filter greaterThanOrEqual(String value) {
      return withOpAndValue(Operator.GREATER_THAN_OR_EQUAL, value);
    }

    /**
     * Builds a new {@code Filter} with the 'lt' operator.
     * Filters based on the title being alphanumerically less than
     * the given value.
     *
     * @param value the value that the title should be less than
     * @return the new {@code Filter}
     */
    public Filter lessThan(String value) {
      return withOpAndValue(Operator.LESS_THAN, value);
    }

    /**
     * Builds a new {@code Filter} with the 'le' operator.
     * Filters based on the title being alphanumerically less than
     * or equal to the given value.
     *
     * @param value the value that the title should be less than or equal to
     * @return the new {@code Filter}
     */
    public Filter lessThanOrEqual(String value) {
      return withOpAndValue(Operator.LESS_THAN_OR_EQUAL, value);
    }
  }

  enum Operator {
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
