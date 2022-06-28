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

package cl.ucn.disc.pdis.fivet.model;

import cl.ucn.disc.pdis.fivet.grpc.*;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The Model Converter.
 *
 * @author Sebastian Murquio-Castillo
 */
@UtilityClass
public final class ModelAdapter {

    /**
     * Build a {@link PersonaEntity} from {@link Persona}
     *
     * @param persona to use
     * @return PersonaEntity
     */
    public static PersonaEntity build(final Persona persona) {
        return PersonaEntity.newBuilder()
                .setRut(persona.getRut())
                .setNombre(persona.getNombre())
                .setEmail(persona.getEmail())
                .setDireccion(persona.getDireccion())
                .setPassword(persona.getPasswd())
                .build();
    }

    /**
     * Build a {@link Persona} from {@link PersonaEntity}
     *
     * @param personaEntity to use
     * @return Persona
     */
    public static Persona build(final PersonaEntity personaEntity) {
        return Persona.builder()
                .rut(personaEntity.getRut())
                .nombre(personaEntity.getNombre())
                .email(personaEntity.getEmail())
                .direccion(personaEntity.getDireccion())
                .passwd(personaEntity.getPassword())
                .build();
    }

    /**
     * Build a {@link Control} from {@link ControlEntity}
     *
     * @param controlEntity to use
     * @return Control
     */
    public static Control build(final ControlEntity controlEntity) {
        return Control.builder()
                .altura(controlEntity.getAltura())
                .diagnostico(controlEntity.getDiagnostico())
                .veterinario(build(controlEntity.getVeterinario()))
                .peso(controlEntity.getPeso())
                .fecha(build(controlEntity.getFecha()))
                .temperatura(controlEntity.getTemperatura())
                .build();
    }

    /**
     * Build a {@link ControlEntity} from {@link Control}
     *
     * @param control to use
     * @return ControlEntity
     */
    public static ControlEntity build(final Control control) {
        return ControlEntity.newBuilder()
                .setAltura(control.getAltura())
                .setDiagnostico(control.getDiagnostico())
                .setVeterinario(build(control.getVeterinario()))
                .setPeso(control.getPeso())
                .setFecha(control.getFecha().toString())
                .setTemperatura(control.getTemperatura())
                .build();
    }

    /**
     * Build a {@link ZonedDateTime} from String fecha
     *
     * @param fecha to use
     * @return ZonedDateTime
     */
    public static ZonedDateTime build(final String fecha) {
        return ZonedDateTime.parse(fecha, DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    /**
     * Build a {@link LocalDate} from String fechaNacimiento
     *
     * @param fechaNacimiento to use
     * @return LocalDate
     */
    public static LocalDate buildLocalDate(final String fechaNacimiento) {
        return LocalDate.parse(fechaNacimiento, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Build a {@link cl.ucn.disc.pdis.fivet.model.FichaMedica.Sexo} from a {@link SexoEntity}
     *
     * @param sexoEntity to use
     * @return Sexo
     */
    public static FichaMedica.Sexo build(final SexoEntity sexoEntity) {
        return FichaMedica.Sexo.valueOf(sexoEntity.name());
    }

    /**
     * Build a {@link SexoEntity} from a {@link cl.ucn.disc.pdis.fivet.model.FichaMedica.Sexo}
     *
     * @param sexo to use
     * @return SexoEntity
     */
    public static SexoEntity build(final FichaMedica.Sexo sexo) {
        return SexoEntity.valueOf(sexo.name());
    }

    /**
     * Build a {@link FichaMedica} from a {@link FichaMedicaEntity}
     * @param fichaMedicaEntity to use
     * @return FichaMedica
     */
    public static FichaMedica build(final FichaMedicaEntity fichaMedicaEntity) {
        return FichaMedica.builder()
                .numero(fichaMedicaEntity.getNumero())
                .nombrePaciente(fichaMedicaEntity.getNombrePaciente())
                .fechaNacimiento(buildLocalDate(fichaMedicaEntity.getFechaNacimiento()))
                .raza(fichaMedicaEntity.getRaza())
                .color(fichaMedicaEntity.getColor())
                .especie(fichaMedicaEntity.getEspecie())
                .tipo(fichaMedicaEntity.getTipo())
                .sexo(build(fichaMedicaEntity.getSexo()))
                .duenio(build(fichaMedicaEntity.getDuenio()))
                .controles(buildControles(fichaMedicaEntity.getControlesList()))
                .build();
    }

    /**
     * Build a {@link FichaMedicaEntity} from a {@link FichaMedica}
     * @param fichaMedica to use
     * @return FichaMedicaEntity
     */
    public static FichaMedicaEntity build(final FichaMedica fichaMedica) {
        return FichaMedicaEntity.newBuilder()
                .setNumero(fichaMedica.getNumero())
                .setNombrePaciente(fichaMedica.getNombrePaciente())
                .setFechaNacimiento(fichaMedica.getFechaNacimiento().toString())
                .setRaza(fichaMedica.getRaza())
                .setColor(fichaMedica.getColor())
                .setEspecie(fichaMedica.getEspecie())
                .setTipo(fichaMedica.getTipo())
                .setSexo(build(fichaMedica.getSexo()))
                .setDuenio(build(fichaMedica.getDuenio()))
                .addAllControles(buildControlEntities(fichaMedica.getControles()))
                .build();
    }

    /**
     * Build a {@link Collection<ControlEntity>} from a {@link Collection<Control>}
     * @param controles to use
     * @return Collection<ControlEntity>
     */
    public static Collection<ControlEntity> buildControlEntities(final Collection<Control> controles) {
        List<ControlEntity> controlEntities = new ArrayList<>();
        for (Control control : controles) {
            controlEntities.add(build(control));
        }
        return controlEntities;
    }

    /**
     * Build a {@link Collection<Control>} from a {@link Collection<ControlEntity>}
     * @param controlEntities to use
     * @return Collection<Control>
     */
    public static Collection<Control> buildControles(final Collection<ControlEntity> controlEntities) {
        List<Control> controles = new ArrayList<>();
        for (ControlEntity controlEntity : controlEntities) {
            controles.add(build(controlEntity));
        }
        return controles;
    }
}
