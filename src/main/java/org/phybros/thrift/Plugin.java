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

public class Plugin implements org.apache.thrift.TBase<Plugin, Plugin._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Plugin");

  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField DESCRIPTION_FIELD_DESC = new org.apache.thrift.protocol.TField("description", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField VERSION_FIELD_DESC = new org.apache.thrift.protocol.TField("version", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField WEBSITE_FIELD_DESC = new org.apache.thrift.protocol.TField("website", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField AUTHORS_FIELD_DESC = new org.apache.thrift.protocol.TField("authors", org.apache.thrift.protocol.TType.LIST, (short)5);
  private static final org.apache.thrift.protocol.TField ENABLED_FIELD_DESC = new org.apache.thrift.protocol.TField("enabled", org.apache.thrift.protocol.TType.BOOL, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new PluginStandardSchemeFactory());
    schemes.put(TupleScheme.class, new PluginTupleSchemeFactory());
  }

  public String name; // required
  public String description; // required
  public String version; // required
  public String website; // required
  public List<String> authors; // required
  public boolean enabled; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NAME((short)1, "name"),
    DESCRIPTION((short)2, "description"),
    VERSION((short)3, "version"),
    WEBSITE((short)4, "website"),
    AUTHORS((short)5, "authors"),
    ENABLED((short)6, "enabled");

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
        case 1: // NAME
          return NAME;
        case 2: // DESCRIPTION
          return DESCRIPTION;
        case 3: // VERSION
          return VERSION;
        case 4: // WEBSITE
          return WEBSITE;
        case 5: // AUTHORS
          return AUTHORS;
        case 6: // ENABLED
          return ENABLED;
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
  private static final int __ENABLED_ISSET_ID = 0;
  private BitSet __isset_bit_vector = new BitSet(1);
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DESCRIPTION, new org.apache.thrift.meta_data.FieldMetaData("description", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VERSION, new org.apache.thrift.meta_data.FieldMetaData("version", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.WEBSITE, new org.apache.thrift.meta_data.FieldMetaData("website", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.AUTHORS, new org.apache.thrift.meta_data.FieldMetaData("authors", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.ENABLED, new org.apache.thrift.meta_data.FieldMetaData("enabled", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Plugin.class, metaDataMap);
  }

  public Plugin() {
  }

  public Plugin(
    String name,
    String description,
    String version,
    String website,
    List<String> authors,
    boolean enabled)
  {
    this();
    this.name = name;
    this.description = description;
    this.version = version;
    this.website = website;
    this.authors = authors;
    this.enabled = enabled;
    setEnabledIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Plugin(Plugin other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    if (other.isSetName()) {
      this.name = other.name;
    }
    if (other.isSetDescription()) {
      this.description = other.description;
    }
    if (other.isSetVersion()) {
      this.version = other.version;
    }
    if (other.isSetWebsite()) {
      this.website = other.website;
    }
    if (other.isSetAuthors()) {
      List<String> __this__authors = new ArrayList<String>();
      for (String other_element : other.authors) {
        __this__authors.add(other_element);
      }
      this.authors = __this__authors;
    }
    this.enabled = other.enabled;
  }

  public Plugin deepCopy() {
    return new Plugin(this);
  }

  @Override
  public void clear() {
    this.name = null;
    this.description = null;
    this.version = null;
    this.website = null;
    this.authors = null;
    setEnabledIsSet(false);
    this.enabled = false;
  }

  public String getName() {
    return this.name;
  }

  public Plugin setName(String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public String getDescription() {
    return this.description;
  }

  public Plugin setDescription(String description) {
    this.description = description;
    return this;
  }

  public void unsetDescription() {
    this.description = null;
  }

  /** Returns true if field description is set (has been assigned a value) and false otherwise */
  public boolean isSetDescription() {
    return this.description != null;
  }

  public void setDescriptionIsSet(boolean value) {
    if (!value) {
      this.description = null;
    }
  }

  public String getVersion() {
    return this.version;
  }

  public Plugin setVersion(String version) {
    this.version = version;
    return this;
  }

  public void unsetVersion() {
    this.version = null;
  }

  /** Returns true if field version is set (has been assigned a value) and false otherwise */
  public boolean isSetVersion() {
    return this.version != null;
  }

  public void setVersionIsSet(boolean value) {
    if (!value) {
      this.version = null;
    }
  }

  public String getWebsite() {
    return this.website;
  }

  public Plugin setWebsite(String website) {
    this.website = website;
    return this;
  }

  public void unsetWebsite() {
    this.website = null;
  }

  /** Returns true if field website is set (has been assigned a value) and false otherwise */
  public boolean isSetWebsite() {
    return this.website != null;
  }

  public void setWebsiteIsSet(boolean value) {
    if (!value) {
      this.website = null;
    }
  }

  public int getAuthorsSize() {
    return (this.authors == null) ? 0 : this.authors.size();
  }

  public java.util.Iterator<String> getAuthorsIterator() {
    return (this.authors == null) ? null : this.authors.iterator();
  }

  public void addToAuthors(String elem) {
    if (this.authors == null) {
      this.authors = new ArrayList<String>();
    }
    this.authors.add(elem);
  }

  public List<String> getAuthors() {
    return this.authors;
  }

  public Plugin setAuthors(List<String> authors) {
    this.authors = authors;
    return this;
  }

  public void unsetAuthors() {
    this.authors = null;
  }

  /** Returns true if field authors is set (has been assigned a value) and false otherwise */
  public boolean isSetAuthors() {
    return this.authors != null;
  }

  public void setAuthorsIsSet(boolean value) {
    if (!value) {
      this.authors = null;
    }
  }

  public boolean isEnabled() {
    return this.enabled;
  }

  public Plugin setEnabled(boolean enabled) {
    this.enabled = enabled;
    setEnabledIsSet(true);
    return this;
  }

  public void unsetEnabled() {
    __isset_bit_vector.clear(__ENABLED_ISSET_ID);
  }

  /** Returns true if field enabled is set (has been assigned a value) and false otherwise */
  public boolean isSetEnabled() {
    return __isset_bit_vector.get(__ENABLED_ISSET_ID);
  }

  public void setEnabledIsSet(boolean value) {
    __isset_bit_vector.set(__ENABLED_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case DESCRIPTION:
      if (value == null) {
        unsetDescription();
      } else {
        setDescription((String)value);
      }
      break;

    case VERSION:
      if (value == null) {
        unsetVersion();
      } else {
        setVersion((String)value);
      }
      break;

    case WEBSITE:
      if (value == null) {
        unsetWebsite();
      } else {
        setWebsite((String)value);
      }
      break;

    case AUTHORS:
      if (value == null) {
        unsetAuthors();
      } else {
        setAuthors((List<String>)value);
      }
      break;

    case ENABLED:
      if (value == null) {
        unsetEnabled();
      } else {
        setEnabled((Boolean)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NAME:
      return getName();

    case DESCRIPTION:
      return getDescription();

    case VERSION:
      return getVersion();

    case WEBSITE:
      return getWebsite();

    case AUTHORS:
      return getAuthors();

    case ENABLED:
      return Boolean.valueOf(isEnabled());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NAME:
      return isSetName();
    case DESCRIPTION:
      return isSetDescription();
    case VERSION:
      return isSetVersion();
    case WEBSITE:
      return isSetWebsite();
    case AUTHORS:
      return isSetAuthors();
    case ENABLED:
      return isSetEnabled();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Plugin)
      return this.equals((Plugin)that);
    return false;
  }

  public boolean equals(Plugin that) {
    if (that == null)
      return false;

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_description = true && this.isSetDescription();
    boolean that_present_description = true && that.isSetDescription();
    if (this_present_description || that_present_description) {
      if (!(this_present_description && that_present_description))
        return false;
      if (!this.description.equals(that.description))
        return false;
    }

    boolean this_present_version = true && this.isSetVersion();
    boolean that_present_version = true && that.isSetVersion();
    if (this_present_version || that_present_version) {
      if (!(this_present_version && that_present_version))
        return false;
      if (!this.version.equals(that.version))
        return false;
    }

    boolean this_present_website = true && this.isSetWebsite();
    boolean that_present_website = true && that.isSetWebsite();
    if (this_present_website || that_present_website) {
      if (!(this_present_website && that_present_website))
        return false;
      if (!this.website.equals(that.website))
        return false;
    }

    boolean this_present_authors = true && this.isSetAuthors();
    boolean that_present_authors = true && that.isSetAuthors();
    if (this_present_authors || that_present_authors) {
      if (!(this_present_authors && that_present_authors))
        return false;
      if (!this.authors.equals(that.authors))
        return false;
    }

    boolean this_present_enabled = true;
    boolean that_present_enabled = true;
    if (this_present_enabled || that_present_enabled) {
      if (!(this_present_enabled && that_present_enabled))
        return false;
      if (this.enabled != that.enabled)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(Plugin other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    Plugin typedOther = (Plugin)other;

    lastComparison = Boolean.valueOf(isSetName()).compareTo(typedOther.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, typedOther.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDescription()).compareTo(typedOther.isSetDescription());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDescription()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.description, typedOther.description);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVersion()).compareTo(typedOther.isSetVersion());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVersion()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.version, typedOther.version);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWebsite()).compareTo(typedOther.isSetWebsite());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWebsite()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.website, typedOther.website);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAuthors()).compareTo(typedOther.isSetAuthors());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAuthors()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.authors, typedOther.authors);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEnabled()).compareTo(typedOther.isSetEnabled());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEnabled()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.enabled, typedOther.enabled);
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
    StringBuilder sb = new StringBuilder("Plugin(");
    boolean first = true;

    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("description:");
    if (this.description == null) {
      sb.append("null");
    } else {
      sb.append(this.description);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("version:");
    if (this.version == null) {
      sb.append("null");
    } else {
      sb.append(this.version);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("website:");
    if (this.website == null) {
      sb.append("null");
    } else {
      sb.append(this.website);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("authors:");
    if (this.authors == null) {
      sb.append("null");
    } else {
      sb.append(this.authors);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("enabled:");
    sb.append(this.enabled);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bit_vector = new BitSet(1);
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class PluginStandardSchemeFactory implements SchemeFactory {
    public PluginStandardScheme getScheme() {
      return new PluginStandardScheme();
    }
  }

  private static class PluginStandardScheme extends StandardScheme<Plugin> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Plugin struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DESCRIPTION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.description = iprot.readString();
              struct.setDescriptionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // VERSION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.version = iprot.readString();
              struct.setVersionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // WEBSITE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.website = iprot.readString();
              struct.setWebsiteIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // AUTHORS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list18 = iprot.readListBegin();
                struct.authors = new ArrayList<String>(_list18.size);
                for (int _i19 = 0; _i19 < _list18.size; ++_i19)
                {
                  String _elem20; // required
                  _elem20 = iprot.readString();
                  struct.authors.add(_elem20);
                }
                iprot.readListEnd();
              }
              struct.setAuthorsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // ENABLED
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.enabled = iprot.readBool();
              struct.setEnabledIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Plugin struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.name != null) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(struct.name);
        oprot.writeFieldEnd();
      }
      if (struct.description != null) {
        oprot.writeFieldBegin(DESCRIPTION_FIELD_DESC);
        oprot.writeString(struct.description);
        oprot.writeFieldEnd();
      }
      if (struct.version != null) {
        oprot.writeFieldBegin(VERSION_FIELD_DESC);
        oprot.writeString(struct.version);
        oprot.writeFieldEnd();
      }
      if (struct.website != null) {
        oprot.writeFieldBegin(WEBSITE_FIELD_DESC);
        oprot.writeString(struct.website);
        oprot.writeFieldEnd();
      }
      if (struct.authors != null) {
        oprot.writeFieldBegin(AUTHORS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.authors.size()));
          for (String _iter21 : struct.authors)
          {
            oprot.writeString(_iter21);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(ENABLED_FIELD_DESC);
      oprot.writeBool(struct.enabled);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PluginTupleSchemeFactory implements SchemeFactory {
    public PluginTupleScheme getScheme() {
      return new PluginTupleScheme();
    }
  }

  private static class PluginTupleScheme extends TupleScheme<Plugin> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Plugin struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetName()) {
        optionals.set(0);
      }
      if (struct.isSetDescription()) {
        optionals.set(1);
      }
      if (struct.isSetVersion()) {
        optionals.set(2);
      }
      if (struct.isSetWebsite()) {
        optionals.set(3);
      }
      if (struct.isSetAuthors()) {
        optionals.set(4);
      }
      if (struct.isSetEnabled()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetDescription()) {
        oprot.writeString(struct.description);
      }
      if (struct.isSetVersion()) {
        oprot.writeString(struct.version);
      }
      if (struct.isSetWebsite()) {
        oprot.writeString(struct.website);
      }
      if (struct.isSetAuthors()) {
        {
          oprot.writeI32(struct.authors.size());
          for (String _iter22 : struct.authors)
          {
            oprot.writeString(_iter22);
          }
        }
      }
      if (struct.isSetEnabled()) {
        oprot.writeBool(struct.enabled);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Plugin struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.description = iprot.readString();
        struct.setDescriptionIsSet(true);
      }
      if (incoming.get(2)) {
        struct.version = iprot.readString();
        struct.setVersionIsSet(true);
      }
      if (incoming.get(3)) {
        struct.website = iprot.readString();
        struct.setWebsiteIsSet(true);
      }
      if (incoming.get(4)) {
        {
          org.apache.thrift.protocol.TList _list23 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.authors = new ArrayList<String>(_list23.size);
          for (int _i24 = 0; _i24 < _list23.size; ++_i24)
          {
            String _elem25; // required
            _elem25 = iprot.readString();
            struct.authors.add(_elem25);
          }
        }
        struct.setAuthorsIsSet(true);
      }
      if (incoming.get(5)) {
        struct.enabled = iprot.readBool();
        struct.setEnabledIsSet(true);
      }
    }
  }

}

