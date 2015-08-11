package com.convenitentmedical.savedata;
/**
 * 挂号的信息集
 * @author Mr.Codey
 *
 */
public class RegInfo {
	private String area="";
	private String hospital="";
	private String department="";
	private String doctor="";
	private RegInfo(){};
	private static final RegInfo INFO=new RegInfo();
	/**
	 * 单例模式设置
	 * @return
	 */
	public static RegInfo getInstanse()
	{
		return INFO;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

}
