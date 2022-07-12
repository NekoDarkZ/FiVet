/*
 * Copyright 2022 Sebastian Murquio-Castillo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.pdis.fivet;

import cl.ucn.disc.pdis.fivet.grpc.*;
import cl.ucn.disc.pdis.fivet.services.FivetServiceImpl;
import com.asarkar.grpc.test.GrpcCleanupExtension;
import com.asarkar.grpc.test.Resources;
import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.StatusRuntimeException;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Iterator;

/**
 * Test for gRPC
 *
 * @author Sebastian Murquio-Castillo
 */
@Slf4j
@ExtendWith(GrpcCleanupExtension.class)
public final class TestGrpc {

    /**
     * Test the gRPC.
     */
    @Test
    public void testGrpc(Resources resources) throws IOException {
        log.debug("Starting TestGrpc ...");

        // Unique server name
        String serverName = InProcessServerBuilder.generateName();
        log.debug("Testing serverName <{}>..", serverName);

        // Initialize the server
        Server server = InProcessServerBuilder
                .forName(serverName)
                .directExecutor()
                .addService(new FivetServiceImpl("jdbc:h2:mem:fivet")).build().start();
        resources.register(server, Duration.ofSeconds(10));

        // Initialize the channel
        ManagedChannel channel = InProcessChannelBuilder.forName(serverName).directExecutor().build();
        resources.register(channel, Duration.ofSeconds(10));

        // The Stub testing
        FivetServiceGrpc.FivetServiceBlockingStub stub = FivetServiceGrpc.newBlockingStub(channel);

        // Add Persona
        log.debug("Testing AddPersona...");
        {
            // Empty
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                PersonaReply personaReply = stub.addPersona(AddPersonaReq.newBuilder()
                        .build());
                log.debug("PersonaReply: {}", personaReply);
            });

            PersonaReply personaReply1 = stub.addPersona(AddPersonaReq.newBuilder()
                            .setPersona(PersonaEntity.newBuilder()
                                    .setRut("19968295-4")
                                    .setNombre("Sebastian Murquio Castillo")
                                    .setEmail("admin@ucn.cl")
                                    .setPassword("admin123")
                                    .setDireccion("Angamos #0610")
                                    .build())
                    .build());

            log.debug("PersonaReply: {}", personaReply1);
            Assertions.assertEquals("19968295-4", personaReply1.getPersona().getRut(), "Wrong RUT");

