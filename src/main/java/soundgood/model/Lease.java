package soundgood.model;

import java.sql.Timestamp;

public class Lease {
    int id;
    Timestamp startDate;
    Timestamp endDate;
    int instrumentID;
    int student_id;

    public Lease(int student_id) {
        this.student_id = student_id;
    }

    public Lease(int id, Timestamp startDate, Timestamp endDate, int instrumentID, int student_id) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instrumentID = instrumentID;
        this.student_id = student_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public int getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(int instrumentID) {
        this.instrumentID = instrumentID;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
}

