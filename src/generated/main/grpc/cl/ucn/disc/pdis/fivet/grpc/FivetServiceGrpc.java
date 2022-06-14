package cl.ucn.disc.pdis.fivet.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.1)",
    comments = "Source: fivet.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FivetServiceGrpc {

  private FivetServiceGrpc() {}

  public static final String SERVICE_NAME = "FivetService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.AuthenticationReq,
      cl.ucn.disc.pdis.fivet.grpc.PersonaReply> getAuthenticateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "authenticate",
      requestType = cl.ucn.disc.pdis.fivet.grpc.AuthenticationReq.class,
      responseType = cl.ucn.disc.pdis.fivet.grpc.PersonaReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.AuthenticationReq,
      cl.ucn.disc.pdis.fivet.grpc.PersonaReply> getAuthenticateMethod() {
    io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.AuthenticationReq, cl.ucn.disc.pdis.fivet.grpc.PersonaReply> getAuthenticateMethod;
    if ((getAuthenticateMethod = FivetServiceGrpc.getAuthenticateMethod) == null) {
      synchronized (FivetServiceGrpc.class) {
        if ((getAuthenticateMethod = FivetServiceGrpc.getAuthenticateMethod) == null) {
          FivetServiceGrpc.getAuthenticateMethod = getAuthenticateMethod =
              io.grpc.MethodDescriptor.<cl.ucn.disc.pdis.fivet.grpc.AuthenticationReq, cl.ucn.disc.pdis.fivet.grpc.PersonaReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "authenticate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cl.ucn.disc.pdis.fivet.grpc.AuthenticationReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cl.ucn.disc.pdis.fivet.grpc.PersonaReply.getDefaultInstance()))
              .setSchemaDescriptor(new FivetServiceMethodDescriptorSupplier("authenticate"))
              .build();
        }
      }
    }
    return getAuthenticateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.AddPersonaReq,
      cl.ucn.disc.pdis.fivet.grpc.PersonaReply> getAddPersonaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addPersona",
      requestType = cl.ucn.disc.pdis.fivet.grpc.AddPersonaReq.class,
      responseType = cl.ucn.disc.pdis.fivet.grpc.PersonaReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.AddPersonaReq,
      cl.ucn.disc.pdis.fivet.grpc.PersonaReply> getAddPersonaMethod() {
    io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.AddPersonaReq, cl.ucn.disc.pdis.fivet.grpc.PersonaReply> getAddPersonaMethod;
    if ((getAddPersonaMethod = FivetServiceGrpc.getAddPersonaMethod) == null) {
      synchronized (FivetServiceGrpc.class) {
        if ((getAddPersonaMethod = FivetServiceGrpc.getAddPersonaMethod) == null) {
          FivetServiceGrpc.getAddPersonaMethod = getAddPersonaMethod =
              io.grpc.MethodDescriptor.<cl.ucn.disc.pdis.fivet.grpc.AddPersonaReq, cl.ucn.disc.pdis.fivet.grpc.PersonaReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addPersona"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cl.ucn.disc.pdis.fivet.grpc.AddPersonaReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cl.ucn.disc.pdis.fivet.grpc.PersonaReply.getDefaultInstance()))
              .setSchemaDescriptor(new FivetServiceMethodDescriptorSupplier("addPersona"))
              .build();
        }
      }
    }
    return getAddPersonaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.AddControlReq,
      cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> getAddControlMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addControl",
      requestType = cl.ucn.disc.pdis.fivet.grpc.AddControlReq.class,
      responseType = cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.AddControlReq,
      cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> getAddControlMethod() {
    io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.AddControlReq, cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> getAddControlMethod;
    if ((getAddControlMethod = FivetServiceGrpc.getAddControlMethod) == null) {
      synchronized (FivetServiceGrpc.class) {
        if ((getAddControlMethod = FivetServiceGrpc.getAddControlMethod) == null) {
          FivetServiceGrpc.getAddControlMethod = getAddControlMethod =
              io.grpc.MethodDescriptor.<cl.ucn.disc.pdis.fivet.grpc.AddControlReq, cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addControl"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cl.ucn.disc.pdis.fivet.grpc.AddControlReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply.getDefaultInstance()))
              .setSchemaDescriptor(new FivetServiceMethodDescriptorSupplier("addControl"))
              .build();
        }
      }
    }
    return getAddControlMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.RetrieveFichaMedicaReq,
      cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> getRetrieveFichaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "retrieveFicha",
      requestType = cl.ucn.disc.pdis.fivet.grpc.RetrieveFichaMedicaReq.class,
      responseType = cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.RetrieveFichaMedicaReq,
      cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> getRetrieveFichaMethod() {
    io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.RetrieveFichaMedicaReq, cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> getRetrieveFichaMethod;
    if ((getRetrieveFichaMethod = FivetServiceGrpc.getRetrieveFichaMethod) == null) {
      synchronized (FivetServiceGrpc.class) {
        if ((getRetrieveFichaMethod = FivetServiceGrpc.getRetrieveFichaMethod) == null) {
          FivetServiceGrpc.getRetrieveFichaMethod = getRetrieveFichaMethod =
              io.grpc.MethodDescriptor.<cl.ucn.disc.pdis.fivet.grpc.RetrieveFichaMedicaReq, cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "retrieveFicha"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cl.ucn.disc.pdis.fivet.grpc.RetrieveFichaMedicaReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply.getDefaultInstance()))
              .setSchemaDescriptor(new FivetServiceMethodDescriptorSupplier("retrieveFicha"))
              .build();
        }
      }
    }
    return getRetrieveFichaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.SearchFichaMedicaReq,
      cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> getSearchFichaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "searchFicha",
      requestType = cl.ucn.disc.pdis.fivet.grpc.SearchFichaMedicaReq.class,
      responseType = cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.SearchFichaMedicaReq,
      cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> getSearchFichaMethod() {
    io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.SearchFichaMedicaReq, cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> getSearchFichaMethod;
    if ((getSearchFichaMethod = FivetServiceGrpc.getSearchFichaMethod) == null) {
      synchronized (FivetServiceGrpc.class) {
        if ((getSearchFichaMethod = FivetServiceGrpc.getSearchFichaMethod) == null) {
          FivetServiceGrpc.getSearchFichaMethod = getSearchFichaMethod =
              io.grpc.MethodDescriptor.<cl.ucn.disc.pdis.fivet.grpc.SearchFichaMedicaReq, cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "searchFicha"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cl.ucn.disc.pdis.fivet.grpc.SearchFichaMedicaReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply.getDefaultInstance()))
              .setSchemaDescriptor(new FivetServiceMethodDescriptorSupplier("searchFicha"))
              .build();
        }
      }
    }
    return getSearchFichaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.AddFichaMedicaReq,
      cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> getAddFichaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addFicha",
      requestType = cl.ucn.disc.pdis.fivet.grpc.AddFichaMedicaReq.class,
      responseType = cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.AddFichaMedicaReq,
      cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> getAddFichaMethod() {
    io.grpc.MethodDescriptor<cl.ucn.disc.pdis.fivet.grpc.AddFichaMedicaReq, cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> getAddFichaMethod;
    if ((getAddFichaMethod = FivetServiceGrpc.getAddFichaMethod) == null) {
      synchronized (FivetServiceGrpc.class) {
        if ((getAddFichaMethod = FivetServiceGrpc.getAddFichaMethod) == null) {
          FivetServiceGrpc.getAddFichaMethod = getAddFichaMethod =
              io.grpc.MethodDescriptor.<cl.ucn.disc.pdis.fivet.grpc.AddFichaMedicaReq, cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addFicha"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cl.ucn.disc.pdis.fivet.grpc.AddFichaMedicaReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply.getDefaultInstance()))
              .setSchemaDescriptor(new FivetServiceMethodDescriptorSupplier("addFicha"))
              .build();
        }
      }
    }
    return getAddFichaMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FivetServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FivetServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FivetServiceStub>() {
        @java.lang.Override
        public FivetServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FivetServiceStub(channel, callOptions);
        }
      };
    return FivetServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FivetServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FivetServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FivetServiceBlockingStub>() {
        @java.lang.Override
        public FivetServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FivetServiceBlockingStub(channel, callOptions);
        }
      };
    return FivetServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FivetServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FivetServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FivetServiceFutureStub>() {
        @java.lang.Override
        public FivetServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FivetServiceFutureStub(channel, callOptions);
        }
      };
    return FivetServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class FivetServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void authenticate(cl.ucn.disc.pdis.fivet.grpc.AuthenticationReq request,
        io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.PersonaReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAuthenticateMethod(), responseObserver);
    }

    /**
     */
    public void addPersona(cl.ucn.disc.pdis.fivet.grpc.AddPersonaReq request,
        io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.PersonaReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddPersonaMethod(), responseObserver);
    }

    /**
     */
    public void addControl(cl.ucn.disc.pdis.fivet.grpc.AddControlReq request,
        io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddControlMethod(), responseObserver);
    }

    /**
     */
    public void retrieveFicha(cl.ucn.disc.pdis.fivet.grpc.RetrieveFichaMedicaReq request,
        io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRetrieveFichaMethod(), responseObserver);
    }

    /**
     */
    public void searchFicha(cl.ucn.disc.pdis.fivet.grpc.SearchFichaMedicaReq request,
        io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchFichaMethod(), responseObserver);
    }

    /**
     */
    public void addFicha(cl.ucn.disc.pdis.fivet.grpc.AddFichaMedicaReq request,
        io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddFichaMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAuthenticateMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cl.ucn.disc.pdis.fivet.grpc.AuthenticationReq,
                cl.ucn.disc.pdis.fivet.grpc.PersonaReply>(
                  this, METHODID_AUTHENTICATE)))
          .addMethod(
            getAddPersonaMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cl.ucn.disc.pdis.fivet.grpc.AddPersonaReq,
                cl.ucn.disc.pdis.fivet.grpc.PersonaReply>(
                  this, METHODID_ADD_PERSONA)))
          .addMethod(
            getAddControlMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cl.ucn.disc.pdis.fivet.grpc.AddControlReq,
                cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply>(
                  this, METHODID_ADD_CONTROL)))
          .addMethod(
            getRetrieveFichaMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cl.ucn.disc.pdis.fivet.grpc.RetrieveFichaMedicaReq,
                cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply>(
                  this, METHODID_RETRIEVE_FICHA)))
          .addMethod(
            getSearchFichaMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                cl.ucn.disc.pdis.fivet.grpc.SearchFichaMedicaReq,
                cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply>(
                  this, METHODID_SEARCH_FICHA)))
          .addMethod(
            getAddFichaMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                cl.ucn.disc.pdis.fivet.grpc.AddFichaMedicaReq,
                cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply>(
                  this, METHODID_ADD_FICHA)))
          .build();
    }
  }

  /**
   */
  public static final class FivetServiceStub extends io.grpc.stub.AbstractAsyncStub<FivetServiceStub> {
    private FivetServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FivetServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FivetServiceStub(channel, callOptions);
    }

    /**
     */
    public void authenticate(cl.ucn.disc.pdis.fivet.grpc.AuthenticationReq request,
        io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.PersonaReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAuthenticateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addPersona(cl.ucn.disc.pdis.fivet.grpc.AddPersonaReq request,
        io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.PersonaReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddPersonaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addControl(cl.ucn.disc.pdis.fivet.grpc.AddControlReq request,
        io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddControlMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void retrieveFicha(cl.ucn.disc.pdis.fivet.grpc.RetrieveFichaMedicaReq request,
        io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRetrieveFichaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void searchFicha(cl.ucn.disc.pdis.fivet.grpc.SearchFichaMedicaReq request,
        io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSearchFichaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addFicha(cl.ucn.disc.pdis.fivet.grpc.AddFichaMedicaReq request,
        io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddFichaMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FivetServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<FivetServiceBlockingStub> {
    private FivetServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FivetServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FivetServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public cl.ucn.disc.pdis.fivet.grpc.PersonaReply authenticate(cl.ucn.disc.pdis.fivet.grpc.AuthenticationReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAuthenticateMethod(), getCallOptions(), request);
    }

    /**
     */
    public cl.ucn.disc.pdis.fivet.grpc.PersonaReply addPersona(cl.ucn.disc.pdis.fivet.grpc.AddPersonaReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddPersonaMethod(), getCallOptions(), request);
    }

    /**
     */
    public cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply addControl(cl.ucn.disc.pdis.fivet.grpc.AddControlReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddControlMethod(), getCallOptions(), request);
    }

    /**
     */
    public cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply retrieveFicha(cl.ucn.disc.pdis.fivet.grpc.RetrieveFichaMedicaReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRetrieveFichaMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> searchFicha(
        cl.ucn.disc.pdis.fivet.grpc.SearchFichaMedicaReq request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSearchFichaMethod(), getCallOptions(), request);
    }

    /**
     */
    public cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply addFicha(cl.ucn.disc.pdis.fivet.grpc.AddFichaMedicaReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddFichaMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FivetServiceFutureStub extends io.grpc.stub.AbstractFutureStub<FivetServiceFutureStub> {
    private FivetServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FivetServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FivetServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cl.ucn.disc.pdis.fivet.grpc.PersonaReply> authenticate(
        cl.ucn.disc.pdis.fivet.grpc.AuthenticationReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAuthenticateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cl.ucn.disc.pdis.fivet.grpc.PersonaReply> addPersona(
        cl.ucn.disc.pdis.fivet.grpc.AddPersonaReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddPersonaMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> addControl(
        cl.ucn.disc.pdis.fivet.grpc.AddControlReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddControlMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> retrieveFicha(
        cl.ucn.disc.pdis.fivet.grpc.RetrieveFichaMedicaReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRetrieveFichaMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply> addFicha(
        cl.ucn.disc.pdis.fivet.grpc.AddFichaMedicaReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddFichaMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AUTHENTICATE = 0;
  private static final int METHODID_ADD_PERSONA = 1;
  private static final int METHODID_ADD_CONTROL = 2;
  private static final int METHODID_RETRIEVE_FICHA = 3;
  private static final int METHODID_SEARCH_FICHA = 4;
  private static final int METHODID_ADD_FICHA = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FivetServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FivetServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AUTHENTICATE:
          serviceImpl.authenticate((cl.ucn.disc.pdis.fivet.grpc.AuthenticationReq) request,
              (io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.PersonaReply>) responseObserver);
          break;
        case METHODID_ADD_PERSONA:
          serviceImpl.addPersona((cl.ucn.disc.pdis.fivet.grpc.AddPersonaReq) request,
              (io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.PersonaReply>) responseObserver);
          break;
        case METHODID_ADD_CONTROL:
          serviceImpl.addControl((cl.ucn.disc.pdis.fivet.grpc.AddControlReq) request,
              (io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply>) responseObserver);
          break;
        case METHODID_RETRIEVE_FICHA:
          serviceImpl.retrieveFicha((cl.ucn.disc.pdis.fivet.grpc.RetrieveFichaMedicaReq) request,
              (io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply>) responseObserver);
          break;
        case METHODID_SEARCH_FICHA:
          serviceImpl.searchFicha((cl.ucn.disc.pdis.fivet.grpc.SearchFichaMedicaReq) request,
              (io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply>) responseObserver);
          break;
        case METHODID_ADD_FICHA:
          serviceImpl.addFicha((cl.ucn.disc.pdis.fivet.grpc.AddFichaMedicaReq) request,
              (io.grpc.stub.StreamObserver<cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FivetServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FivetServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return cl.ucn.disc.pdis.fivet.grpc.Fivet.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FivetService");
    }
  }

  private static final class FivetServiceFileDescriptorSupplier
      extends FivetServiceBaseDescriptorSupplier {
    FivetServiceFileDescriptorSupplier() {}
  }

  private static final class FivetServiceMethodDescriptorSupplier
      extends FivetServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FivetServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FivetServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FivetServiceFileDescriptorSupplier())
              .addMethod(getAuthenticateMethod())
              .addMethod(getAddPersonaMethod())
              .addMethod(getAddControlMethod())
              .addMethod(getRetrieveFichaMethod())
              .addMethod(getSearchFichaMethod())
              .addMethod(getAddFichaMethod())
              .build();
        }
      }
    }
    return result;
  }
}
