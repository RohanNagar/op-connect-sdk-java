package com.sanctionco.opconnect.model.apiactivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActorTest {
  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final String JSON_PATH = "/fixtures/apirequest/actor.json";

  @Test
  void shouldDeserialize() throws Exception {
    Actor expected = new Actor("test-id", "test-account", "test-jti", "test-useragent", "test-ip");
    Actor actual = MAPPER.readValue(getClass().getResourceAsStream(JSON_PATH), Actor.class);

    assertEquals(expected, actual);
  }
}
