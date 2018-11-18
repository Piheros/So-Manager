/**
 * @file NotationItem.java
 * @version 0.2
 * @author Mehdi Bouafia & Pierre Pavlovic
 * @date 18/11/2018
 *
 * @section License
 *
 * GNU GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 *
 * Copyright (C) 2018  Mehdi Bouafia & Pierre Pavlovic
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */
package fr.eseo.dis.pavlovpi.somanager.data;

public class NotationItem {

    private String forename;
    private String note;
    private String noteAverage;
    private String surname;

    public NotationItem (String forename, String surname, String note,String noteAverage) {
        this.forename = forename;
        this.surname = surname;
        this.note = note;
        this.noteAverage = noteAverage;
    }

    public String getNotationForename() {
        return this.forename;
    }

    public String getNotationSurname() {
        return this.surname;
    }

    public String getNotationNote() {
        return this.note;
    }

    public String getNoteAverage() {
        return this.noteAverage;
    }

}
