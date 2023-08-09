package com.example.theexamapi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_course", schema = "exam_api_db", catalog = "")
public class TCourse {
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jcourse_id")
    private int jcourseId;

    @Id
    @Column(name = "uid_course")
    private String uidCourse;
    @Basic
    @Column(name = "stitle")
    private String stitle;
    @Basic
    @Column(name = "sdetails")
    private String sdetails;
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

    @OneToOne
    @JoinColumn(name = "uid_stream_fk",referencedColumnName = "uid_stream")
    private TStream uidStreamFk;
    @Basic
    @Column(name = "uid_previous")
    private Integer uidPrevious;

    public int getJcourseId() {
        return jcourseId;
    }

    public void setJcourseId(int jcourseId) {
        this.jcourseId = jcourseId;
    }

    public String getUidCourse() {
        return uidCourse;
    }

    public void setUidCourse(String uidCourse) {
        this.uidCourse = uidCourse;
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

    public TStream getUidStreamFk() {
        return uidStreamFk;
    }

    public void setUidStreamFk(TStream uidStreamFk) {
        this.uidStreamFk = uidStreamFk;
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
        TCourse that = (TCourse) o;
        return jcourseId == that.jcourseId && jstatus == that.jstatus && Objects.equals(uidCourse, that.uidCourse) && Objects.equals(stitle, that.stitle) && Objects.equals(sdetails, that.sdetails) && Objects.equals(dtcreatedAt, that.dtcreatedAt) && Objects.equals(uidCreatedByFk, that.uidCreatedByFk) && Objects.equals(uidUpdatedByFk, that.uidUpdatedByFk) && Objects.equals(uidStreamFk, that.uidStreamFk) && Objects.equals(uidPrevious, that.uidPrevious);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jcourseId, uidCourse, stitle, sdetails, jstatus, dtcreatedAt, uidCreatedByFk, uidUpdatedByFk, uidStreamFk, uidPrevious);
    }
}
