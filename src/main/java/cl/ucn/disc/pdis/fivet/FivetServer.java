/*
 * Copyright 2022 Sebastian Murquio Castillo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


package cl.ucn.disc.pdis.fivet;

import cl.ucn.disc.pdis.fivet.grpc.*;
import cl.ucn.disc.pdis.fivet.model.Control;
import cl.ucn.disc.pdis.fivet.model.FichaMedica;
import cl.ucn.disc.pdis.fivet.model.Persona;
import cl.ucn.disc.pdis.fivet.services.FivetController;
import cl.ucn.disc.pdis.fivet.services.FivetControllerImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Optional;

/**
 * The Server of FiVet.
 *
 * @author Sebastian Murquio-Castillo
 */
@Slf4j
public final class FivetServer {

    /**
     * The Main
     */
    @SneakyThrows
    public static void main(String[] args) {

        log.debug("Building the FivetServiceImpl ...");
        FivetServiceImpl fivetService = new FivetServiceImpl("jdbc:sqlite:fivet.db");

        log.debug("Building and Starting The Server ...");

        // Build and Start the Server
        Server server = ServerBuilder.forPort(8080)
                .addService(fivetService)
                .build()
                .start();

        // Awaits
        server.awaitTermination();

        log.debug("Done.");
    }

    /**
     * The Fivet Implementation
     */
    @Slf4j
    private static class FivetServiceImpl extends FivetServiceGrpc.FivetServiceImplBase {

        /**
         * The Controller
         */
        private final FivetController fivetController;

        /**
         * The Fivet Service
         *
         * @param databaseUrl to use
         */
        public FivetServiceImpl(String databaseUrl) {
            this.fivetController = new FivetControllerImpl(databaseUrl);
        }

        /**
         * Authenticate
         * @param request credentials of Persona
         * @param responseObserver StreamObserver of Persona
         */
        @Override
        public void authenticate(AuthenticationReq request, StreamObserver<PersonaReply> responseObserver) {

            // Retrieve from Controller
            Optional<cl.ucn.disc.pdis.fivet.model.Persona> persona2 = this.fivetController.retrieveByLogin(
                    request.getLogin());
            if (persona2.isPresent()) {
                // Return the observer
                responseObserver.onNext(PersonaReply.newBuilder()
                        .setPersona(PersonaEntity.newBuilder()
                                .setRut(persona2.get().getRut())
                                .setNombre(persona2.get().getNombre())
                                .setEmail(persona2.get().getEmail())
                                .setDireccion(persona2.get().getDireccion())
                                .build())
                        .build());
                responseObserver.onCompleted();
            } else {
                responseObserver.onNext(PersonaReply.newBuilder()
                        .setPersona(PersonaEntity.newBuilder()
                                .setRut("199682954")
                                .setNombre("Sebastian Murquio Castillo")
                                .setEmail("sebastian.murquio@alumnos.ucn.cl")
                                .setDireccion("Angamos #0610")
                                .build())
                        .build());
                responseObserver.onCompleted();
            }
        }

        /**
         * Add a Persona
         * @param request the request
         * @param responseObserver the response
         */
        @Override
        public void addPersona(AddPersonaReq request, StreamObserver<PersonaReply> responseObserver) {

            PersonaEntity personaEntity = request.getPersona();

            Optional<Persona> optionalPersona = this.fivetController.retrieveByLogin(personaEntity.getRut());

            if (optionalPersona.isPresent()) {
                log.warn("Persona already exists");

                responseObserver.onNext(PersonaReply.newBuilder()
                                .setPersona(PersonaEntity.newBuilder()
                                        .setRut(optionalPersona.get().getRut())
                                        .setNombre(optionalPersona.get().getNombre())
                                        .setEmail(optionalPersona.get().getEmail())
                                        .setDireccion(optionalPersona.get().getDireccion())
                                        .build())
                        .build());
                responseObserver.onCompleted();
            } else {
                cl.ucn.disc.pdis.fivet.model.Persona persona = Persona.builder()
                        .rut(personaEntity.getRut())
                        .nombre(personaEntity.getNombre())
                        .email(personaEntity.getEmail())
                        .direccion(personaEntity.getDireccion())
                        .build();

                this.fivetController.addPersona(persona);

                responseObserver.onNext(PersonaReply.newBuilder()
                                .setPersona(PersonaEntity.newBuilder()
                                        .setRut(persona.getRut())
                                        .setNombre(persona.getNombre())
                                        .setEmail(persona.getEmail())
                                        .setDireccion(persona.getDireccion())
                                        .build())
                        .build());

                responseObserver.onCompleted();
            }
        }

