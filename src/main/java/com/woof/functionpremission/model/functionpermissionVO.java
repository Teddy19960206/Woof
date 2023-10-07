package com.woof.functionpremission.model;

import java.util.Objects;

public class functionpermissionVO implements java.io.Serializable {
	private Integer funcno;
	private Integer funcname;

	public Integer getFuncno() {
		return funcno;
	}

	public void setFuncno(Integer funcno) {
		this.funcno = funcno;
	}

	public Integer getFuncname() {
		return funcname;
	}

	public void setFuncname(Integer funcname) {
		this.funcname = funcname;
	}

	@Override
	public int hashCode() {
		return Objects.hash(funcname, funcno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		functionpermissionVO other = (functionpermissionVO) obj;
		return Objects.equals(funcname, other.funcname) && Objects.equals(funcno, other.funcno);
	}

	@Override
	public String toString() {
		return "functionpermissionVO [funcno=" + funcno + ", funcname=" + funcname + "]";
	}

}