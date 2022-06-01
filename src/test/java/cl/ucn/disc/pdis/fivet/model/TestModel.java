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

import cl.ucn.disc.pdis.fivet.orm.DAO;
import cl.ucn.disc.pdis.fivet.orm.ORMLiteDAO;
import com.j256.ormlite.support.ConnectionSource;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * The Test of Model
 *
 * @author Sebastian Murquio-Castillo
 */
@Slf4j
public final class TestModel {

    /**
     * Testing the Model
     */
    @SneakyThrows
    @Test
    public void testModel() {
        log.info("Starting the test...");

        // The password encoder
        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

        log.debug("Build the ConnectionSource...");

        // The connection
        @Cleanup
        ConnectionSource cs = ORMLiteDAO.buildConnectionSource("jdbc:h2:mem:fivet");
        // ConnectionSource cs = ORMLiteDAO.buildConnectionSource("jdbc:sqlite:fivet_test.db");

        log.debug("Loading FichaMedica...");
        DAO<FichaMedica> daoFichaMedica = new ORMLiteDAO<>(cs, FichaMedica.class);
        daoFichaMedica.dropAndCreateTable();

        log.debug("Loading Persona...");
        DAO<Persona> daoPersona = new ORMLiteDAO<>(cs, Persona.class);
        daoPersona.dropAndCreateTable();

        log.debug("Loading Control...");
        DAO<Control> daoControl = new ORMLiteDAO<>(cs, Control.class);
        daoControl.dropAndCreateTable();

        // Saving a Persona
        {
            Persona persona = Persona.builder()
                    .rut("199682954")
                    .nombre("Sebastian Murquio-Castillo")
                    .email("sebastian.murquio@alumnos.ucn.cl")
                    .passwd(passwordEncoder.encode("smurquio123"))
                    .direccion("Angamos #0610")
                    .build();
            daoPersona.save(persona);
            print("Persona", persona);
            Assertions.assertNotNull(persona.getId(), "ID was null");
        }

        // Getting attributes from Persona
        {
           Optional<Persona> persona = Optional.of(daoPersona.get(1).orElseThrow());
           Assertions.assertNotNull(persona.get().getRut(), "Rut was null");
           Assertions.assertNotNull(persona.get().getNombre(), "Nombre was null");
           Assertions.assertNotNull(persona.get().getDireccion(), "Direccion was null");
           Assertions.assertNotNull(persona.get().getEmail(), "Email was null");
           Assertions.assertNotNull(persona.get().getPasswd(), "Password was null");
           Assertions.assertNotNull(persona.get().getId(), "ID was null");
           Assertions.assertNotNull(persona.get().getCreatedAt(), "CreatedAt was null");
           Assertions.assertNull(persona.get().getDeletedAt(), "CreatedAt wasnt null");
        }

        // Retrieve a Persona
        {
            Persona persona = daoPersona.get(1).orElseThrow();
            print("Persona", persona);

            // Save FichaMedica
            {
                FichaMedica fichaMedica = FichaMedica.builder()
                        .numero(100)
                        .nombrePaciente("Fofi")
                        .especie("Felino")
                        .fechaNacimiento(LocalDate.now())
                        .raza("Calico")
                        .color("Gris")
                        .tipo("Normal")
                        .duenio(persona)
                        .sexo(FichaMedica.Sexo.HEMBRA)
                        .build();
                daoFichaMedica.save(fichaMedica);
                Assertions.assertNotNull(fichaMedica.getId(), "Id was null");
                print("FichaMedica", fichaMedica);
            }
        }

        // Getting attributes from FichaMedica
        {
            FichaMedica fichaMedica = daoFichaMedica.get(1).orElseThrow();
            Assertions.assertNotNull(fichaMedica.getId(), "ID was null");
            Assertions.assertNotNull(fichaMedica.getNumero(), "Numero was null");
            Assertions.assertNotNull(fichaMedica.getNombrePaciente(), "NombrePaciente was null");
            Assertions.assertNotNull(fichaMedica.getEspecie(), "Especie was null");
            Assertions.assertNotNull(fichaMedica.getFechaNacimiento(), "FechaNacimiento was null");
            Assertions.assertNotNull(fichaMedica.getRaza(), "Raza was null");
            Assertions.assertNotNull(fichaMedica.getColor(), "Color was null");
            Assertions.assertNotNull(fichaMedica.getTipo(), "Tipo was null");
            Assertions.assertNotNull(fichaMedica.getSexo(), "Sexo was null");
        }

        // Retrieve a FichaMedica
        {
            FichaMedica fichaMedica = daoFichaMedica.get(1).orElseThrow();
            print("FichaMedica", fichaMedica);
            print("Duenio", fichaMedica.getDuenio());
            Assertions.assertNotNull(fichaMedica.getDuenio(), "Duenio was null");

            // Create a Control
            {
                Control control = Control.builder()
                        .altura(0.6)
                        .diagnostico("Sin novedad")
                        .fecha(ZonedDateTime.now())
                        .peso(10.5)
                        .temperatura(38.5)
                        .fichaMedica(fichaMedica)
                        .veterinario(fichaMedica.getDuenio())
                        .build();
                fichaMedica.add(control);



            // Getting attributes from Control
                
                Assertions.assertNotNull(control.getId(), "ID was null");
                Assertions.assertNotNull(control.getFecha(), "Fecha was null");
                Assertions.assertNotNull(control.getTemperatura(), "Temperatura was null");
                Assertions.assertNotNull(control.getPeso(), "Peso was null");
                Assertions.assertNotNull(control.getAltura(), "Altura was null");
                Assertions.assertNotNull(control.getDiagnostico(), "Diagnostico was null");
                Assertions.assertNotNull(control.getVeterinario(), "Veterinario was null");
                Assertions.assertNotNull(control.getFichaMedica(), "FichaMedica was null");
            }

        }

        // Retrieve a FichaMedica with Control
        {
            FichaMedica fichaMedica = daoFichaMedica.get(1).orElseThrow();
            print("FichaMedica", fichaMedica);
            Assertions.assertNotNull(fichaMedica.getControles(), "Controles was null");
            Assertions.assertEquals(1, fichaMedica.getControles().size(), "Controles != 1");
            for (Control c : fichaMedica.getControles()) {
                print("Control", c);
            }
        }
        log.info("Done.");
    }

    /**
     * Print a Object with message
     * @param message to use
     * @param o       to use
     */
    private static void print(String message, Object o) {
        log.debug("{}: {}", message, ToStringBuilder.reflectionToString(o, ToStringStyle.MULTI_LINE_STYLE));
    }

}
