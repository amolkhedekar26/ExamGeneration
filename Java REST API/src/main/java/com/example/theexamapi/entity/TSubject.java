package com.example.theexamapi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_subject", schema = "exam_api_db", catalog = "")
public class TSubject {
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jsubject_id")
    private int jsubjectId;

    @Id
    @Column(name = "uid_subject")
    private String uidSubject;
    //    @Basic
//    @Column(name = "uid_department_class_semester_map")
    @OneToOne
    @JoinColumn(name = "uid_department_class_semester_map", referencedColumnName = "uid_department_class_map_semester_mapping")
//    private TDepartmentClassSemesterMap tDepartmentClassSemesterMap;
    private TDepartmentClassMapSemesterMapping uidDepartmentClassSemesterMapFK;
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
    @Basic
    @Column(name = "uid_previous")
    private Integer uidPrevious;

    public int getJsubjectId() {
        return jsubjectId;
    }

    public void setJsubjectId(int jsubjectId) {
        this.jsubjectId = jsubjectId;
    }

    public String getUidSubject() {
        return uidSubject;
    }

    public void setUidSubject(String uidSubject) {
        this.uidSubject = uidSubject;
    }

    public TDepartmentClassMapSemesterMapping getUidDepartmentClassSemesterMap() {
        return uidDepartmentClassSemesterMapFK;
    }

    public void setUidDepartmentClassSemesterMap(TDepartmentClassMapSemesterMapping uidDepartmentClassSemesterMap) {
        this.uidDepartmentClassSemesterMapFK = uidDepartmentClassSemesterMap;
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
        TSubject that = (TSubject) o;
        return jsubjectId == that.jsubjectId && jstatus == that.jstatus && Objects.equals(uidSubject, that.uidSubject) && Objects.equals(uidDepartmentClassSemesterMapFK, that.uidDepartmentClassSemesterMapFK) && Objects.equals(stitle, that.stitle) && Objects.equals(sdetails, that.sdetails) && Objects.equals(dtcreatedAt, that.dtcreatedAt) && Objects.equals(uidCreatedByFk, that.uidCreatedByFk) && Objects.equals(uidUpdatedByFk, that.uidUpdatedByFk) && Objects.equals(uidPrevious, that.uidPrevious);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jsubjectId, uidSubject, uidDepartmentClassSemesterMapFK, stitle, sdetails, jstatus, dtcreatedAt, uidCreatedByFk, uidUpdatedByFk, uidPrevious);
    }
}
