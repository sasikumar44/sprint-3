package com.sgic.employee.server.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(schema = "employee-service", name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private LocalDateTime dateOfBirth;
	@NotNull
	private Long designationId;
	@NotNull
	private String address;
	@NotNull
	private String phoneNumber;
	@NotNull
	private String email;
	@NotNull
	private String bench;
	@Lob
	@Column
	private String photo;
	@NotNull
	private String active;
	@NotNull
	private LocalDateTime joinDate;
	private LocalDateTime leaveDate;
	@NotNull
	private String username;
	@NotNull
	private String password;
	private String remarks;
	@CreationTimestamp
	private LocalDateTime createdOn;
	@UpdateTimestamp
	private LocalDateTime updatedOn;
	
	public Employee() {
		
	}

	public Employee(Long id, @NotNull String firstName, @NotNull String lastName, @NotNull LocalDateTime dateOfBirth,
			@NotNull Long designationId, @NotNull String address, @NotNull String phoneNumber, @NotNull String email,
			@NotNull String bench, String photo, @NotNull String active, @NotNull LocalDateTime joinDate,
			LocalDateTime leaveDate, @NotNull String username, @NotNull String password, String remarks,
			LocalDateTime createdOn, LocalDateTime updatedOn) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.designationId = designationId;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.bench = bench;
		this.photo = photo;
		this.active = active;
		this.joinDate = joinDate;
		this.leaveDate = leaveDate;
		this.username = username;
		this.password = password;
		this.remarks = remarks;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public Long getDesignationId() {
		return designationId;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getBench() {
		return bench;
	}

	public String getPhoto() {
		return photo;
	}

	public String getActive() {
		return active;
	}

	public LocalDateTime getJoinDate() {
		return joinDate;
	}

	public LocalDateTime getLeaveDate() {
		return leaveDate;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRemarks() {
		return remarks;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBench(String bench) {
		this.bench = bench;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public void setJoinDate(LocalDateTime joinDate) {
		this.joinDate = joinDate;
	}

	public void setLeaveDate(LocalDateTime leaveDate) {
		this.leaveDate = leaveDate;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	
}
