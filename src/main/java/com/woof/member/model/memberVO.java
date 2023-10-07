package com.woof.member.model;

import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

public class memberVO implements java.io.Serializable {
	private Integer memno;
	private String memname;
	private String memgender;
	private byte[] memphoto;
	private String mememail;
	private String mempassword;
	private String memtel;
	private Date membd;
	private Integer momopoint;
	private Integer totalclass;
	private Integer memstatus;

	public Integer getMemno() {
		return memno;
	}

	public void setMemno(Integer memno) {
		this.memno = memno;
	}

	public String getMemname() {
		return memname;
	}

	public void setMemname(String memname) {
		this.memname = memname;
	}

	public String getMemgender() {
		return memgender;
	}

	public void setMemgender(String memgender) {
		this.memgender = memgender;
	}

	public byte[] getMemphoto() {
		return memphoto;
	}

	public void setMemphoto(byte[] memphoto) {
		this.memphoto = memphoto;
	}

	public String getMememail() {
		return mememail;
	}

	public void setMememail(String mememail) {
		this.mememail = mememail;
	}

	public String getMempassword() {
		return mempassword;
	}

	public void setMempassword(String mempassword) {
		this.mempassword = mempassword;
	}

	public String getMemtel() {
		return memtel;
	}

	public void setMemtel(String memtel) {
		this.memtel = memtel;
	}

	public Date getMembd() {
		return membd;
	}

	public void setMembd(Date membd) {
		this.membd = membd;
	}

	public Integer getMomopoint() {
		return momopoint;
	}

	public void setMomopoint(Integer momopoint) {
		this.momopoint = momopoint;
	}

	public Integer getTotalclass() {
		return totalclass;
	}

	public void setTotalclass(Integer totalclass) {
		this.totalclass = totalclass;
	}

	public Integer getMemstatus() {
		return memstatus;
	}

	public void setMemstatus(Integer memstatus) {
		this.memstatus = memstatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(memphoto);
		result = prime * result + Objects.hash(membd, mememail, memgender, memname, memno, mempassword, memstatus,
				memtel, momopoint, totalclass);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		memberVO other = (memberVO) obj;
		return Objects.equals(membd, other.membd) && Objects.equals(mememail, other.mememail)
				&& Objects.equals(memgender, other.memgender) && Objects.equals(memname, other.memname)
				&& Objects.equals(memno, other.memno) && Objects.equals(mempassword, other.mempassword)
				&& Arrays.equals(memphoto, other.memphoto) && Objects.equals(memstatus, other.memstatus)
				&& Objects.equals(memtel, other.memtel) && Objects.equals(momopoint, other.momopoint)
				&& Objects.equals(totalclass, other.totalclass);
	}

	@Override
	public String toString() {
		return "memberVO [memno=" + memno + ", memname=" + memname + ", memgender=" + memgender + ", memphoto="
				+ Arrays.toString(memphoto) + ", mememail=" + mememail + ", mempassword=" + mempassword + ", memtel="
				+ memtel + ", membd=" + membd + ", momopoint=" + momopoint + ", totalclass=" + totalclass
				+ ", memstatus=" + memstatus + "]";
	}
}