            // With an Email already in DB
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                PersonaReply personaReply = stub.addPersona(AddPersonaReq.newBuilder()
                        .setPersona(PersonaEntity.newBuilder()
                                .setRut("19968462-0")
                                .setNombre("Vanesa Briones Ibacache")
                                .setEmail("admin@ucn.cl")
                                .setPassword("admin123")
                                .setDireccion("Angamos #0610")
                                .build())
                        .build());
                log.debug("PersonaReply: {}", personaReply);
            });

            // With a Rut already in DB
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                PersonaReply personaReply = stub.addPersona(AddPersonaReq.newBuilder()
                        .setPersona(PersonaEntity.newBuilder()
                                .setRut("19968295-4")
                                .setNombre("Sebastian Murquio Castillo")
                                .setEmail("anotheradmin@ucn.cl")
                                .setPassword("admin123")
                                .setDireccion("Angamos #0610")
                                .build())
                        .build());
                log.debug("PersonaReply: {}", personaReply);
            });
        }

        // Authenticate
        log.debug("Testing Authenticate...");
        {
            // Empty
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                PersonaReply personaReply = stub.authenticate(AuthenticationReq.newBuilder()
                        .build());
                log.debug("PersonaReply: {}", personaReply);
            });

            // No password
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
               PersonaReply personaReply = stub.authenticate(AuthenticationReq.newBuilder()
                               .setLogin("admin@ucn.cl")
                       .build());
                log.debug("PersonaReply: {}", personaReply);
            });

            // Wrong password
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                PersonaReply personaReply = stub.authenticate(AuthenticationReq.newBuilder()
                                .setLogin("admin@ucn.cl")
                                .setPassword("wrong password")
                        .build());
                log.debug("PersonaReply: {}", personaReply);
            });

            // Wrong Login
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                PersonaReply personaReply = stub.authenticate(AuthenticationReq.newBuilder()
                                .setLogin("unknown@ucn.cl")
                                .setPassword("admin123")
                        .build());
                log.debug("PersonaReply: {}", personaReply);
            });

            PersonaReply personaReply = stub.authenticate(AuthenticationReq.newBuilder()
                    .setLogin("admin@ucn.cl")
                    .setPassword("admin123")
                    .build());

            log.debug("PersonaReply: {}", personaReply);
            Assertions.assertEquals("19968295-4", personaReply.getPersona().getRut(), "Wrong RUT");
        }

        // Add FichaMedica
        log.debug("Testing AddFichaMedica...");
        {
            // Empty
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                FichaMedicaReply fichaMedicaReply = stub.addFichaMedica(AddFichaMedicaReq.newBuilder()
                        .build());
                log.debug("PersonaReply: {}", fichaMedicaReply);
            });

            FichaMedicaReply fichaMedicaReply = stub.addFichaMedica(AddFichaMedicaReq.newBuilder()
                    .setFichaMedica(FichaMedicaEntity.newBuilder()
                            .setNumero(1)
                            .setNombrePaciente("Mane")
                            .setEspecie("Gato")
                            .setFechaNacimiento("2021-11-16")
                            .setRaza("Calico")
                            .setColor("Blanco")
                            .setTipo("Normal")
                            .setSexo(SexoEntity.HEMBRA)
                            .setDuenio(PersonaEntity.newBuilder()
                                    .setRut("19968462-0")
                                    .setNombre("Vanesa Briones Ibacache")
                                    .setEmail("vanesa.briones@alumnos.ucn.cl")
                                    .setDireccion("Angamos #0610")
                                    .build())
                            .build())
                    .build());

            log.debug("FichaMedicaReply: {}", fichaMedicaReply);

            // Empty
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                FichaMedicaReply fichaMedicaReplyAlreadyExists =
                        stub.addFichaMedica(AddFichaMedicaReq.newBuilder()
                                        .setFichaMedica(FichaMedicaEntity.newBuilder()
                                                .setNumero(1)
                                                .setNombrePaciente("Mane")
                                                .setEspecie("Gato")
                                                .setFechaNacimiento("2021-11-16")
                                                .setRaza("Calico")
                                                .setColor("Blanco")
                                                .setTipo("Normal")
                                                .setSexo(SexoEntity.HEMBRA)
                                                .setDuenio(PersonaEntity.newBuilder()
                                                        .setRut("19968462-0")
                                                        .setNombre("Vanesa Briones Ibacache")
                                                        .setEmail("vanesa.briones@alumnos.ucn.cl")
                                                        .setDireccion("Angamos #0610")
                                                        .build())
                                                .build())
                        .build());
                log.debug("PersonaReply: {}", fichaMedicaReplyAlreadyExists);
            });
        }

        // Add Control
        log.debug("Testing AddControl...");
        {
            // Empty
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                FichaMedicaReply fichaMedicaReply = stub.addControl(AddControlReq.newBuilder()
                        .build());
                log.debug("PersonaReply: {}", fichaMedicaReply);
            });

            FichaMedicaReply fichaMedicaReply = stub.addControl(AddControlReq.newBuilder()
                            .setControl(ControlEntity.newBuilder()
                                    .setFecha(ZonedDateTime.now().toString())
                                    .setTemperatura(38.5)
                                    .setPeso(10.5)
                                    .setAltura(0.6)
                                    .setDiagnostico("Sin novedad")
                                    .setVeterinario(PersonaEntity.newBuilder()
                                            .setRut("19968295-4")
                                            .setNombre("Sebastian Murquio Castillo")
                                            .setEmail("sebastian.murquio@alumnos.ucn.cl")
                                            .setDireccion("Angamos #0610")
                                            .build())
                                    .build())
                            .setNumeroFichaMedica(1)
                    .build());

            log.debug("FichaMedicaReply: {}.", fichaMedicaReply);
        }

        // Testing RetrieveFichaMedica
        log.debug("Testing RetrieveFichaMedica...");
        {
            // Empty
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                FichaMedicaReply fichaMedicaReply = stub.retrieveFichaMedica(RetrieveFichaMedicaReq.newBuilder()
                        .build());
                log.debug("PersonaReply: {}", fichaMedicaReply);
            });

            // NumeroFichaMedica not found
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
               FichaMedicaReply fichaMedicaReply = stub.retrieveFichaMedica(RetrieveFichaMedicaReq.newBuilder()
                               .setNumeroFichaMedica(100)
                       .build());
                log.debug("PersonaReply: {}", fichaMedicaReply);
            });


            FichaMedicaReply fichaMedicaReply = stub.retrieveFichaMedica(RetrieveFichaMedicaReq.newBuilder()
                            .setNumeroFichaMedica(1)
                    .build());

            log.debug("FichaMedicaReply: {}.", fichaMedicaReply);
        }

        // Testing SearchFichaMedica
        log.debug("Testing SearchFichaMedica...");
        {
            // By Numero
            Iterator<FichaMedicaReply> fichaMedicaReplyIteratorNumero =
                    stub.searchFichaMedica(SearchFichaMedicaReq.newBuilder()
                                    .setQuery("1")
                            .build());

            while(fichaMedicaReplyIteratorNumero.hasNext()) {
                FichaMedicaReply fichaMedicaReply = fichaMedicaReplyIteratorNumero.next();
                log.debug("FichaMedicaReply: {}.", fichaMedicaReply.getFichaMedica());
                Assertions.assertEquals(1,
                        fichaMedicaReply.getFichaMedica().getNumero());
            }

            // By Rut Duenio
            Iterator<FichaMedicaReply> fichaMedicaReplyIteratorRut =
                    stub.searchFichaMedica(SearchFichaMedicaReq.newBuilder()
                            .setQuery("19968462-0")
                            .build());

            while(fichaMedicaReplyIteratorRut.hasNext()) {
                FichaMedicaReply fichaMedicaReply = fichaMedicaReplyIteratorRut.next();
                log.debug("FichaMedicaReply: {}.", fichaMedicaReply.getFichaMedica());
                Assertions.assertEquals("19968462-0",
                        fichaMedicaReply.getFichaMedica().getDuenio().getRut());
            }

            // By Nombre Mascota
            Iterator<FichaMedicaReply> fichaMedicaReplyIteratorMascota =
                    stub.searchFichaMedica(SearchFichaMedicaReq.newBuilder()
                            .setQuery("Mane")
                            .build());

            while(fichaMedicaReplyIteratorMascota.hasNext()) {
                FichaMedicaReply fichaMedicaReply = fichaMedicaReplyIteratorMascota.next();
                log.debug("FichaMedicaReply: {}.", fichaMedicaReply.getFichaMedica());
                Assertions.assertEquals("Mane",
                        fichaMedicaReply.getFichaMedica().getNombrePaciente());
            }

            // By Nombre Duenio
            Iterator<FichaMedicaReply> fichaMedicaReplyIteratorDuenio =
                    stub.searchFichaMedica(SearchFichaMedicaReq.newBuilder()
                            .setQuery("Vanesa Briones Ibacache")
                            .build());

            while(fichaMedicaReplyIteratorDuenio.hasNext()) {
                FichaMedicaReply fichaMedicaReply = fichaMedicaReplyIteratorDuenio.next();
                log.debug("FichaMedicaReply: {}.", fichaMedicaReply.getFichaMedica());
                Assertions.assertEquals("Vanesa Briones Ibacache",
                        fichaMedicaReply.getFichaMedica().getDuenio().getNombre());
            }

            // Not Found no numeric
            Iterator<FichaMedicaReply> fichaMedicaReplyIteratorNonNumeric
                    = stub.searchFichaMedica(SearchFichaMedicaReq.newBuilder()
                            .setQuery("a")
                            .build());

            Assertions.assertThrows(StatusRuntimeException.class,
                    fichaMedicaReplyIteratorNonNumeric::hasNext);

            // Not Found numeric
            Iterator<FichaMedicaReply> fichaMedicaReplyIteratorNumeric
                    = stub.searchFichaMedica(SearchFichaMedicaReq.newBuilder()
                    .setQuery("100")
                    .build());

            Assertions.assertThrows(StatusRuntimeException.class, fichaMedicaReplyIteratorNumeric::hasNext);

            // Empty
            Iterator<FichaMedicaReply> fichaMedicaReplyIteratorEmpty
                    = stub.searchFichaMedica(SearchFichaMedicaReq.newBuilder()
                    .build());

            Assertions.assertThrows(StatusRuntimeException.class, fichaMedicaReplyIteratorEmpty::hasNext);
        }
    }
}
