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

package cl.ucn.disc.pdis.fivet.model;

import cl.ucn.disc.pdis.fivet.orm.BaseEntity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;

/**
 * FichaMedica Class.
 *
 * @author Sebastian Murquio-Castillo
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DatabaseTable
public final class FichaMedica extends BaseEntity {

    /**
     * The number of the FichaMedica
     */
    @Getter
    @DatabaseField(canBeNull = false, unique = true)
    private Integer numero;

    /**
     * the name of the Patient
     */
    @Getter
    @DatabaseField(canBeNull = false)
    private String nombrePaciente;

    /**
     * The especie of the Patient
     */
    @Getter
    @DatabaseField(canBeNull = false)
    private String especie;

    /**
     * The date of birth of the Patient
     */
    @Getter
    @DatabaseField(canBeNull = false)
    private LocalDate fechaNacimiento;

    /**
     * The raza of the Patient
     */
    @Getter
    @DatabaseField(canBeNull = false)
    private String raza;

    /**
     * The color of the Patient
     */
    @Getter
    @DatabaseField(canBeNull = false)
    private String color;

    /**
     * The tipo of the Patient
     */
    @Getter
    @DatabaseField(canBeNull = false)
    private String tipo;

    /**
     * The sexo of the Patient
     */
    @Getter
    @DatabaseField(canBeNull = false)
    private Sexo sexo;

    /**
     * The Duenio of the Patient
     */
    @Getter
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "duenio")
    private Persona duenio;

    /**
     * The list of controles of the Patient
     */
    @Getter
    @ForeignCollectionField(eager = true, orderColumnName = "fecha")
    private Collection<Control> controles;

    /**
     * Append a Control
     */
    public void add(Control control) {
        this.controles.add(control);
    }

    /**
     * The Sexo
     */
    public enum Sexo {
        MACHO,
        HEMBRA
    }
}
