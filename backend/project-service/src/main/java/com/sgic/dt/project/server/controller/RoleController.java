package com.sgic.dt.project.server.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgic.common.api.enums.RestApiResponseStatus;
import com.sgic.common.api.response.ApiResponse;
import com.sgic.common.api.response.ContentResponse;
import com.sgic.common.api.response.ListContentResponse;
import com.sgic.dt.project.dto.mapper.Mapper;

import com.sgic.dt.project.server.dto.RoleDTO;
import com.sgic.dt.project.server.entities.Module;
import com.sgic.dt.project.server.entities.Project;
import com.sgic.dt.project.server.entities.Role;
import com.sgic.dt.project.server.services.RoleService;
import com.sgic.dt.project.server.util.ErrorCodes;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class RoleController {
	@Autowired
	RoleService roleService;
	

	
	@Autowired
	ErrorCodes errorMessages;

	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = Logger.getLogger(RoleController.class);	
	
	//=============== ADD A ROLE =================================================//
	@PostMapping(value = "/role")
	public ResponseEntity<Object> createRole(@RequestBody RoleDTO roleDTO)
	{
		Role role = mapper.map(roleDTO, Role.class);
		roleService.createRole(role);
		return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.CREATED), HttpStatus.CREATED);
	}
	
	//=============== GET ALL ROLES ===============================//
	@GetMapping(value = "/role")
	  public ResponseEntity<Object> getRoles() 
	{
		
		List <Role> roleList = roleService.getAllRoles();
		List<RoleDTO> roleDTOList = mapper.map(roleList, RoleDTO.class);
		
		//Module module = moduleService.getModuleById(id);
		return new ResponseEntity<>(new ListContentResponse<RoleDTO>("List",roleDTOList, RestApiResponseStatus.RECEIVED), HttpStatus.OK);
	}
	
	//=============== GET A ROLE BY ID =====================================//
	@GetMapping(value = "/role/{id}")
	public ResponseEntity<Object> getRoleById(@PathVariable Long id)
	{
		Role role = roleService.getRoleById(id);
		
		RoleDTO roleDTO = mapper.map(role, RoleDTO.class);
		//return new ResponseEntity<ModuleDTO>(moduleDTO, HttpStatus.OK);
		return new ResponseEntity<>(new ContentResponse<RoleDTO>("Object", roleDTO, RestApiResponseStatus.RECEIVED), HttpStatus.OK);
		
	}
	
	//=============== UPDATE ROLE ============================================================//
	@PutMapping(value = "/role")
	public ResponseEntity<Object> updateRole(@RequestBody RoleDTO roleDTO)
	{
		Role role = mapper.map(roleDTO, Role.class);
		roleService.updateRole(role);
		return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.UPDATED), HttpStatus.OK);
	}
	
	//=============== DELETE ROLE BY ID =========================//
	@DeleteMapping(value = "/role/{id}")
	public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
		return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.DELETED), HttpStatus.OK);
	}
	
	//=============== TOTAL NUMBER OF ROLES =======================//
	@GetMapping(value = "/role/total")
	public Long getTotalRoles() 
	{
		List <Role> roleList = roleService.getAllRoles();
		Long totalNum=(long) roleList.size();
		return totalNum;
	}
	
	
	/*
	
	//=============== NUMBER OF INPROCESS PROJECTS =======================//
	@GetMapping(value = "/project/inprocess")
	public Long getInProcessProjects() 
	{
		Long num  = projectService.getNumberOfInprocess();
		System.out.println(projectService.getNumberOfInprocess());
		//Long totalNum=(long) projectList.size();
		return num;
	}
	//=============== NUMBER OF INPROCESS PROJECTS =======================//
	@GetMapping(value = "/project/completed")
	public Long getCompletedProjects() 
	{
		Long num  = projectService.getNumberOfCompleted();
		//System.out.println(projectService.getNumberOfInprocess());
		//Long totalNum=(long) projectList.size();
		return num;
	}
*/
	
}
