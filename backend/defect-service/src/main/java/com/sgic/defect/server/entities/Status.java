package com.sgic.defect.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "defect-service", name = "status")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String description;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
//	@JsonIgnore
//	private List<Defect> defect;

	public Status() {

	}

	public Status(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

//	public List<Defect> getDefect() {
//		return defect;
//	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public void setDefect(List<Defect> defect) {
//		this.defect = defect;
//	}
	
}
