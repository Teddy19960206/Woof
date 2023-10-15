package com.woof.functionpermission.entity;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "function_permission")
public class FunctionPermissionVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FUNC_NO", updatable = false, nullable = false)
	private Integer funcNo;

	@Column(name = "FUNC_NAME", nullable = false, columnDefinition = "TINYINT")
	private Integer funcName;

	public Integer getFuncNo() {
		return funcNo;
	}

	public void setFuncNo(Integer funcNo) {
		this.funcNo = funcNo;
	}

	public Integer getFuncName() {
		return funcName;
	}

	public void setFuncName(Integer funcName) {
		this.funcName = funcName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(funcName, funcNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FunctionPermissionVO other = (FunctionPermissionVO) obj;
		return Objects.equals(funcName, other.funcName) && Objects.equals(funcNo, other.funcNo);
	}

	@Override
	public String toString() {
		return "FunctionPermissionVO [funcNo=" + funcNo + ", funcName=" + funcName + "]";
	}
}
//package com.woof.functionpermission.model;
//
//import java.util.Objects;
//
//public class FunctionPermissionVO implements java.io.Serializable {
//	private Integer funcNo;
//	private Integer funcName;
//
//	public Integer getFuncNo() {
//		return funcNo;
//	}
//
//	public void setFuncNo(Integer funcNo) {
//		this.funcNo = funcNo;
//	}
//
//	public Integer getFuncName() {
//		return funcName;
//	}
//
//	public void setFuncName(Integer funcName) {
//		this.funcName = funcName;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(funcName, funcNo);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		FunctionPermissionVO other = (FunctionPermissionVO) obj;
//		return Objects.equals(funcName, other.funcName) && Objects.equals(funcNo, other.funcNo);
//	}
//
//	@Override
//	public String toString() {
//		return "FunctionPermissionVO [funcNo=" + funcNo + ", funcName=" + funcName + "]";
//	}
//}
