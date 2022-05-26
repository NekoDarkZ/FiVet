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
import cl.ucn.disc.pdis.fivet.orm.LocalDateType;
import cl.ucn.disc.pdis.fivet.orm.ORMLiteDAO;
import cl.ucn.disc.pdis.fivet.orm.ZonedDateTimeType;
import com.j256.ormlite.field.DataPersisterManager;
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

        log.debug("Registering the ZonedDateTimeType...");
        DataPersisterManager.registerDataPersisters(ZonedDateTimeType.INSTANCE);
        log.debug("Registering the LocalDateType...");
        DataPersisterManager.registerDataPersisters(LocalDateType.INSTANCE);

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
