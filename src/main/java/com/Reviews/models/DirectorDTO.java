package com.Reviews.models;

import javax.validation.constraints.NotEmpty;

public class DirectorDTO {
	private Integer id;
	@NotEmpty(message="{api.Director_name}")
	private String name;
	@NotEmpty(message="{api.Director_color}")
	private String hairColor;
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
	public String getHairColor() {
		return hairColor;
	}
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

}
