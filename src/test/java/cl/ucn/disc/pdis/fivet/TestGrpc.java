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
import cl.ucn.disc.pdis.fivet.model.FichaMedica;
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
            });

            PersonaReply personaReply1 = stub.addPersona(AddPersonaReq.newBuilder()
                            .setPersona(PersonaEntity.newBuilder()
                                    .setRut("199682954")
                                    .setNombre("Sebastian Murquio Castillo")
                                    .setEmail("admin@ucn.cl")
                                    .setPassword("admin123")
                                    .setDireccion("Angamos #0610")
                                    .build())
                    .build());

            log.debug("PersonaReply: {}", personaReply1);
            Assertions.assertEquals("199682954", personaReply1.getPersona().getRut(), "Wrong RUT");

            // With an Email already in DB
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                PersonaReply personaReply = stub.addPersona(AddPersonaReq.newBuilder()
                        .setPersona(PersonaEntity.newBuilder()
                                .setRut("199684620")
                                .setNombre("Vanesa Briones Ibacache")
                                .setEmail("admin@ucn.cl")
                                .setPassword("admin123")
                                .setDireccion("Angamos #0610")
                                .build())
                        .build());
            });

            // With a Rut already in DB
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                PersonaReply personaReply = stub.addPersona(AddPersonaReq.newBuilder()
                        .setPersona(PersonaEntity.newBuilder()
                                .setRut("199682954")
                                .setNombre("Sebastian Murquio Castillo")
                                .setEmail("anotheradmin@ucn.cl")
                                .setPassword("admin123")
                                .setDireccion("Angamos #0610")
                                .build())
                        .build());
            });
        }

        // Authenticate
        log.debug("Testing Authenticate...");
        {
            // Empty
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                PersonaReply personaReply = stub.authenticate(AuthenticationReq.newBuilder()
                        .build());
            });

            // No password
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
               PersonaReply personaReply = stub.authenticate(AuthenticationReq.newBuilder()
                               .setLogin("admin@ucn.cl")
                       .build());
            });

            // Wrong password
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                PersonaReply personaReply = stub.authenticate(AuthenticationReq.newBuilder()
                                .setLogin("admin@ucn.cl")
                                .setPassword("wrong password")
                        .build());
            });

            // Wrong Login
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                PersonaReply personaReply = stub.authenticate(AuthenticationReq.newBuilder()
                                .setLogin("unknown@ucn.cl")
                                .setPassword("admin123")
                        .build());
            });

            PersonaReply personaReply = stub.authenticate(AuthenticationReq.newBuilder()
                    .setLogin("admin@ucn.cl")
                    .setPassword("admin123")
                    .build());

            log.debug("PersonaReply: {}", personaReply);
            Assertions.assertEquals("199682954", personaReply.getPersona().getRut(), "Wrong RUT");
        }

        // Add FichaMedica
        log.debug("Testing AddFichaMedica...");
        {
            // Empty
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                FichaMedicaReply fichaMedicaReply = stub.addFichaMedica(AddFichaMedicaReq.newBuilder()
                        .build());
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
                                    .setRut("199684620")
                                    .setNombre("Vanesa Briones Ibacache")
                                    .setEmail("vanesa.briones@alumnos.ucn.cl")
                                    .setDireccion("Angamos #0610")
                                    .build())
                            .build())
                    .build());

            log.debug("FichaMedicaReply: {}", fichaMedicaReply);
        }

        // Add Control
        log.debug("Testing AddControl...");
        {
            // Empty
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                FichaMedicaReply fichaMedicaReply = stub.addControl(AddControlReq.newBuilder()
                        .build());
            });

            FichaMedicaReply fichaMedicaReply = stub.addControl(AddControlReq.newBuilder()
                            .setControl(ControlEntity.newBuilder()
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
            });

            // NumeroFichaMedica not found
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
               FichaMedicaReply fichaMedicaReply = stub.retrieveFichaMedica(RetrieveFichaMedicaReq.newBuilder()
                               .setNumeroFichaMedica(100)
                       .build());
            });


            FichaMedicaReply fichaMedicaReply = stub.retrieveFichaMedica(RetrieveFichaMedicaReq.newBuilder()
                            .setNumeroFichaMedica(1)
                    .build());

            log.debug("FichaMedicaReply: {}.", fichaMedicaReply);
        }

        // Testing SearchFichaMedica
        log.debug("Testing SearchFichaMedica...");
        {
            // Empty
            Assertions.assertThrows(StatusRuntimeException.class, () -> {
                Iterator<FichaMedicaReply> fichaMedicaReplyIterator
                        = stub.searchFichaMedica(SearchFichaMedicaReq.newBuilder()
                        .build());
            });

            // TODO: terminar test
        }
    }
}
