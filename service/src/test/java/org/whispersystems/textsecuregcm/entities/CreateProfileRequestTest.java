/*
 * Copyright 2026 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.entities;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Base64;
import org.junit.jupiter.api.Test;
import org.whispersystems.textsecuregcm.util.SystemMapper;

class CreateProfileRequestTest {

  private final ObjectMapper mapper = SystemMapper.jsonMapper();

  @Test
  void deserializesTimezoneField() throws Exception {
    final byte[] timezoneCiphertext = new byte[68];
    final String json = """
        {
          "version": "%s",
          "name": "%s",
          "aboutEmoji": "",
          "about": "",
          "paymentAddress": null,
          "phoneNumberSharing": "%s",
          "avatar": false,
          "sameAvatar": false,
          "badgeIds": [],
          "timezone": "%s"
        }
        """.formatted(
        "a".repeat(64),
        Base64.getEncoder().encodeToString(new byte[81]),
        Base64.getEncoder().encodeToString(new byte[29]),
        Base64.getEncoder().encodeToString(timezoneCiphertext));

    final CreateProfileRequest request = mapper.readValue(json, CreateProfileRequest.class);

    assertArrayEquals(timezoneCiphertext, request.timezone());
  }

  @Test
  void timezoneDefaultsToNullWhenAbsent() throws Exception {
    final String json = """
        {
          "version": "%s",
          "name": "%s",
          "aboutEmoji": "",
          "about": "",
          "paymentAddress": null,
          "phoneNumberSharing": "%s",
          "avatar": false,
          "sameAvatar": false,
          "badgeIds": []
        }
        """.formatted(
        "a".repeat(64),
        Base64.getEncoder().encodeToString(new byte[81]),
        Base64.getEncoder().encodeToString(new byte[29]));

    final CreateProfileRequest request = mapper.readValue(json, CreateProfileRequest.class);

    assertNull(request.timezone());
  }
}
