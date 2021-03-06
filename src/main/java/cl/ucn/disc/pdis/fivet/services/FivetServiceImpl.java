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

package cl.ucn.disc.pdis.fivet.services;

import cl.ucn.disc.pdis.fivet.grpc.AddControlReq;
import cl.ucn.disc.pdis.fivet.grpc.AddFichaMedicaReq;
import cl.ucn.disc.pdis.fivet.grpc.AddPersonaReq;
import cl.ucn.disc.pdis.fivet.grpc.AuthenticationReq;
import cl.ucn.disc.pdis.fivet.grpc.ControlEntity;
import cl.ucn.disc.pdis.fivet.grpc.FichaMedicaEntity;
import cl.ucn.disc.pdis.fivet.grpc.FichaMedicaReply;
import cl.ucn.disc.pdis.fivet.grpc.FivetServiceGrpc;
import cl.ucn.disc.pdis.fivet.grpc.PersonaEntity;
import cl.ucn.disc.pdis.fivet.grpc.PersonaReply;
import cl.ucn.disc.pdis.fivet.grpc.RetrieveFichaMedicaReq;
import cl.ucn.disc.pdis.fivet.grpc.SearchFichaMedicaReq;
import cl.ucn.disc.pdis.fivet.model.Control;
import cl.ucn.disc.pdis.fivet.model.FichaMedica;
import cl.ucn.disc.pdis.fivet.model.ModelAdapter;
import cl.ucn.disc.pdis.fivet.model.Persona;
import com.google.protobuf.Any;
import com.google.rpc.Code;
import com.google.rpc.ErrorInfo;
import com.google.rpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * The Fivet Implementation.
 *
 * @author Sebastian Murquio-Castillo
 */
@Slf4j
public class FivetServiceImpl extends FivetServiceGrpc.FivetServiceImplBase {
    /**
     * The Controller.
     */
    private final FivetController fivetController;

    /**
     * The Fivet Service.
     *
     * @param databaseUrl to use
     */
    public FivetServiceImpl(String databaseUrl) {
        this.fivetController = new FivetControllerImpl(databaseUrl);
    }

    /**
     * Authenticate a {@link Persona}.
     *
     *  @param request credentials of Persona
     *  @param responseObserver StreamObserver of PersonaReply
     */
    @Override
    public void authenticate(AuthenticationReq request, StreamObserver<PersonaReply> responseObserver) {

        // Retrieve from Controller
        Optional<Persona> personaOptional = this.fivetController.authenticate(
                request.getLogin(),
                request.getPassword());

        personaOptional.ifPresentOrElse(persona -> {
            responseObserver.onNext(PersonaReply
                    .newBuilder()
                    .setPersona(ModelAdapter.build(persona))
                    .build());

            responseObserver.onCompleted();

        }, () -> responseObserver.onError(buildException(Code.PERMISSION_DENIED, "Wrong Credentials")));
    }

    /**
     * Add a {@link Persona} to the backend.
     *
     *  @param request Persona to add
     *  @param responseObserver StreamObserver of PersonaReply
     */
    @Override
    public void addPersona(AddPersonaReq request, StreamObserver<PersonaReply> responseObserver) {

        PersonaEntity personaEntity = request.getPersona();

        if (personaEntity.getRut().isEmpty()) {
            responseObserver.onError(buildException(Code.INVALID_ARGUMENT, "Invalid Argument"));
        } else {
            Optional<Persona> optionalPersona = this.fivetController.retrieveByLogin(personaEntity.getEmail());

            optionalPersona.ifPresentOrElse(persona ->
                    responseObserver.onError(buildException(Code.ALREADY_EXISTS, "Persona already exists")), () -> {

                    Optional<Persona> optionalPersona1 = this.fivetController.retrieveByLogin(personaEntity.getRut());

                    optionalPersona1.ifPresentOrElse(persona ->
                        responseObserver.onError(buildException(Code.ALREADY_EXISTS, "Persona already exists")), () -> {

                            Persona persona = ModelAdapter.build(personaEntity);
                            this.fivetController.add(persona, persona.getPasswd());

                            responseObserver.onNext(PersonaReply.newBuilder()
                                    .setPersona(ModelAdapter.build(persona))
                                    .build());

                            responseObserver.onCompleted();
                        });
                    });
        }
    }

