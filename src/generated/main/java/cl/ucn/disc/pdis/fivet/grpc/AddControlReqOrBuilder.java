// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fivet.proto

package cl.ucn.disc.pdis.fivet.grpc;

public interface AddControlReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:AddControlReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 numeroFichaMedica = 1;</code>
   * @return The numeroFichaMedica.
   */
  int getNumeroFichaMedica();

  /**
   * <code>.ControlEntity control = 2;</code>
   * @return Whether the control field is set.
   */
  boolean hasControl();
  /**
   * <code>.ControlEntity control = 2;</code>
   * @return The control.
   */
  cl.ucn.disc.pdis.fivet.grpc.ControlEntity getControl();
  /**
   * <code>.ControlEntity control = 2;</code>
   */
  cl.ucn.disc.pdis.fivet.grpc.ControlEntityOrBuilder getControlOrBuilder();
}
