package com.example.theexamapi.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_mcq_option", schema = "exam_api_db", catalog = "")
public class TMcqOptionEntity {
    @Basic
    @Column(name = "joption_id")
    private int joptionId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "uid_option")
    private String uidOption;
    @Basic
    @Column(name = "scontent")
    private String scontent;
    @Basic
    @Column(name = "uid_question_fk")
    private String uidQuestionFk;
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

    public int getJoptionId() {
        return joptionId;
    }

    public void setJoptionId(int joptionId) {
        this.joptionId = joptionId;
    }

    public String getUidOption() {
        return uidOption;
    }

    public void setUidOption(String uidOption) {
        this.uidOption = uidOption;
    }

    public String getScontent() {
        return scontent;
    }

    public void setScontent(String scontent) {
        this.scontent = scontent;
    }

    public String getUidQuestionFk() {
        return uidQuestionFk;
    }

    public void setUidQuestionFk(String uidQuestionFk) {
        this.uidQuestionFk = uidQuestionFk;
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
        TMcqOptionEntity that = (TMcqOptionEntity) o;
        return joptionId == that.joptionId && jstatus == that.jstatus && Objects.equals(uidOption, that.uidOption) && Objects.equals(scontent, that.scontent) && Objects.equals(uidQuestionFk, that.uidQuestionFk) && Objects.equals(dtcreatedAt, that.dtcreatedAt) && Objects.equals(uidCreatedByFk, that.uidCreatedByFk) && Objects.equals(uidUpdatedByFk, that.uidUpdatedByFk) && Objects.equals(uidPrevious, that.uidPrevious);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joptionId, uidOption, scontent, uidQuestionFk, jstatus, dtcreatedAt, uidCreatedByFk, uidUpdatedByFk, uidPrevious);
    }
}
