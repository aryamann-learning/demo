package com.example.demo.entity;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_laptops")

public class LaptopEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String brand;
	private String description;
	private BigDecimal price;
	private Date createdAt;

	public LaptopEntity() {
		super();
	}

	public LaptopEntity(Long id, String name, String brand, String description, BigDecimal price, Date createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Laptop [id=" + id + ", name=" + name + ", brand=" + brand + ", description=" + description + ", price="
				+ price + ", createdAt=" + createdAt + "]";
	}

	
}