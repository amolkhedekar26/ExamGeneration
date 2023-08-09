package com.example.theexamapi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_mcq_option", schema = "exam_api_db", catalog = "")
public class TMcqOption {
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "joption_id")
    private int joptionId;

    @Id
    @Column(name = "uid_option")
    private String uidOption;
    @Basic
    @Column(name = "scontent")
    private String scontent;
//    @Basic
//    @Column(name = "uid_question_fk")
    @OneToOne
    @JoinColumn(name = "uid_question_fk", referencedColumnName = "uid_question")
    private TQuestion uidQuestionFk;
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

    public TQuestion getUidQuestionFk() {
        return uidQuestionFk;
    }

    public void setUidQuestionFk(TQuestion uidQuestionFk) {
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
        TMcqOption that = (TMcqOption) o;
        return joptionId == that.joptionId && jstatus == that.jstatus && Objects.equals(uidOption, that.uidOption) && Objects.equals(scontent, that.scontent) && Objects.equals(uidQuestionFk, that.uidQuestionFk) && Objects.equals(dtcreatedAt, that.dtcreatedAt) && Objects.equals(uidCreatedByFk, that.uidCreatedByFk) && Objects.equals(uidUpdatedByFk, that.uidUpdatedByFk) && Objects.equals(uidPrevious, that.uidPrevious);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joptionId, uidOption, scontent, uidQuestionFk, jstatus, dtcreatedAt, uidCreatedByFk, uidUpdatedByFk, uidPrevious);
    }
}
