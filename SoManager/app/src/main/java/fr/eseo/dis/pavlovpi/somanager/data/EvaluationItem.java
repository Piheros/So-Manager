package fr.eseo.dis.pavlovpi.somanager.data;

public class EvaluationItem {

    private String forename;
    private String surname;

    private String note;

    public EvaluationItem(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
    }

    public String getForename() {
        return this.forename;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
