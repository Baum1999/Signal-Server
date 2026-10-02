/*
 * Copyright 2026 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.storage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class VersionedProfileV1Test {

  @Test
  void carriesTimezoneField() {
    final byte[] timezone = new byte[68];
    final VersionedProfileV1 profile = new VersionedProfileV1(
        "version", new byte[81], null, new byte[0], new byte[0], null, new byte[29], new byte[32], timezone);

    assertArrayEquals(timezone, profile.timezone());
  }

  @Test
  void equalsConsidersTimezone() {
    final VersionedProfileV1 a = new VersionedProfileV1(
        "version", new byte[81], null, new byte[0], new byte[0], null, new byte[29], new byte[32], new byte[]{1});
    final VersionedProfileV1 b = new VersionedProfileV1(
        "version", new byte[81], null, new byte[0], new byte[0], null, new byte[29], new byte[32], new byte[]{2});

    assertNotEquals(a, b);
  }
}
