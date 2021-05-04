package com.sanctionco.opconnect.model.apiactivity;

/**
 * Represents an action that occurred from an {@link APIRequest}.
 */
public enum APIRequestAction {
  /**
   * Represents a READ (i.e. GET) API request action.
   */
  READ,

  /**
   * Represents a CREATE (i.e. POST) API request action.
   */
  CREATE,

  /**
   * Represents an UPDATE (i.e. PUT or PATCH) API request action.
   */
  UPDATE,

  /**
   * Represents a DELETE (i.e. DELETE) API request action.
   */
  DELETE
}
