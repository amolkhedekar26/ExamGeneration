package com.example.theexamapi.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_department_class_mapping", schema = "exam_api_db", catalog = "")
public class TDepartmentClassMappingEntity {
    @Basic
    @Column(name = "jdepartment_class_mapping_id")
    private int jdepartmentClassMappingId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "uid_department_class_mapping")
    private String uidDepartmentClassMapping;
    @Basic
    @Column(name = "uid_department_fk")
    private String uidDepartmentFk;
    @Basic
    @Column(name = "uid_class_fk")
    private String uidClassFk;
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

    public int getJdepartmentClassMappingId() {
        return jdepartmentClassMappingId;
    }

    public void setJdepartmentClassMappingId(int jdepartmentClassMappingId) {
        this.jdepartmentClassMappingId = jdepartmentClassMappingId;
    }

    public String getUidDepartmentClassMapping() {
        return uidDepartmentClassMapping;
    }

    public void setUidDepartmentClassMapping(String uidDepartmentClassMapping) {
        this.uidDepartmentClassMapping = uidDepartmentClassMapping;
    }

    public String getUidDepartmentFk() {
        return uidDepartmentFk;
    }

    public void setUidDepartmentFk(String uidDepartmentFk) {
        this.uidDepartmentFk = uidDepartmentFk;
    }

    public String getUidClassFk() {
        return uidClassFk;
    }

    public void setUidClassFk(String uidClassFk) {
        this.uidClassFk = uidClassFk;
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
        TDepartmentClassMappingEntity that = (TDepartmentClassMappingEntity) o;
        return jdepartmentClassMappingId == that.jdepartmentClassMappingId && jstatus == that.jstatus && Objects.equals(uidDepartmentClassMapping, that.uidDepartmentClassMapping) && Objects.equals(uidDepartmentFk, that.uidDepartmentFk) && Objects.equals(uidClassFk, that.uidClassFk) && Objects.equals(dtcreatedAt, that.dtcreatedAt) && Objects.equals(uidCreatedByFk, that.uidCreatedByFk) && Objects.equals(uidUpdatedByFk, that.uidUpdatedByFk) && Objects.equals(uidPrevious, that.uidPrevious);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jdepartmentClassMappingId, uidDepartmentClassMapping, uidDepartmentFk, uidClassFk, jstatus, dtcreatedAt, uidCreatedByFk, uidUpdatedByFk, uidPrevious);
    }
}
