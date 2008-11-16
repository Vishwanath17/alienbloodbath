// Copyright 2008 and onwards Matthew Burkhart.
//
// This program is free software; you can redistribute it and/or modify it under
// the terms of the GNU General Public License as published by the Free Software
// Foundation; version 3 of the License.
//
// This program is distributed in the hope that it will be useful, but WITHOUT
// ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
// FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
// details.

package android.com.abb;

import android.graphics.Rect;
import android.view.KeyEvent;
import java.lang.Math;

import android.com.abb.Entity;


public class Fire extends Entity {
  public Fire() {
    super();
    radius = kRadius;
    sprite_source =
        new Rect(0, kSpriteBase, kSpriteWidth, kSpriteBase + kSpriteHeight);
  }

  public void Step(float time_step) {
    super.Step(time_step);

    // Update the sprite to reflect the age / life of the fire entity.
    frame += time_step * kFrameRate;
    int rounded_frame = (int)frame;
    if (rounded_frame <= kFrames) {
      sprite_source.top = kSpriteBase + kSpriteHeight * rounded_frame;
      sprite_source.bottom = kSpriteBase + kSpriteHeight * (rounded_frame + 1);
    } else {
      alive = false;  // Signal for deletion.
    }
  }

  public void CollideEntity(Entity entity) {
    if (Math.abs(entity.x - x) < radius + entity.radius &&
        Math.abs(entity.y - y) < radius + entity.radius) {
      entity.alive = false;  // Anything which collides with fire dies.
    }
  }

  private float frame = 0.0f;

  private static final int kFrames = 14;
  private static final float kFrameRate = 6.0f;  // Frames / sec.
  private static final float kRadius = 16.0f;
  private static final int kSpriteBase = 521;
  private static final int kSpriteWidth = 64;
  private static final int kSpriteHeight = 36;
}