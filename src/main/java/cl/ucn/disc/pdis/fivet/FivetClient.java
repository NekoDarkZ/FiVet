package cl.ucn.disc.pdis.fivet;

import cl.ucn.disc.pdis.fivet.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;

/**
 * The Client of Fivet.
 *
 * @author Sebastian Murquio-Castillo
 */
@Slf4j
public final class FivetClient {

    /**
     * The Client.
     */
    public static void main(String[] args) {
        log.debug("Starting the Client...");

        // The Channel
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", 8080)
                .usePlaintext()
                .build();

        // The Stub
        FivetServiceGrpc.FivetServiceBlockingStub stub = FivetServiceGrpc.newBlockingStub(channel);

        try {
            PersonaReply persona = stub.authenticate(AuthenticateReq.newBuilder()
                            .setLogin("sebastian.murquio@alumnos.ucn.cl")
                            .setPassword("seba1234")
                    .build());
            log.debug("Persona: {}", persona);
        } catch (StatusRuntimeException e) {
            log.warn("RPC failed: {} ", e.getStatus());
        }
        log.debug("Done.");
    }
}
