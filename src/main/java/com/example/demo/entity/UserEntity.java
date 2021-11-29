package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.example.demo.dto.User;


@Entity
@Table(name = "users")
public class UserEntity {
	private int id;
    private String firstName;
    private String lastName;

    public UserEntity() {
    }
    public UserEntity(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
//other setters and getters

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setId(Integer id2) {
		this.id=id2;
		
	}

	public  User todto(){
		User user = new User();
		user.setFirstName(this.firstName);
		user.setLastName(this.lastName);
		return user;
	}

}
