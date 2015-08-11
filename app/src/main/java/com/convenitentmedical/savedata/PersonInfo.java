package com.convenitentmedical.savedata;

public class PersonInfo {
	private String name;
	private String sex;
	private String age;
	private String idCard;

	private PersonInfo() {
	};

	private static final PersonInfo INFO = new PersonInfo();

	public static PersonInfo getInstance() {
		return INFO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public static PersonInfo getInfo() {
		return INFO;
	}
}
