package cl.ucn.disc.pdis.fivet;

import cl.ucn.disc.pdis.fivet.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;

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
        try {
            PersonaReply persona = stub.addPersona(AddPersonaReq.newBuilder()
                    .setPersona(PersonaEntity.newBuilder()
                            .setRut("199682954")
                            .setNombre("Sebastian Murquio Castillo")
                            .setEmail("sebastian.murquio@alumnos.ucn.cl")
                            .setDireccion("Angamos #0610")
                            .setPassword("seba1234")
                            .build())
                    .build());
            log.debug("Persona from AddPersona: {}", persona.getPersona());

            PersonaReply personaAuth = stub.authenticate(AuthenticationReq.newBuilder()
                            .setLogin("sebastian.murquio@alumnos.ucn.cl")
                            .setPassword("seba1234")
                    .build());
            log.debug("Persona from Authenticate: {}", personaAuth);
        } catch (StatusRuntimeException e) {
            log.warn("RPC failed: {} ", e.getStatus());
        }

        // Add FichaMedica request

        try {
            FichaMedicaReply fichaMedica = stub.addFichaMedica(AddFichaMedicaReq.newBuilder()
                    .setFichaMedica(FichaMedicaEntity.newBuilder()
                            .setNumero(1)
                            .setNombrePaciente("Fofi")
                            .setEspecie("Gato")
                            .setFechaNacimiento("2011-11-01")
                            .setRaza("Calico")
                            .setColor("Gris")
                            .setTipo("Normal")
                            .setSexo(SexoEntity.HEMBRA)
                            .setDuenio(stub.retrievePersona(RetrievePersonaReq.newBuilder()
                                    .setLogin("199682954")
                                    .build()).getPersona())
                            .build())
                    .build());
            log.debug("FichaMedica from AddFicha: {}", fichaMedica.getFichaMedica());
        } catch (StatusRuntimeException e) {
            log.warn("RPC failed: {}", e.getStatus());
        }

        // Add Control request
        try {
            ControlEntity controlEntity = ControlEntity.newBuilder()
                    .setFecha(ZonedDateTime.now().toString())
                    .setTemperatura(38.5)
                    .setPeso(10.5)
                    .setAltura(0.6)
                    .setDiagnostico("Sin novedad")
                    .setVeterinario(stub.retrievePersona(RetrievePersonaReq.newBuilder()
                                    .setLogin("199682954")
                            .build()).getPersona())
                    .setFichaMedica(stub.retrieveFichaMedica(RetrieveFichaMedicaReq.newBuilder()
                                    .setNumeroFichaMedica(1).build()).getFichaMedica())
                    .build();

            FichaMedicaReply fichaMedicaReply = stub.addControl(AddControlReq.newBuilder()
                    .setControl(controlEntity)
                    .setNumeroFichaMedica(1)
                    .build());
            log.debug("FichaMedica from AddControl: {}", fichaMedicaReply.getFichaMedica());

        } catch (StatusRuntimeException e) {
            log.warn("RPC failed: {}", e.getStatus());
        }

        // Retrieve FichaMedica request
        try {
            FichaMedicaReply fichaMedica = stub.retrieveFichaMedica(RetrieveFichaMedicaReq.newBuilder()
                            .setNumeroFichaMedica(1)
                    .build());
            log.debug("FichaMedica from RetrieveFichaMedica: {}", fichaMedica.getFichaMedica());
        } catch (StatusRuntimeException e) {
            log.warn("RPC failed: {}", e.getStatus());
        }

        log.debug("Done.");
    }
}
