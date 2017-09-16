package com.thorvey.usermgmtsvc;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private String profession;

	public User() {
	}

	public User(int id, String name, String profession) {
		this.id = id;
		this.name = name;
		this.profession = profession;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else if (!(object instanceof User)) {
		} else {
			User user = (User) object;
			if (id == user.getId() && name.equals(user.getName()) && profession.equals(user.getProfession())) {
				return true;
			}
		}
		return false;
	}

}