package com.woof.groupcourseschedule.entity;

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

import com.google.gson.annotations.Expose;
import com.woof.groupcourse.entity.GroupCourse;
import com.woof.trainer.entity.Trainer;


@Entity
@Table(name = "group_course_schedule")
public class GroupCourseSchedule implements Serializable {

    @Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GCS_NO" , updatable = false)
    private Integer gcsNo;

    @Expose
	@ManyToOne
	@JoinColumn(name = "GC_NO" , referencedColumnName = "GC_NO")
    private GroupCourse groupCourse;

    @Expose
	@ManyToOne
	@JoinColumn(name = "TRAINER_NO" , referencedColumnName = "TRAINER_NO")
	private Trainer trainer;

    @Expose
	@Column(name = "GCS_START" , nullable = false)
    private Date gcsStart;

    @Expose
	@Column(name = "GCS_END" , nullable = false)
    private Date gcsEnd;

    @Expose
	@Column(name = "MIN_LIMIT" , nullable = false , columnDefinition = "TINYINT")
    private Integer minLimit;

    @Expose
	@Column(name = "MAX_LIMIT" , nullable = false , columnDefinition = "TINYINT")
    private Integer maxLimit;

    @Expose
	@Column(name = "COUNT" , nullable = false , insertable = false , columnDefinition = "TINYINT")
    private Integer count;

    @Expose
	@Column(name = "GCS_PRICE" , nullable = false)
    private Integer gcsPrice;

    @Expose
	@Column(name = "GCS_STATUS" , nullable = false , insertable = false , columnDefinition = "TINYINT")
    private Integer gcsStatus;

    public GroupCourseSchedule() {
    }
    
    
    public GroupCourse getGroupCourse() {
		return groupCourse;
	}


	public void setGroupCourse(GroupCourse groupCourse) {
		this.groupCourse = groupCourse;
	}


	public Trainer getTrainer() {
		return trainer;
	}


	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}


	public Integer getGcsNo() {
        return gcsNo;
    }

    public void setGcsNo(Integer gcsNo) {
        this.gcsNo = gcsNo;
    }

    public Date getGcsStart() {
        return gcsStart;
    }

    public void setGcsStart(Date gscStart) {
        this.gcsStart = gscStart;
    }

    public Date getGcsEnd() {
        return gcsEnd;
    }

    public void setGcsEnd(Date gscEnd) {
        this.gcsEnd = gscEnd;
    }

    public Integer getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(Integer minLimit) {
        this.minLimit = minLimit;
    }

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getGcsPrice() {
        return gcsPrice;
    }

    public void setGcsPrice(Integer gcsPrice) {
        this.gcsPrice = gcsPrice;
    }

    public Integer getGcsStatus() {
        return gcsStatus;
    }

    public void setGcsStatus(Integer gcsStatus) {
        this.gcsStatus = gcsStatus;
    }


	@Override
	public int hashCode() {
		return Objects.hash(gcsNo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupCourseSchedule other = (GroupCourseSchedule) obj;
		return Objects.equals(gcsNo, other.gcsNo);
	}


	@Override
	public String toString() {
		return "GroupCourseSchedule [gcsNo=" + gcsNo + ", gcsStart=" + gcsStart + ", gcsEnd=" + gcsEnd + ", minLimit="
				+ minLimit + ", maxLimit=" + maxLimit + ", count=" + count + ", gcsPrice=" + gcsPrice + ", gcsStatus="
				+ gcsStatus + "]";
	}

    
}
