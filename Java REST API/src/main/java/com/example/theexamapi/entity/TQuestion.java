package com.example.theexamapi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_question", schema = "exam_api_db", catalog = "")
public class TQuestion {
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jquestion_id")
    private int jquestionId;

    @Id
    @Column(name = "uid_question")
    private String uidQuestion;
    @Basic
    @Column(name = "squestion_text")
    private String squestionText;
    @Basic
    @Column(name = "scategory")
    private String scategory;
    @Basic
    @Column(name = "stag")
    private String stag;
    @Basic
    @Column(name = "jmarks")
    private Integer jmarks;
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

    public int getJquestionId() {
        return jquestionId;
    }

    public void setJquestionId(int jquestionId) {
        this.jquestionId = jquestionId;
    }

    public String getUidQuestion() {
        return uidQuestion;
    }

    public void setUidQuestion(String uidQuestion) {
        this.uidQuestion = uidQuestion;
    }

    public String getSquestionText() {
        return squestionText;
    }

    public void setSquestionText(String squestionText) {
        this.squestionText = squestionText;
    }

    public String getScategory() {
        return scategory;
    }

    public void setScategory(String scategory) {
        this.scategory = scategory;
    }

    public String getStag() {
        return stag;
    }

    public void setStag(String stag) {
        this.stag = stag;
    }

    public Integer getJmarks() {
        return jmarks;
    }

    public void setJmarks(Integer jmarks) {
        this.jmarks = jmarks;
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
        TQuestion that = (TQuestion) o;
        return jquestionId == that.jquestionId && jstatus == that.jstatus && Objects.equals(uidQuestion, that.uidQuestion) && Objects.equals(squestionText, that.squestionText) && Objects.equals(scategory, that.scategory) && Objects.equals(stag, that.stag) && Objects.equals(jmarks, that.jmarks) && Objects.equals(dtcreatedAt, that.dtcreatedAt) && Objects.equals(uidCreatedByFk, that.uidCreatedByFk) && Objects.equals(uidUpdatedByFk, that.uidUpdatedByFk) && Objects.equals(uidPrevious, that.uidPrevious);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jquestionId, uidQuestion, squestionText, scategory, stag, jmarks, jstatus, dtcreatedAt, uidCreatedByFk, uidUpdatedByFk, uidPrevious);
    }
}
