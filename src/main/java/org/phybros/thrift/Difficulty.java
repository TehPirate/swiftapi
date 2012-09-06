/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.phybros.thrift;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

/**
 * Game difficulties
 */
public enum Difficulty implements org.apache.thrift.TEnum {
  /**
   * Players regain health over time, hostile mobs don't spawn,
   * the hunger bar does not deplete.
   */
  PEACEFUL(0),
  /**
   * Hostile mobs spawn, enemies deal less damage than on
   * normal difficulty, the hunger bar does deplete and starving
   * deals up to 5 hearts of damage.
   */
  EASY(1),
  /**
   * Hostile mobs spawn, enemies deal normal amounts of damage,
   * the hunger bar does deplete and starving deals up to 9.5
   * hearts of damage.
   */
  NORMAL(2),
  /**
   * Hostile mobs spawn, enemies deal greater damage than on
   * normal difficulty, the hunger bar does deplete and starving
   * can kill players.
   */
  HARD(3);

  private final int value;

  private Difficulty(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static Difficulty findByValue(int value) { 
    switch (value) {
      case 0:
        return PEACEFUL;
      case 1:
        return EASY;
      case 2:
        return NORMAL;
      case 3:
        return HARD;
      default:
        return null;
    }
  }
}
