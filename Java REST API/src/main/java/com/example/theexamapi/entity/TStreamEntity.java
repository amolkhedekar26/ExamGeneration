package com.example.theexamapi.entity;

import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@Table(name = "t_stream", schema = "the_exam_api")
@SQLDelete(sql = "" +
        "update t_stream set state = 3 where uid = ?")
@Loader(namedQuery = "findStreamByUid")
@NamedQuery(name = "findStreamByUid",query = "" +
        "select tStream from TStreamEntity tStream where uid=?1 and state=1")
@Where(clause = "state=1")
public class TStreamEntity {
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Id
    @Column(name = "uid")
    private String uid;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "details")
    private String details;
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
        TStreamEntity that = (TStreamEntity) o;
        return id == that.id && state == that.state && Objects.equals(uid, that.uid) && Objects.equals(title, that.title) && Objects.equals(details, that.details) && Objects.equals(createdAt, that.createdAt) && Objects.equals(createdBy, that.createdBy) && Objects.equals(updatedBy, that.updatedBy) && Objects.equals(fkUid, that.fkUid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, title, details, state, createdAt, createdBy, updatedBy, fkUid);
    }
}
