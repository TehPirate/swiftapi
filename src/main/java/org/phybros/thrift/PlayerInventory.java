/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.phybros.thrift;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

public class PlayerInventory implements org.apache.thrift.TBase<PlayerInventory, PlayerInventory._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PlayerInventory");

  private static final org.apache.thrift.protocol.TField INVENTORY_FIELD_DESC = new org.apache.thrift.protocol.TField("inventory", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField ITEM_IN_HAND_FIELD_DESC = new org.apache.thrift.protocol.TField("itemInHand", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField ARMOR_FIELD_DESC = new org.apache.thrift.protocol.TField("armor", org.apache.thrift.protocol.TType.STRUCT, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new PlayerInventoryStandardSchemeFactory());
    schemes.put(TupleScheme.class, new PlayerInventoryTupleSchemeFactory());
  }

  public List<ItemStack> inventory; // required
  public ItemStack itemInHand; // required
  public PlayerArmor armor; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    INVENTORY((short)1, "inventory"),
    ITEM_IN_HAND((short)2, "itemInHand"),
    ARMOR((short)3, "armor");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // INVENTORY
          return INVENTORY;
        case 2: // ITEM_IN_HAND
          return ITEM_IN_HAND;
        case 3: // ARMOR
          return ARMOR;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.INVENTORY, new org.apache.thrift.meta_data.FieldMetaData("inventory", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ItemStack.class))));
    tmpMap.put(_Fields.ITEM_IN_HAND, new org.apache.thrift.meta_data.FieldMetaData("itemInHand", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ItemStack.class)));
    tmpMap.put(_Fields.ARMOR, new org.apache.thrift.meta_data.FieldMetaData("armor", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, PlayerArmor.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PlayerInventory.class, metaDataMap);
  }

  public PlayerInventory() {
  }

  public PlayerInventory(
    List<ItemStack> inventory,
    ItemStack itemInHand,
    PlayerArmor armor)
  {
    this();
    this.inventory = inventory;
    this.itemInHand = itemInHand;
    this.armor = armor;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PlayerInventory(PlayerInventory other) {
    if (other.isSetInventory()) {
      List<ItemStack> __this__inventory = new ArrayList<ItemStack>();
      for (ItemStack other_element : other.inventory) {
        __this__inventory.add(new ItemStack(other_element));
      }
      this.inventory = __this__inventory;
    }
    if (other.isSetItemInHand()) {
      this.itemInHand = new ItemStack(other.itemInHand);
    }
    if (other.isSetArmor()) {
      this.armor = new PlayerArmor(other.armor);
    }
  }

  public PlayerInventory deepCopy() {
    return new PlayerInventory(this);
  }

  @Override
  public void clear() {
    this.inventory = null;
    this.itemInHand = null;
    this.armor = null;
  }

  public int getInventorySize() {
    return (this.inventory == null) ? 0 : this.inventory.size();
  }

  public java.util.Iterator<ItemStack> getInventoryIterator() {
    return (this.inventory == null) ? null : this.inventory.iterator();
  }

  public void addToInventory(ItemStack elem) {
    if (this.inventory == null) {
      this.inventory = new ArrayList<ItemStack>();
    }
    this.inventory.add(elem);
  }

  public List<ItemStack> getInventory() {
    return this.inventory;
  }

  public PlayerInventory setInventory(List<ItemStack> inventory) {
    this.inventory = inventory;
    return this;
  }

  public void unsetInventory() {
    this.inventory = null;
  }

  /** Returns true if field inventory is set (has been assigned a value) and false otherwise */
  public boolean isSetInventory() {
    return this.inventory != null;
  }

  public void setInventoryIsSet(boolean value) {
    if (!value) {
      this.inventory = null;
    }
  }

  public ItemStack getItemInHand() {
    return this.itemInHand;
  }

  public PlayerInventory setItemInHand(ItemStack itemInHand) {
    this.itemInHand = itemInHand;
    return this;
  }

  public void unsetItemInHand() {
    this.itemInHand = null;
  }

  /** Returns true if field itemInHand is set (has been assigned a value) and false otherwise */
  public boolean isSetItemInHand() {
    return this.itemInHand != null;
  }

  public void setItemInHandIsSet(boolean value) {
    if (!value) {
      this.itemInHand = null;
    }
  }

  public PlayerArmor getArmor() {
    return this.armor;
  }

  public PlayerInventory setArmor(PlayerArmor armor) {
    this.armor = armor;
    return this;
  }

  public void unsetArmor() {
    this.armor = null;
  }

  /** Returns true if field armor is set (has been assigned a value) and false otherwise */
  public boolean isSetArmor() {
    return this.armor != null;
  }

  public void setArmorIsSet(boolean value) {
    if (!value) {
      this.armor = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case INVENTORY:
      if (value == null) {
        unsetInventory();
      } else {
        setInventory((List<ItemStack>)value);
      }
      break;

    case ITEM_IN_HAND:
      if (value == null) {
        unsetItemInHand();
      } else {
        setItemInHand((ItemStack)value);
      }
      break;

    case ARMOR:
      if (value == null) {
        unsetArmor();
      } else {
        setArmor((PlayerArmor)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case INVENTORY:
      return getInventory();

    case ITEM_IN_HAND:
      return getItemInHand();

    case ARMOR:
      return getArmor();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case INVENTORY:
      return isSetInventory();
    case ITEM_IN_HAND:
      return isSetItemInHand();
    case ARMOR:
      return isSetArmor();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof PlayerInventory)
      return this.equals((PlayerInventory)that);
    return false;
  }

  public boolean equals(PlayerInventory that) {
    if (that == null)
      return false;

    boolean this_present_inventory = true && this.isSetInventory();
    boolean that_present_inventory = true && that.isSetInventory();
    if (this_present_inventory || that_present_inventory) {
      if (!(this_present_inventory && that_present_inventory))
        return false;
      if (!this.inventory.equals(that.inventory))
        return false;
    }

    boolean this_present_itemInHand = true && this.isSetItemInHand();
    boolean that_present_itemInHand = true && that.isSetItemInHand();
    if (this_present_itemInHand || that_present_itemInHand) {
      if (!(this_present_itemInHand && that_present_itemInHand))
        return false;
      if (!this.itemInHand.equals(that.itemInHand))
        return false;
    }

    boolean this_present_armor = true && this.isSetArmor();
    boolean that_present_armor = true && that.isSetArmor();
    if (this_present_armor || that_present_armor) {
      if (!(this_present_armor && that_present_armor))
        return false;
      if (!this.armor.equals(that.armor))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(PlayerInventory other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    PlayerInventory typedOther = (PlayerInventory)other;

    lastComparison = Boolean.valueOf(isSetInventory()).compareTo(typedOther.isSetInventory());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInventory()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.inventory, typedOther.inventory);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetItemInHand()).compareTo(typedOther.isSetItemInHand());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetItemInHand()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.itemInHand, typedOther.itemInHand);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetArmor()).compareTo(typedOther.isSetArmor());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetArmor()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.armor, typedOther.armor);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("PlayerInventory(");
    boolean first = true;

    sb.append("inventory:");
    if (this.inventory == null) {
      sb.append("null");
    } else {
      sb.append(this.inventory);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("itemInHand:");
    if (this.itemInHand == null) {
      sb.append("null");
    } else {
      sb.append(this.itemInHand);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("armor:");
    if (this.armor == null) {
      sb.append("null");
    } else {
      sb.append(this.armor);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class PlayerInventoryStandardSchemeFactory implements SchemeFactory {
    public PlayerInventoryStandardScheme getScheme() {
      return new PlayerInventoryStandardScheme();
    }
  }

  private static class PlayerInventoryStandardScheme extends StandardScheme<PlayerInventory> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, PlayerInventory struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // INVENTORY
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list10 = iprot.readListBegin();
                struct.inventory = new ArrayList<ItemStack>(_list10.size);
                for (int _i11 = 0; _i11 < _list10.size; ++_i11)
                {
                  ItemStack _elem12; // required
                  _elem12 = new ItemStack();
                  _elem12.read(iprot);
                  struct.inventory.add(_elem12);
                }
                iprot.readListEnd();
              }
              struct.setInventoryIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ITEM_IN_HAND
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.itemInHand = new ItemStack();
              struct.itemInHand.read(iprot);
              struct.setItemInHandIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ARMOR
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.armor = new PlayerArmor();
              struct.armor.read(iprot);
              struct.setArmorIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, PlayerInventory struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.inventory != null) {
        oprot.writeFieldBegin(INVENTORY_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.inventory.size()));
          for (ItemStack _iter13 : struct.inventory)
          {
            _iter13.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.itemInHand != null) {
        oprot.writeFieldBegin(ITEM_IN_HAND_FIELD_DESC);
        struct.itemInHand.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.armor != null) {
        oprot.writeFieldBegin(ARMOR_FIELD_DESC);
        struct.armor.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PlayerInventoryTupleSchemeFactory implements SchemeFactory {
    public PlayerInventoryTupleScheme getScheme() {
      return new PlayerInventoryTupleScheme();
    }
  }

  private static class PlayerInventoryTupleScheme extends TupleScheme<PlayerInventory> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, PlayerInventory struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetInventory()) {
        optionals.set(0);
      }
      if (struct.isSetItemInHand()) {
        optionals.set(1);
      }
      if (struct.isSetArmor()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetInventory()) {
        {
          oprot.writeI32(struct.inventory.size());
          for (ItemStack _iter14 : struct.inventory)
          {
            _iter14.write(oprot);
          }
        }
      }
      if (struct.isSetItemInHand()) {
        struct.itemInHand.write(oprot);
      }
      if (struct.isSetArmor()) {
        struct.armor.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PlayerInventory struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list15 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.inventory = new ArrayList<ItemStack>(_list15.size);
          for (int _i16 = 0; _i16 < _list15.size; ++_i16)
          {
            ItemStack _elem17; // required
            _elem17 = new ItemStack();
            _elem17.read(iprot);
            struct.inventory.add(_elem17);
          }
        }
        struct.setInventoryIsSet(true);
      }
      if (incoming.get(1)) {
        struct.itemInHand = new ItemStack();
        struct.itemInHand.read(iprot);
        struct.setItemInHandIsSet(true);
      }
      if (incoming.get(2)) {
        struct.armor = new PlayerArmor();
        struct.armor.read(iprot);
        struct.setArmorIsSet(true);
      }
    }
  }

}

