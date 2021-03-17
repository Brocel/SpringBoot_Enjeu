package com.brocels.springboot.enjeu.domain;

import javax.validation.constraints.NotBlank;

public class Player {

	private Integer id;
	
	@NotBlank(message = "Please enter a name")
	private String name;
	
	private Integer age;
	private String city;
	private String country;
	private String avatarUrl;
	
	@NotBlank(message = "Please enter an email")
	private String eMail;
	
	public Player() {
		
	}

	public Player(Integer id, String name, Integer age, String city,
			String country, String avatarUrl, String eMail) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.city = city;
		this.country = country;
		this.avatarUrl = avatarUrl;
		this.eMail = eMail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
}
