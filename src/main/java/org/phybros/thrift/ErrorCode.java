/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.phybros.thrift;



public enum ErrorCode implements org.apache.thrift.TEnum {
  INVALID_REQUEST(0),
  INVALID_AUTHSTRING(1),
  NOT_FOUND(2);

  private final int value;

  private ErrorCode(int value) {
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
  public static ErrorCode findByValue(int value) { 
    switch (value) {
      case 0:
        return INVALID_REQUEST;
      case 1:
        return INVALID_AUTHSTRING;
      case 2:
        return NOT_FOUND;
      default:
        return null;
    }
  }
}
