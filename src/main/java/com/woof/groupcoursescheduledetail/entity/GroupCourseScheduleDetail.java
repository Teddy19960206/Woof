package com.woof.groupcoursescheduledetail.entity;

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
import com.woof.trainer.model.TrainerVO;


@Entity
@Table(name = "group_course_schedule_detail")
public class GroupCourseScheduleDetail implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GCSD_NO" , updatable = false)
    private Integer gcsdNo;
	
	@ManyToOne
	@JoinColumn(name = "GCS_NO" , referencedColumnName = "GCS_NO")
	private GroupCourseSchedule groupCourseSchedule;
	
	@ManyToOne
	@JoinColumn(name= "TRAINER_NO" , referencedColumnName = "TRAINER_NO")
    private TrainerVO trainerVO;
	
	@Column(name = "CLASS_DATE" , nullable = false)
    private Date classDate;

    public GroupCourseScheduleDetail() {
    }

    public Integer getGcsdNo() {
        return gcsdNo;
    }

    public void setGcsdNo(Integer gcsdNo) {
        this.gcsdNo = gcsdNo;
    }



    public GroupCourseSchedule getGroupCourseScheduleVO() {
		return groupCourseSchedule;
	}

	public void setGroupCourseScheduleVO(GroupCourseSchedule groupCourseSchedule) {
		this.groupCourseSchedule = groupCourseSchedule;
	}

	public TrainerVO getTrainerVO() {
		return trainerVO;
	}

	public void setTrainerVO(TrainerVO trainerVO) {
		this.trainerVO = trainerVO;
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
		GroupCourseScheduleDetail other = (GroupCourseScheduleDetail) obj;
		return Objects.equals(gcsdNo, other.gcsdNo);
	}

	@Override
	public String toString() {
		return "GroupCourseScheduleDetailVO [gcsdNo=" + gcsdNo + ", classDate=" + classDate + "]";
	}


}
