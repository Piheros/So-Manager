/**
 * @file JuryItem.java
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

public class JuryItem {

    private String idJury;
    private String date;
    private String info;
    private String supervisorName;
    private String students;
    private String description;
    private String projectId;

    public JuryItem(String idJury, String date, String info, String supervisorName,String students, String description,String projectId){
        this.idJury = idJury;
        this.date = date;
        this.info = info;
        this.supervisorName = supervisorName;
        this.students = students;
        this.description = description;
        this.projectId = projectId;
    }

    public String getIdJury(){
        return this.idJury;
    }

    public String getDate(){
        return this.date;
    }

    public String getInfo(){
        return this.info;
    }

    public String getSupervisorName() {
        return this.supervisorName;
    }

    public String getStudents() {
        return this.students;
    }

    public String getJuryProjectDescription() {
        return this.description;
    }

    public String getJuryProjectId() {
        return this.projectId;
    }

}
