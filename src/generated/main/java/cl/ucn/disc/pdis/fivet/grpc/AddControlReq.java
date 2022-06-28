// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fivet.proto

package cl.ucn.disc.pdis.fivet.grpc;

/**
 * Protobuf type {@code AddControlReq}
 */
public final class AddControlReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:AddControlReq)
    AddControlReqOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AddControlReq.newBuilder() to construct.
  private AddControlReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AddControlReq() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new AddControlReq();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AddControlReq(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            numeroFichaMedica_ = input.readInt32();
            break;
          }
          case 18: {
            cl.ucn.disc.pdis.fivet.grpc.ControlEntity.Builder subBuilder = null;
            if (control_ != null) {
              subBuilder = control_.toBuilder();
            }
            control_ = input.readMessage(cl.ucn.disc.pdis.fivet.grpc.ControlEntity.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(control_);
              control_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (com.google.protobuf.UninitializedMessageException e) {
      throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return cl.ucn.disc.pdis.fivet.grpc.Fivet.internal_static_AddControlReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return cl.ucn.disc.pdis.fivet.grpc.Fivet.internal_static_AddControlReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            cl.ucn.disc.pdis.fivet.grpc.AddControlReq.class, cl.ucn.disc.pdis.fivet.grpc.AddControlReq.Builder.class);
  }

  public static final int NUMEROFICHAMEDICA_FIELD_NUMBER = 1;
  private int numeroFichaMedica_;
  /**
   * <code>int32 numeroFichaMedica = 1;</code>
   * @return The numeroFichaMedica.
   */
  @java.lang.Override
  public int getNumeroFichaMedica() {
    return numeroFichaMedica_;
  }

  public static final int CONTROL_FIELD_NUMBER = 2;
  private cl.ucn.disc.pdis.fivet.grpc.ControlEntity control_;
  /**
   * <code>.ControlEntity control = 2;</code>
   * @return Whether the control field is set.
   */
  @java.lang.Override
  public boolean hasControl() {
    return control_ != null;
  }
  /**
   * <code>.ControlEntity control = 2;</code>
   * @return The control.
   */
  @java.lang.Override
  public cl.ucn.disc.pdis.fivet.grpc.ControlEntity getControl() {
    return control_ == null ? cl.ucn.disc.pdis.fivet.grpc.ControlEntity.getDefaultInstance() : control_;
  }
  /**
   * <code>.ControlEntity control = 2;</code>
   */
  @java.lang.Override
  public cl.ucn.disc.pdis.fivet.grpc.ControlEntityOrBuilder getControlOrBuilder() {
    return getControl();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (numeroFichaMedica_ != 0) {
      output.writeInt32(1, numeroFichaMedica_);
    }
    if (control_ != null) {
      output.writeMessage(2, getControl());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (numeroFichaMedica_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, numeroFichaMedica_);
    }
    if (control_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getControl());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof cl.ucn.disc.pdis.fivet.grpc.AddControlReq)) {
      return super.equals(obj);
    }
    cl.ucn.disc.pdis.fivet.grpc.AddControlReq other = (cl.ucn.disc.pdis.fivet.grpc.AddControlReq) obj;

    if (getNumeroFichaMedica()
        != other.getNumeroFichaMedica()) return false;
    if (hasControl() != other.hasControl()) return false;
    if (hasControl()) {
      if (!getControl()
          .equals(other.getControl())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + NUMEROFICHAMEDICA_FIELD_NUMBER;
    hash = (53 * hash) + getNumeroFichaMedica();
    if (hasControl()) {
      hash = (37 * hash) + CONTROL_FIELD_NUMBER;
      hash = (53 * hash) + getControl().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static cl.ucn.disc.pdis.fivet.grpc.AddControlReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static cl.ucn.disc.pdis.fivet.grpc.AddControlReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static cl.ucn.disc.pdis.fivet.grpc.AddControlReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static cl.ucn.disc.pdis.fivet.grpc.AddControlReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static cl.ucn.disc.pdis.fivet.grpc.AddControlReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static cl.ucn.disc.pdis.fivet.grpc.AddControlReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static cl.ucn.disc.pdis.fivet.grpc.AddControlReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static cl.ucn.disc.pdis.fivet.grpc.AddControlReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static cl.ucn.disc.pdis.fivet.grpc.AddControlReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static cl.ucn.disc.pdis.fivet.grpc.AddControlReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static cl.ucn.disc.pdis.fivet.grpc.AddControlReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static cl.ucn.disc.pdis.fivet.grpc.AddControlReq parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(cl.ucn.disc.pdis.fivet.grpc.AddControlReq prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code AddControlReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:AddControlReq)
      cl.ucn.disc.pdis.fivet.grpc.AddControlReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return cl.ucn.disc.pdis.fivet.grpc.Fivet.internal_static_AddControlReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return cl.ucn.disc.pdis.fivet.grpc.Fivet.internal_static_AddControlReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              cl.ucn.disc.pdis.fivet.grpc.AddControlReq.class, cl.ucn.disc.pdis.fivet.grpc.AddControlReq.Builder.class);
    }

    // Construct using cl.ucn.disc.pdis.fivet.grpc.AddControlReq.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      numeroFichaMedica_ = 0;

      if (controlBuilder_ == null) {
        control_ = null;
      } else {
        control_ = null;
        controlBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return cl.ucn.disc.pdis.fivet.grpc.Fivet.internal_static_AddControlReq_descriptor;
    }

    @java.lang.Override
    public cl.ucn.disc.pdis.fivet.grpc.AddControlReq getDefaultInstanceForType() {
      return cl.ucn.disc.pdis.fivet.grpc.AddControlReq.getDefaultInstance();
    }

    @java.lang.Override
    public cl.ucn.disc.pdis.fivet.grpc.AddControlReq build() {
      cl.ucn.disc.pdis.fivet.grpc.AddControlReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public cl.ucn.disc.pdis.fivet.grpc.AddControlReq buildPartial() {
      cl.ucn.disc.pdis.fivet.grpc.AddControlReq result = new cl.ucn.disc.pdis.fivet.grpc.AddControlReq(this);
      result.numeroFichaMedica_ = numeroFichaMedica_;
      if (controlBuilder_ == null) {
        result.control_ = control_;
      } else {
        result.control_ = controlBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof cl.ucn.disc.pdis.fivet.grpc.AddControlReq) {
        return mergeFrom((cl.ucn.disc.pdis.fivet.grpc.AddControlReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(cl.ucn.disc.pdis.fivet.grpc.AddControlReq other) {
      if (other == cl.ucn.disc.pdis.fivet.grpc.AddControlReq.getDefaultInstance()) return this;
      if (other.getNumeroFichaMedica() != 0) {
        setNumeroFichaMedica(other.getNumeroFichaMedica());
      }
      if (other.hasControl()) {
        mergeControl(other.getControl());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      cl.ucn.disc.pdis.fivet.grpc.AddControlReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (cl.ucn.disc.pdis.fivet.grpc.AddControlReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int numeroFichaMedica_ ;
    /**
     * <code>int32 numeroFichaMedica = 1;</code>
     * @return The numeroFichaMedica.
     */
    @java.lang.Override
    public int getNumeroFichaMedica() {
      return numeroFichaMedica_;
    }
    /**
     * <code>int32 numeroFichaMedica = 1;</code>
     * @param value The numeroFichaMedica to set.
     * @return This builder for chaining.
     */
    public Builder setNumeroFichaMedica(int value) {
      
      numeroFichaMedica_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 numeroFichaMedica = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearNumeroFichaMedica() {
      
      numeroFichaMedica_ = 0;
      onChanged();
      return this;
    }

    private cl.ucn.disc.pdis.fivet.grpc.ControlEntity control_;
    private com.google.protobuf.SingleFieldBuilderV3<
        cl.ucn.disc.pdis.fivet.grpc.ControlEntity, cl.ucn.disc.pdis.fivet.grpc.ControlEntity.Builder, cl.ucn.disc.pdis.fivet.grpc.ControlEntityOrBuilder> controlBuilder_;
    /**
     * <code>.ControlEntity control = 2;</code>
     * @return Whether the control field is set.
     */
    public boolean hasControl() {
      return controlBuilder_ != null || control_ != null;
    }
    /**
     * <code>.ControlEntity control = 2;</code>
     * @return The control.
     */
    public cl.ucn.disc.pdis.fivet.grpc.ControlEntity getControl() {
      if (controlBuilder_ == null) {
        return control_ == null ? cl.ucn.disc.pdis.fivet.grpc.ControlEntity.getDefaultInstance() : control_;
      } else {
        return controlBuilder_.getMessage();
      }
    }
    /**
     * <code>.ControlEntity control = 2;</code>
     */
    public Builder setControl(cl.ucn.disc.pdis.fivet.grpc.ControlEntity value) {
      if (controlBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        control_ = value;
        onChanged();
      } else {
        controlBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.ControlEntity control = 2;</code>
     */
    public Builder setControl(
        cl.ucn.disc.pdis.fivet.grpc.ControlEntity.Builder builderForValue) {
      if (controlBuilder_ == null) {
        control_ = builderForValue.build();
        onChanged();
      } else {
        controlBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.ControlEntity control = 2;</code>
     */
    public Builder mergeControl(cl.ucn.disc.pdis.fivet.grpc.ControlEntity value) {
      if (controlBuilder_ == null) {
        if (control_ != null) {
          control_ =
            cl.ucn.disc.pdis.fivet.grpc.ControlEntity.newBuilder(control_).mergeFrom(value).buildPartial();
        } else {
          control_ = value;
        }
        onChanged();
      } else {
        controlBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.ControlEntity control = 2;</code>
     */
    public Builder clearControl() {
      if (controlBuilder_ == null) {
        control_ = null;
        onChanged();
      } else {
        control_ = null;
        controlBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.ControlEntity control = 2;</code>
     */
    public cl.ucn.disc.pdis.fivet.grpc.ControlEntity.Builder getControlBuilder() {
      
      onChanged();
      return getControlFieldBuilder().getBuilder();
    }
    /**
     * <code>.ControlEntity control = 2;</code>
     */
    public cl.ucn.disc.pdis.fivet.grpc.ControlEntityOrBuilder getControlOrBuilder() {
      if (controlBuilder_ != null) {
        return controlBuilder_.getMessageOrBuilder();
      } else {
        return control_ == null ?
            cl.ucn.disc.pdis.fivet.grpc.ControlEntity.getDefaultInstance() : control_;
      }
    }
    /**
     * <code>.ControlEntity control = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        cl.ucn.disc.pdis.fivet.grpc.ControlEntity, cl.ucn.disc.pdis.fivet.grpc.ControlEntity.Builder, cl.ucn.disc.pdis.fivet.grpc.ControlEntityOrBuilder> 
        getControlFieldBuilder() {
      if (controlBuilder_ == null) {
        controlBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            cl.ucn.disc.pdis.fivet.grpc.ControlEntity, cl.ucn.disc.pdis.fivet.grpc.ControlEntity.Builder, cl.ucn.disc.pdis.fivet.grpc.ControlEntityOrBuilder>(
                getControl(),
                getParentForChildren(),
                isClean());
        control_ = null;
      }
      return controlBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:AddControlReq)
  }

  // @@protoc_insertion_point(class_scope:AddControlReq)
  private static final cl.ucn.disc.pdis.fivet.grpc.AddControlReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new cl.ucn.disc.pdis.fivet.grpc.AddControlReq();
  }

  public static cl.ucn.disc.pdis.fivet.grpc.AddControlReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AddControlReq>
      PARSER = new com.google.protobuf.AbstractParser<AddControlReq>() {
    @java.lang.Override
    public AddControlReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AddControlReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AddControlReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AddControlReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public cl.ucn.disc.pdis.fivet.grpc.AddControlReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

