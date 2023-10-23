package com.woof.groupscheduledetail.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.trainer.entity.Trainer;
import org.hibernate.annotations.BatchSize;


@Entity
@Table(name = "group_course_schedule_detail")
@BatchSize(size = 20)
public class GroupScheduleDetail implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GCSD_NO" , updatable = false)
    private Integer gcsdNo;
	
	@ManyToOne
	@JoinColumn(name = "GCS_NO" , referencedColumnName = "GCS_NO")
	private GroupCourseSchedule groupCourseSchedule;
	
	@ManyToOne
	@JoinColumn(name= "TRAINER_NO" , referencedColumnName = "TRAINER_NO")
    private Trainer trainer;
	
	@Column(name = "CLASS_DATE" , nullable = false)
    private Date classDate;

    public GroupScheduleDetail() {
    }

    public Integer getGcsdNo() {
        return gcsdNo;
    }

    public void setGcsdNo(Integer gcsdNo) {
        this.gcsdNo = gcsdNo;
    }

    public GroupCourseSchedule getGroupCourseSchedule() {
		return groupCourseSchedule;
	}

	public void setGroupCourseSchedule(GroupCourseSchedule groupCourseSchedule) {
		this.groupCourseSchedule = groupCourseSchedule;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

	@Override
	public int hashCode() {
		return Objects.hash(gcsdNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupScheduleDetail other = (GroupScheduleDetail) obj;
		return Objects.equals(gcsdNo, other.gcsdNo);
	}

	@Override
	public String toString() {
		return "GroupScheduleDetailDAOImpl [gcsdNo=" + gcsdNo + ", classDate=" + classDate + "]";
	}


}