    /**
     * Add a {@link Control} to the backend.
     *
     * @param request Control to add
     * @param responseObserver  StreamObserver of FichaMedicaReply
     */
    @Override
    public void addControl(AddControlReq request, StreamObserver<FichaMedicaReply> responseObserver) {

        ControlEntity controlEntity = request.getControl();

        if (controlEntity.getFecha().isEmpty()) {
            responseObserver.onError(buildException(Code.INVALID_ARGUMENT, "Invalid Argument"));
        } else {
            Optional<FichaMedica> optionalFichaMedica =
                    this.fivetController.retrieveFichaMedica(request.getNumeroFichaMedica());

            optionalFichaMedica.ifPresentOrElse(fichaMedica -> {

                Control control = ModelAdapter.build(controlEntity);

                Optional<Persona> optionalVeterinario =
                        this.fivetController.retrieveByLogin(controlEntity.getVeterinario().getRut());

                optionalVeterinario.ifPresentOrElse(veterinario -> {

                    control.setVeterinario(veterinario);
                    this.fivetController.addControl(control, fichaMedica.getNumero());

                    responseObserver.onNext(FichaMedicaReply.newBuilder()
                            .setFichaMedica(ModelAdapter.build(fichaMedica))
                            .build());

                    responseObserver.onCompleted();
                }, () -> responseObserver.onError(buildException(Code.NOT_FOUND, "Veterinario not found")));

            }, () -> responseObserver.onError(buildException(Code.NOT_FOUND, "FichaMedica not found")));
        }
    }

    /**
     * Retrieve a {@link  FichaMedica}.
     *
     * @param request numero of FichaMedica to retrieve
     * @param responseObserver StreamObserver of FichaMedicaReply
     */
    @Override
    public void retrieveFichaMedica(RetrieveFichaMedicaReq request, StreamObserver<FichaMedicaReply> responseObserver) {
        Optional<FichaMedica> optionalFichaMedica =
                this.fivetController.retrieveFichaMedica(request.getNumeroFichaMedica());

        optionalFichaMedica.ifPresentOrElse(fichaMedica -> {
            responseObserver.onNext(FichaMedicaReply.newBuilder()
                            .setFichaMedica(ModelAdapter.build(fichaMedica))
                    .build());

            responseObserver.onCompleted();

        }, () -> responseObserver.onError(buildException(Code.NOT_FOUND, "FichaMedica not found")));
    }

