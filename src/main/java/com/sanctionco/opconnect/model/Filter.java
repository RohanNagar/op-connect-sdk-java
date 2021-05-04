package com.sanctionco.opconnect.model;

public class Filter {
  private final String filter;

  Filter(Builder builder) {
    if (builder.and != null && builder.or != null) {
      throw new IllegalArgumentException("Only one of 'and'/'or' can be set for a Filter.");
    }

    if (builder.operator == null) {
      throw new IllegalArgumentException("Operator must be set.");
    }

    if (builder.operator.equals(Operator.PRESENT)) {
      filter = String.format("%s %s", builder.field, builder.operator.getOpText());
      return;
    }

    if (builder.value == null) {
      throw new IllegalArgumentException("Value must be set.");
    }

    if (builder.and == null && builder.or == null) {
      // no parenthesis
      filter = String.format("%s %s \"%s\"",
          builder.field,
          builder.operator.getOpText(),
          builder.value);
    } else if (builder.and != null) {
      filter = String.format("(%s %s \"%s\" and %s)",
          builder.field,
          builder.operator.getOpText(),
          builder.value,
          builder.and.filter);
    } else {
      filter = String.format("(%s %s \"%s\" or %s)",
          builder.field,
          builder.operator.getOpText(),
          builder.value,
          builder.or.filter);
    }
  }

  public String getFilter() {
    return filter;
  }

  public static Builder title() {
    Builder builder = new Builder();
    builder.field = "title";

    return builder;
  }

  public static class Builder {
    private String field;
    private Operator operator;
    private String value;

    private Filter and;
    private Filter or;

    Builder withOpAndValue(Operator op, String value) {
      this.operator = op;
      this.value = value;
      return this;
    }

    public Builder equals(String value) {
      return withOpAndValue(Operator.EQUALS, value);
    }

    public Builder contains(String value) {
      return withOpAndValue(Operator.CONTAINS, value);
    }

    public Builder startsWith(String value) {
      return withOpAndValue(Operator.STARTS_WITH, value);
    }

    public Builder present(String value) {
      return withOpAndValue(Operator.PRESENT, value);
    }

    public Builder greaterThan(String value) {
      return withOpAndValue(Operator.GREATER_THAN, value);
    }

    public Builder greaterThanOrEqual(String value) {
      return withOpAndValue(Operator.GREATER_THAN_OR_EQUAL, value);
    }

    public Builder lessThan(String value) {
      return withOpAndValue(Operator.LESS_THAN, value);
    }

    public Builder lessThanOrEqual(String value) {
      return withOpAndValue(Operator.LESS_THAN_OR_EQUAL, value);
    }

    public Builder and(Filter and) {
      this.and = and;
      return this;
    }

    public Builder or(Filter or) {
      this.or = or;
      return this;
    }

    public Filter build() {
      return new Filter(this);
    }
  }

  public enum Operator {
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
