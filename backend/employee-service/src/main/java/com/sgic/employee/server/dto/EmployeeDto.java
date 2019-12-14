package com.sgic.employee.server.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeDto {
	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;	
	private Long designationId;
	private String designationName;
	private String address;
	private String phoneNumber;
	private String email;
	private String bench;
	private String photo;
	private String active;
	private LocalDate joinDate;
	private LocalDate leaveDate;
	private String username;
	private String password;
	private String remarks;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	
	public Long getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public String getDesignationName() {
		return designationName;
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
	public LocalDate getJoinDate() {
		return joinDate;
	}
	public LocalDate getLeaveDate() {
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
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
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
	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}
	public void setLeaveDate(LocalDate leaveDate) {
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
