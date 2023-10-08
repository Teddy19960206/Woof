package com.woof.administratorpermission.model;

import java.util.Objects;

public class AdministratorPermissionVO implements java.io.Serializable {
private Integer adminNo;
private Integer FUNCNo;
public AdministratorPermissionVO() {
}
public Integer getAdminNo() {
	return adminNo;
}
public void setAdminNo(Integer adminNo) {
	this.adminNo = adminNo;
}
public Integer getFUNCNo() {
	return FUNCNo;
}
public void setFUNCNo(Integer fUNCNo) {
	FUNCNo = fUNCNo;
}
@Override
public int hashCode() {
	return Objects.hash(FUNCNo, adminNo);
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
	return Objects.equals(FUNCNo, other.FUNCNo) && Objects.equals(adminNo, other.adminNo);
}
@Override
public String toString() {
	return "AdministratorPermission [adminNo=" + adminNo + ", FUNCNo=" + FUNCNo + ", getAdminNo()=" + getAdminNo()
			+ ", getFUNCNo()=" + getFUNCNo() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
			+ ", toString()=" + super.toString() + "]";
}
}
