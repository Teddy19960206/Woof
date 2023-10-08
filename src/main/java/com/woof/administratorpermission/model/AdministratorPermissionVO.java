package com.woof.administratorpermission.model;

import java.io.Serializable;
import java.util.Objects;

public class AdministratorPermissionVO implements Serializable {
	private Integer adminNo;
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
