package com.woof.administratorpermission.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="administrator_permission")
public class AdministratorPermissionVO implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ADMIN_NO",updatable=false, nullable=false)
	private Integer adminNo;
	@Column(name="FUNC_NO" ,nullable=false)
	private Integer funcNo;

	public AdministratorPermissionVO() {
	}

	public Integer getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}

	public Integer getFuncNo() {
		return funcNo;
	}

	public void setFuncNo(Integer funcNo) {
		this.funcNo = funcNo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminNo, funcNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdministratorPermissionVO other = (AdministratorPermissionVO) obj;
		return Objects.equals(adminNo, other.adminNo) && Objects.equals(funcNo, other.funcNo);
	}

	@Override
	public String toString() {
		return "AdministratorPermissionVO [adminNo=" + adminNo + ", funcNo=" + funcNo + ", getAdminNo()=" + getAdminNo()
				+ ", getFuncNo()=" + getFuncNo() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}

}
