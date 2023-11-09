package com.woof.administratorpermission.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name="administrator_permission")
@IdClass(AdministratorPermission.CompositeAdministratorPermission.class)
public class AdministratorPermission implements Serializable {
	@Id
	@Column(name="ADMIN_NO",unique = true, nullable=false)
	private Integer adminNo;
	@Id
	@Column(name="FUNC_NO" ,nullable=false)
	private Integer funcNo;

	public CompositeAdministratorPermission getCompositeKey() {
		return new CompositeAdministratorPermission(adminNo , funcNo);
	}
	
	public void setCompositekey(CompositeAdministratorPermission key) {
		this.adminNo = key.getAdminNo();
		this.funcNo = key.getFuncNo();
	}
	
	
	
	public AdministratorPermission() {
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

	static class CompositeAdministratorPermission implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer adminNo;
		private Integer funcNo;
		public CompositeAdministratorPermission(Integer adminNo, Integer funcNo) {
			super();
			this.adminNo = adminNo;
			this.funcNo = funcNo;
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
			CompositeAdministratorPermission other = (CompositeAdministratorPermission) obj;
			return Objects.equals(adminNo, other.adminNo) && Objects.equals(funcNo, other.funcNo);
		}

		@Override
		public String toString() {
			return "CompositeAdministratorPermission [adminNo=" + adminNo + ", funcNo=" + funcNo + "]";
		}
		
	}


}

 
	