        /**
         * Add FichaMedica
         * @param request the request
         * @param responseObserver the response
         */
        @Override
        public void addFicha(AddFichaMedicaReq request, StreamObserver<FichaMedicaReply> responseObserver) {
            FichaMedicaEntity fichaMedicaEntity = request.getFichaMedica();

            cl.ucn.disc.pdis.fivet.model.FichaMedica fichaMedica = FichaMedica.builder()
                    .numero(fichaMedicaEntity.getNumero())
                    .nombrePaciente(fichaMedicaEntity.getNombrePaciente())
                    .especie(fichaMedicaEntity.getEspecie())
                    .fechaNacimiento(LocalDate.parse(fichaMedicaEntity.getFechaNacimiento()))
                    .raza(fichaMedicaEntity.getRaza())
                    .color(fichaMedicaEntity.getColor())
                    .tipo(fichaMedicaEntity.getTipo())
                    .sexo(FichaMedica.Sexo.valueOf(fichaMedicaEntity.getSexo().name()))
                    .controles(Collections.emptyList())
                    .build();

            fichaMedica.setDuenio(Persona.builder()
                            .rut(fichaMedicaEntity.getDuenio().getRut())
                            .nombre(fichaMedicaEntity.getDuenio().getNombre())
                            .email(fichaMedicaEntity.getDuenio().getEmail())
                            .direccion(fichaMedicaEntity.getDuenio().getDireccion())
                    .build());

            this.fivetController.addFichaMedica(fichaMedica);

            responseObserver.onNext(FichaMedicaReply.newBuilder()
                            .setFichaMedica(FichaMedicaEntity.newBuilder()
                                    .setNumero(fichaMedica.getNumero())
                                    .setNombrePaciente(fichaMedica.getNombrePaciente())
                                    .setEspecie(fichaMedica.getEspecie())
                                    .setFechaNacimiento(fichaMedica.getFechaNacimiento().toString())
                                    .setRaza(fichaMedica.getRaza())
                                    .setColor(fichaMedica.getColor())
                                    .setTipo(fichaMedica.getTipo())
                                    .setSexo(SexoEntity.valueOf(fichaMedica.getSexo().toString()))
                                    .setDuenio(PersonaEntity.newBuilder()
                                            .setRut(fichaMedica.getDuenio().getRut())
                                            .setNombre(fichaMedica.getDuenio().getNombre())
                                            .setEmail(fichaMedica.getDuenio().getEmail())
                                            .setDireccion(fichaMedica.getDuenio().getDireccion())
                                            .build())
                                    .addControles(ControlEntity.newBuilder()
                                            .build())
                                    .build())
                    .build());

            responseObserver.onCompleted();
        }

        /**
         * Add Control
         * @param request the request
         * @param responseObserver  the responseObserver
         */
        @Override
        public void addControl(AddControlReq request, StreamObserver<FichaMedicaReply> responseObserver) {

            //TODO: fix addControl

            ControlEntity controlEntity = request.getControl();

            Optional<cl.ucn.disc.pdis.fivet.model.FichaMedica> optionalFichaMedica =
                    this.fivetController.retrieveFichaMedica(request.getNumeroFichaMedica());

            if (optionalFichaMedica.isPresent()) {
               FichaMedica fichaMedica = optionalFichaMedica.get();

               log.debug("Duenio 1: {}", this.fivetController.retrieveByLogin(fichaMedica.getDuenio().getRut()));

                cl.ucn.disc.pdis.fivet.model.Control control = Control.builder()
                        .fecha(ZonedDateTime.parse(controlEntity.getFecha(), DateTimeFormatter.ISO_ZONED_DATE_TIME))
                        .temperatura(controlEntity.getTemperatura())
                        .peso(controlEntity.getPeso())
                        .altura(controlEntity.getAltura())
                        .diagnostico(controlEntity.getDiagnostico())
                        .veterinario(Persona.builder()
                                .rut(controlEntity.getVeterinario().getRut())
                                .nombre(controlEntity.getVeterinario().getNombre())
                                .email(controlEntity.getVeterinario().getEmail())
                                .direccion(controlEntity.getVeterinario().getDireccion())
                                .build())
                        .fichaMedica(fichaMedica)
                        .build();

                this.fivetController.addControl(control, fichaMedica.getNumero());

                log.debug("Duenio: {}", control.getFichaMedica().getDuenio().getRut());

                responseObserver.onNext(FichaMedicaReply.newBuilder()
                        .setFichaMedica(FichaMedicaEntity.newBuilder()
                                .setNumero(fichaMedica.getNumero())
                                .setNombrePaciente(fichaMedica.getNombrePaciente())
                                .setEspecie(fichaMedica.getEspecie())
                                .setFechaNacimiento(fichaMedica.getFechaNacimiento().toString())
                                .setRaza(fichaMedica.getRaza())
                                .setColor(fichaMedica.getColor())
                                .setTipo(fichaMedica.getTipo())
                                .setSexo(SexoEntity.valueOf(fichaMedica.getSexo().toString()))
                                .setDuenio(PersonaEntity.newBuilder()
                                        .setRut(fichaMedica.getDuenio().getRut())
                                        .setNombre(fichaMedica.getDuenio().getNombre())
                                        .setEmail(fichaMedica.getDuenio().getEmail())
                                        .setDireccion(fichaMedica.getDuenio().getDireccion())
                                        .build())
                                .addControles(controlEntity)
                                .build())
                        .build());
                responseObserver.onCompleted();
            } else {
                responseObserver.onNext(FichaMedicaReply.newBuilder()
                        .setFichaMedica(FichaMedicaEntity.newBuilder()
                                .setNumero(0)
                                .setNombrePaciente("NoNombre")
                                .setEspecie("NoEspecie")
                                .setFechaNacimiento("1998-10-31")
                                .setRaza("NoRaza")
                                .setColor("NoColor")
                                .setTipo("NoTipo")
                                .setSexo(SexoEntity.UNDEFINED)
                                .setDuenio(PersonaEntity.newBuilder()
                                        .setRut("199682954")
                                        .setNombre("Sebastian Murquio Castillo")
                                        .setEmail("sebastian.murquio@alumnos.ucn.cl")
                                        .setDireccion("Angamos #0610")
                                        .build())
                                .build())
                        .build());
                responseObserver.onCompleted();
            }
        }
    }
}
