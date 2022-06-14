package cl.ucn.disc.pdis.fivet;

import cl.ucn.disc.pdis.fivet.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZonedDateTime;

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

        // Persona request
        /*
        try {
            PersonaReply persona = stub.authenticate(AuthenticationReq.newBuilder()
                            .setLogin("sebastian.murquio@alumnos.ucn.cl")
                            .setPassword("seba1234")
                    .build());
            log.debug("Persona: {}", persona);
        } catch (StatusRuntimeException e) {
            log.warn("RPC failed: {} ", e.getStatus());
        }
        */

        // Add Control request
        try {
            PersonaReply persona = stub.addPersona(AddPersonaReq.newBuilder()
                    .setPersona(PersonaEntity.newBuilder()
                            .setRut("199682954")
                            .setNombre("Sebastian Murquio Castillo")
                            .setEmail("sebastian.murquio@alumnos.ucn.cl")
                            .setDireccion("Angamos #0610")
                            .build())
                    .build());
            log.debug("Persona: {}", persona.getPersona());

            FichaMedicaReply fichaMedica = stub.addFicha(AddFichaMedicaReq.newBuilder()
                            .setFichaMedica(FichaMedicaEntity.newBuilder()
                                    .setNumero(1)
                                    .setNombrePaciente("Fofi")
                                    .setEspecie("Gato")
                                    .setFechaNacimiento("2011-11-01")
                                    .setRaza("Calico")
                                    .setColor("Gris")
                                    .setTipo("Normal")
                                    .setSexo(SexoEntity.HEMBRA)
                                    .setDuenio(PersonaEntity.newBuilder()
                                            .setRut("199682954")
                                            .setNombre("Sebastian Murquio Castillo")
                                            .setEmail("sebastian.murquio@alumnos.ucn.cl")
                                            .setDireccion("Angamos #0610")
                                            .build())
                                    .build())
                    .build());
            log.debug("FichaMedica addFicha: {}", fichaMedica.getFichaMedica());

            ControlEntity controlEntity = ControlEntity.newBuilder()
                    .setFecha(ZonedDateTime.now().toString())
                    .setTemperatura(38.5)
                    .setPeso(10.5)
                    .setAltura(0.6)
                    .setDiagnostico("Sin novedad")
                    .setVeterinario(PersonaEntity.newBuilder()
                            .setRut("199682954")
                            .setNombre("Sebastian Murquio Castillo")
                            .setEmail("sebastian.murquio@alumnos.ucn.cl")
                            .setDireccion("Angamos #0610")
                            .build())
                    .build();

            FichaMedicaReply fichaMedicaReply = stub.addControl(AddControlReq.newBuilder()
                    .setControl(controlEntity)
                    .setNumeroFichaMedica(1)
                    .build());
            log.debug("FichaMedica addControl: {}", fichaMedicaReply.getFichaMedica());

        } catch (StatusRuntimeException e) {
            log.warn("RPC failed: {}", e.getStatus());
        }

        log.debug("Done.");
    }
}
