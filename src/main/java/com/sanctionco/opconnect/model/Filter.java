package com.sanctionco.opconnect.model;

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

  public String getFilter() {
    return filter;
  }

  public Filter and(Filter other) {
    return new Filter(this, other, null);
  }

  public Filter or(Filter other) {
    return new Filter(this, null, other);
  }

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

    public Filter equals(String value) {
      return withOpAndValue(Operator.EQUALS, value);
    }

    public Filter contains(String value) {
      return withOpAndValue(Operator.CONTAINS, value);
    }

    public Filter startsWith(String value) {
      return withOpAndValue(Operator.STARTS_WITH, value);
    }

    public Filter present() {
      return withOpAndValue(Operator.PRESENT, "");
    }

    public Filter greaterThan(String value) {
      return withOpAndValue(Operator.GREATER_THAN, value);
    }

    public Filter greaterThanOrEqual(String value) {
      return withOpAndValue(Operator.GREATER_THAN_OR_EQUAL, value);
    }

    public Filter lessThan(String value) {
      return withOpAndValue(Operator.LESS_THAN, value);
    }

    public Filter lessThanOrEqual(String value) {
      return withOpAndValue(Operator.LESS_THAN_OR_EQUAL, value);
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
