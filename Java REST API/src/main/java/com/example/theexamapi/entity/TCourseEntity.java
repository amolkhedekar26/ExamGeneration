package com.example.theexamapi.entity;

import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@Table(name = "t_course", schema = "the_exam_api")
@SQLDelete(sql = "" +
        "update t_course set state = 3 where uid = ?")
@Loader(namedQuery = "findCourseByUid")
@NamedQuery(name = "findCourseByUid",query = "" +
        "select tCourse from TCourseEntity tCourse where uid=?1 and state=1")
@Where(clause = "state=1")
public class TCourseEntity {
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Id
    @Column(name = "uid")
    private String uid;
    @Basic
    @Column(name = "abbreviation")
    private String abbreviation;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "details")
    private String details;
    @Basic
    @Column(name = "qualification")
    private byte qualification;
    @Basic
    @Column(name = "stream")
    private Integer stream;
    @Basic
    @Column(name = "state")
    private byte state;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Basic
    @Column(name = "created_by")
    private Integer createdBy;
    @Basic
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Basic
    @Column(name = "fk_uid")
    private Integer fkUid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public byte getQualification() {
        return qualification;
    }

    public void setQualification(byte qualification) {
        this.qualification = qualification;
    }

    public Integer getStream() {
        return stream;
    }

    public void setStream(Integer stream) {
        this.stream = stream;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getFkUid() {
        return fkUid;
    }

    public void setFkUid(Integer fkUid) {
        this.fkUid = fkUid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TCourseEntity that = (TCourseEntity) o;
        return id == that.id && qualification == that.qualification && state == that.state && Objects.equals(uid, that.uid) && Objects.equals(abbreviation, that.abbreviation) && Objects.equals(title, that.title) && Objects.equals(details, that.details) && Objects.equals(stream, that.stream) && Objects.equals(createdAt, that.createdAt) && Objects.equals(createdBy, that.createdBy) && Objects.equals(updatedBy, that.updatedBy) && Objects.equals(fkUid, that.fkUid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, abbreviation, title, details, qualification, stream, state, createdAt, createdBy, updatedBy, fkUid);
    }
}
