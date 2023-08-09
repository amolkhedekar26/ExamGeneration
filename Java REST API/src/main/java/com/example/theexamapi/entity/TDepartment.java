package com.example.theexamapi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_department", schema = "exam_api_db", catalog = "")
public class TDepartment {
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jdepartment_id")
    private int jdepartmentId;

    @Id
    @Column(name = "uid_department")
    private String uidDepartment;
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
//    @Basic
//    @Column(name = "uid_course_fk")
    @OneToOne
    @JoinColumn(name = "uid_course_fk",referencedColumnName = "uid_course")
    private TCourse uidCourseFk;
//    private String uidCourseFk;
    @Basic
    @Column(name = "uid_previous")
    private Integer uidPrevious;

    public int getJdepartmentId() {
        return jdepartmentId;
    }

    public void setJdepartmentId(int jdepartmentId) {
        this.jdepartmentId = jdepartmentId;
    }

    public String getUidDepartment() {
        return uidDepartment;
    }

    public void setUidDepartment(String uidDepartment) {
        this.uidDepartment = uidDepartment;
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

    public TCourse getUidCourseFk() {
        return uidCourseFk;
    }

    public void setUidCourseFk(TCourse uidCourseFk) {
        this.uidCourseFk = uidCourseFk;
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
        TDepartment that = (TDepartment) o;
        return jdepartmentId == that.jdepartmentId && jstatus == that.jstatus && Objects.equals(uidDepartment, that.uidDepartment) && Objects.equals(stitle, that.stitle) && Objects.equals(sdetails, that.sdetails) && Objects.equals(dtcreatedAt, that.dtcreatedAt) && Objects.equals(uidCreatedByFk, that.uidCreatedByFk) && Objects.equals(uidUpdatedByFk, that.uidUpdatedByFk) && Objects.equals(uidCourseFk, that.uidCourseFk) && Objects.equals(uidPrevious, that.uidPrevious);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jdepartmentId, uidDepartment, stitle, sdetails, jstatus, dtcreatedAt, uidCreatedByFk, uidUpdatedByFk, uidCourseFk, uidPrevious);
    }
}
