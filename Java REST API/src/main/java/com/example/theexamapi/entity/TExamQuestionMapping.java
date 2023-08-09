package com.example.theexamapi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_exam_question_mapping", schema = "exam_api_db", catalog = "")
public class TExamQuestionMapping {
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jexam_question_mapping_id")
    private int jexamQuestionMappingId;

    @Id
    @Column(name = "uid_exam_question_mapping")
    private String uidExamQuestionMapping;
//    @Basic
//    @Column(name = "uid_exam_fk")
    @OneToOne
    @JoinColumn(name = "uid_exam_fk", referencedColumnName = "uid_exam")
    private TExam uidExamFk;
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

    public int getJexamQuestionMappingId() {
        return jexamQuestionMappingId;
    }

    public void setJexamQuestionMappingId(int jexamQuestionMappingId) {
        this.jexamQuestionMappingId = jexamQuestionMappingId;
    }

    public String getUidExamQuestionMapping() {
        return uidExamQuestionMapping;
    }

    public void setUidExamQuestionMapping(String uidExamQuestionMapping) {
        this.uidExamQuestionMapping = uidExamQuestionMapping;
    }

    public TExam getUidExamFk() {
        return uidExamFk;
    }

    public void setUidExamFk(TExam uidExamFk) {
        this.uidExamFk = uidExamFk;
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
        TExamQuestionMapping that = (TExamQuestionMapping) o;
        return jexamQuestionMappingId == that.jexamQuestionMappingId && jstatus == that.jstatus && Objects.equals(uidExamQuestionMapping, that.uidExamQuestionMapping) && Objects.equals(uidExamFk, that.uidExamFk) && Objects.equals(uidQuestionFk, that.uidQuestionFk) && Objects.equals(dtcreatedAt, that.dtcreatedAt) && Objects.equals(uidCreatedByFk, that.uidCreatedByFk) && Objects.equals(uidUpdatedByFk, that.uidUpdatedByFk) && Objects.equals(uidPrevious, that.uidPrevious);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jexamQuestionMappingId, uidExamQuestionMapping, uidExamFk, uidQuestionFk, jstatus, dtcreatedAt, uidCreatedByFk, uidUpdatedByFk, uidPrevious);
    }
}