    /**
     * Retrieve a Iterator of {@link  FichaMedica} that match with the query.
     *
     *  @param request contains the query to use
     *  @param responseObserver StreamObserver of FichaMedicaReply
     */
    @Override
    public void searchFichaMedica(SearchFichaMedicaReq request, StreamObserver<FichaMedicaReply> responseObserver) {
        String metadata = request.getQuery();

        if (metadata.isEmpty()) {
            responseObserver.onError(buildException(Code.INVALID_ARGUMENT, "Invalid Argument"));
        } else {
            // 1. By number of fichaMedica

            if (isNumeric(metadata)) {
                Optional<FichaMedica> optionalFichaMedica =
                        this.fivetController.retrieveFichaMedica(Integer.parseInt(metadata));

                optionalFichaMedica.ifPresentOrElse(fichaMedica -> {
                    responseObserver.onNext(FichaMedicaReply.newBuilder()
                                    .setFichaMedica(ModelAdapter.build(fichaMedica))
                            .build());
                    responseObserver.onCompleted();
                }, () -> responseObserver.onError(buildException(Code.NOT_FOUND, "FichaMedica not found")));
            } else {

                int count = 0;
                List<FichaMedica> fichaMedicaList = this.fivetController.retrieveAllFichaMedica();

                for (FichaMedica fichaMedica : fichaMedicaList) {

                    // 2. By rut of Duenio

                    if (metadata.equalsIgnoreCase(fichaMedica.getDuenio().getRut())) {
                        count++;
                        responseObserver.onNext(FichaMedicaReply.newBuilder()
                                        .setFichaMedica(ModelAdapter.build(fichaMedica))
                                .build());

                        // 3. By nombre Mascota
                    } else if (metadata.equalsIgnoreCase(fichaMedica.getNombrePaciente())) {
                        count++;
                        responseObserver.onNext(FichaMedicaReply.newBuilder()
                                .setFichaMedica(ModelAdapter.build(fichaMedica))
                                .build());

                        // 4. By nombre Duenio
                    } else if (metadata.equalsIgnoreCase(fichaMedica.getDuenio().getNombre())) {
                        count++;
                        responseObserver.onNext(FichaMedicaReply.newBuilder()
                                .setFichaMedica(ModelAdapter.build(fichaMedica))
                                .build());
                    }
                }

                if (count > 0) {
                    responseObserver.onCompleted();
                } else {
                    responseObserver.onError(buildException(Code.NOT_FOUND, "FichaMedica not found"));
                }
            }
        }
    }

    /**
     * Add a {@link FichaMedica} to the backend.
     *
     *  @param request the request
     *  @param responseObserver the response
     */
    @Override
    public void addFichaMedica(AddFichaMedicaReq request, StreamObserver<FichaMedicaReply> responseObserver) {

        if (request.hasFichaMedica()) {
            FichaMedicaEntity fichaMedicaEntity = request.getFichaMedica();

            Optional<FichaMedica> optionalFichaMedica =
                    this.fivetController.retrieveFichaMedica(fichaMedicaEntity.getNumero());

            optionalFichaMedica.ifPresentOrElse(fichaMedica ->
                            responseObserver.onError(buildException(Code.ALREADY_EXISTS, "FichaMedica already exists")),
                    () -> {

                        FichaMedica fichaMedica = ModelAdapter.build(fichaMedicaEntity);

                        Optional<Persona> optionalDuenio =
                                this.fivetController.retrieveByLogin(fichaMedica.getDuenio().getRut());

                        optionalDuenio.ifPresentOrElse(fichaMedica::setDuenio, () -> {

                            this.fivetController.addPersona(fichaMedica.getDuenio());
                            Optional<Persona> duenio =
                                    this.fivetController.retrieveByLogin(fichaMedica.getDuenio().getRut());

                            duenio.ifPresent(fichaMedica::setDuenio);
                        });

                        log.debug("Duenio id: {}", fichaMedica.getDuenio().getId());

                        this.fivetController.addFichaMedica(fichaMedica);

                        responseObserver.onNext(FichaMedicaReply.newBuilder()
                                .setFichaMedica(ModelAdapter.build(fichaMedica))
                                .build());

                        responseObserver.onCompleted();
                    });
        } else {
            responseObserver.onError(buildException(Code.INVALID_ARGUMENT, "Invalid Argument"));
        }
    }

    /**
     * Check a String to convert into a Integer.
     *
     * @param str to use
     * @return boolean (0 if not numeric and 1 if is numeric)
     */
    private static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9.]+");
    }

    /**
     * Build a StatusRuntimeException with a message.
     *
     * @param code error
     * @param message to use
     * @return the {@link StatusRuntimeException}
     */
    private StatusRuntimeException buildException(final Code code, final String message) {
        return StatusProto.toStatusRuntimeException(Status.newBuilder()
                .setCode(code.getNumber())
                .setMessage(message)
                .addDetails(Any.pack(ErrorInfo.newBuilder()
                        .setReason(message)
                        .build()))
                .build());
    }
}
