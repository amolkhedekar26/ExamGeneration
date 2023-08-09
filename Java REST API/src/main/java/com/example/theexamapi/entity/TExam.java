package com.example.theexamapi.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_exam", schema = "exam_api_db", catalog = "")
public class TExam {
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jexam_id")
    private int jexamId;

    @Id
    @Column(name = "uid_exam")
    private String uidExam;
    @Basic
    @Column(name = "stitle")
    private String stitle;
    @Basic
    @Column(name = "sdetails")
    private String sdetails;
    @Basic
    @Column(name = "dtdate_of_exam")
    private Date dtdateOfExam;
    @Basic
    @Column(name = "stype_of_exam")
    private String stypeOfExam;
    @Basic
    @Column(name = "jduration")
    private Integer jduration;
    @Basic
    @Column(name = "jtotal_marks")
    private Integer jtotalMarks;
    @Basic
    @Column(name = "jpassing_marks")
    private Integer jpassingMarks;
    @Basic
    @Column(name = "sstatus_of_exam")
    private String sstatusOfExam;
//    @Basic
//    @Column(name = "uid_subject_fk")
    @OneToOne
    @JoinColumn(name = "uid_subject_fk", referencedColumnName = "uid_subject")
    private TSubject uidSubjectFk;
    @Basic
    @Column(name = "jstatus")
    private int jstatus;
    @Basic
    @Column(name = "dtcreated_at")
    private Timestamp dtcreatedAt;
    @Basic
    @Column(name = "uid_created_by_fk")
    private Integer uidCreatedByFk;
    @Basic
    @Column(name = "uid_updated_by_fk")
    private Integer uidUpdatedByFk;
    @Basic
    @Column(name = "uid_previous")
    private Integer uidPrevious;

    public int getJexamId() {
        return jexamId;
    }

    public void setJexamId(int jexamId) {
        this.jexamId = jexamId;
    }

    public String getUidExam() {
        return uidExam;
    }

    public void setUidExam(String uidExam) {
        this.uidExam = uidExam;
    }

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle;
    }

    public String getSdetails() {
        return sdetails;
    }

    public void setSdetails(String sdetails) {
        this.sdetails = sdetails;
    }

    public Date getDtdateOfExam() {
        return dtdateOfExam;
    }

    public void setDtdateOfExam(Date dtdateOfExam) {
        this.dtdateOfExam = dtdateOfExam;
    }

    public String getStypeOfExam() {
        return stypeOfExam;
    }

    public void setStypeOfExam(String stypeOfExam) {
        this.stypeOfExam = stypeOfExam;
    }

    public Integer getJduration() {
        return jduration;
    }

    public void setJduration(Integer jduration) {
        this.jduration = jduration;
    }

    public Integer getJtotalMarks() {
        return jtotalMarks;
    }

    public void setJtotalMarks(Integer jtotalMarks) {
        this.jtotalMarks = jtotalMarks;
    }

    public Integer getJpassingMarks() {
        return jpassingMarks;
    }

    public void setJpassingMarks(Integer jpassingMarks) {
        this.jpassingMarks = jpassingMarks;
    }

    public String getSstatusOfExam() {
        return sstatusOfExam;
    }

    public void setSstatusOfExam(String sstatusOfExam) {
        this.sstatusOfExam = sstatusOfExam;
    }

    public TSubject getUidSubjectFk() {
        return uidSubjectFk;
    }

    public void setUidSubjectFk(TSubject uidSubjectFk) {
        this.uidSubjectFk = uidSubjectFk;
    }

    public int getJstatus() {
        return jstatus;
    }

    public void setJstatus(int jstatus) {
        this.jstatus = jstatus;
    }

    public Timestamp getDtcreatedAt() {
        return dtcreatedAt;
    }

    public void setDtcreatedAt(Timestamp dtcreatedAt) {
        this.dtcreatedAt = dtcreatedAt;
    }

    public Integer getUidCreatedByFk() {
        return uidCreatedByFk;
    }

    public void setUidCreatedByFk(Integer uidCreatedByFk) {
        this.uidCreatedByFk = uidCreatedByFk;
    }

    public Integer getUidUpdatedByFk() {
        return uidUpdatedByFk;
    }

    public void setUidUpdatedByFk(Integer uidUpdatedByFk) {
        this.uidUpdatedByFk = uidUpdatedByFk;
    }

    public Integer getUidPrevious() {
        return uidPrevious;
    }

    public void setUidPrevious(Integer uidPrevious) {
        this.uidPrevious = uidPrevious;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TExam that = (TExam) o;
        return jexamId == that.jexamId && jstatus == that.jstatus && Objects.equals(uidExam, that.uidExam) && Objects.equals(stitle, that.stitle) && Objects.equals(sdetails, that.sdetails) && Objects.equals(dtdateOfExam, that.dtdateOfExam) && Objects.equals(stypeOfExam, that.stypeOfExam) && Objects.equals(jduration, that.jduration) && Objects.equals(jtotalMarks, that.jtotalMarks) && Objects.equals(jpassingMarks, that.jpassingMarks) && Objects.equals(sstatusOfExam, that.sstatusOfExam) && Objects.equals(uidSubjectFk, that.uidSubjectFk) && Objects.equals(dtcreatedAt, that.dtcreatedAt) && Objects.equals(uidCreatedByFk, that.uidCreatedByFk) && Objects.equals(uidUpdatedByFk, that.uidUpdatedByFk) && Objects.equals(uidPrevious, that.uidPrevious);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jexamId, uidExam, stitle, sdetails, dtdateOfExam, stypeOfExam, jduration, jtotalMarks, jpassingMarks, sstatusOfExam, uidSubjectFk, jstatus, dtcreatedAt, uidCreatedByFk, uidUpdatedByFk, uidPrevious);
    }
}
