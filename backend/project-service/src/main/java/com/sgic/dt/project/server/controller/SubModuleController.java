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
import com.sgic.dt.project.server.dto.ModuleDTO;
import com.sgic.dt.project.server.dto.ProjectDTO;
import com.sgic.dt.project.server.dto.SubModuleDTO;
import com.sgic.dt.project.server.entities.Module;
import com.sgic.dt.project.server.entities.Project;
import com.sgic.dt.project.server.entities.SubModule;
import com.sgic.dt.project.server.services.ModuleService;
import com.sgic.dt.project.server.services.ProjectService;
import com.sgic.dt.project.server.services.SubModuleService;
import com.sgic.dt.project.server.util.ErrorCodes;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class SubModuleController {
	@Autowired
	SubModuleService subModuleService;
	
	@Autowired
	ProjectService projectService;
	@Autowired
	ModuleService moduleService;
	
	@Autowired
	ErrorCodes errorMessages;

	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = Logger.getLogger(SubModuleController.class);	
	
	//=============== ADD A SUBMODULE =================================================//
	@PostMapping(value = "/submodule")
	public ResponseEntity<Object> createSubModule(@RequestBody SubModuleDTO subModuleDTO)
	{
		SubModule subModule = mapper.map(subModuleDTO, SubModule.class);		
		subModuleService.createSubModule(subModule);
		return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.CREATED), HttpStatus.CREATED);
	}
	
	//=============== GET ALL SUBMODULES ===============================//
	@GetMapping(value = "/submodule")
	  public ResponseEntity<Object> getSubModules() 
	{
		
		List <SubModule> subModuleList = subModuleService.getAllSubModules();
		List<SubModuleDTO> subModuleDTOList = mapper.map(subModuleList, SubModuleDTO.class);
		for(SubModuleDTO subModuleDTO_Object : subModuleDTOList)
		{
			Project project = projectService.getProjectById(subModuleDTO_Object.getProjectId());
			subModuleDTO_Object.setProjectName(project.getName());
			Module module = moduleService.getModuleById(subModuleDTO_Object.getModuleId());
			subModuleDTO_Object.setModuleName(module.getName());
			//System.out.println("jjjjjjjj "+moduleDTO_Object.getProjectName());
		}
		return new ResponseEntity<>(new ListContentResponse<SubModuleDTO>("List",subModuleDTOList, RestApiResponseStatus.RECEIVED), HttpStatus.OK);
	}
	
	//=============== GET A SUBMODULE BY ID =====================================//
	@GetMapping(value = "/submodule/{id}")
	public ResponseEntity<Object> getSubModuleById(@PathVariable Long id)
	{
		SubModule subModule = subModuleService.getSubModuleById(id);
		SubModuleDTO subModuleDTO = mapper.map(subModule, SubModuleDTO.class);
		Module module = moduleService.getModuleById(subModuleDTO.getModuleId());
		subModuleDTO.setModuleName(module.getName());
		
		return new ResponseEntity<>(new ContentResponse<SubModuleDTO>("Object", subModuleDTO, RestApiResponseStatus.RECEIVED), HttpStatus.OK);
		
	}
	
	//=============== UPDATE SUBMODULE ============================================================//
	@PutMapping(value = "/submodule")
	public ResponseEntity<Object> updateSubModule(@RequestBody SubModuleDTO subModuleDTO)
	{
		SubModule subModule = mapper.map(subModuleDTO, SubModule.class);
		Module module = moduleService.getModuleById(subModuleDTO.getModuleId());
		subModule.setProjectId(module.getProjectId());
		subModuleService.updateSubModule(subModule);
		return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.UPDATED), HttpStatus.OK);
	}
	
	//=============== DELETE SUBMODULE BY ID =========================//
	@DeleteMapping(value = "/submodule/{id}")
	public ResponseEntity<Object> deleteSubModule(@PathVariable Long id) {
		subModuleService.deleteSubModule(id);
		return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.DELETED), HttpStatus.OK);
	}
	
	//=============== TOTAL NUMBER OF SUBMODULES =======================//
	@GetMapping(value = "/submodule/total")
	public Long getTotalSubModules() 
	{
		List <SubModule> subModuleList = subModuleService.getAllSubModules();
		Long totalNum=(long) subModuleList.size();
		return totalNum;
	}
	
	//=============== GET ALL SUBMODULES BY PROJECT ID ===============================//
	@GetMapping(value = "/submodule/byproject/{id}")
	  public ResponseEntity<Object> getALLSubModulesByProjectId(@PathVariable Long id) 
	{
		List <SubModule> subModuleList = subModuleService.getAllSubModulesByProjectId(id);
		List<SubModuleDTO> subModuleDTOList = mapper.map(subModuleList, SubModuleDTO.class);
		//return moduleDTOList;
		return new ResponseEntity<>(new ListContentResponse<SubModuleDTO>("List",subModuleDTOList, RestApiResponseStatus.RECEIVED), HttpStatus.OK);
	}
	
	//=============== GET ALL SUBMODULES BY MODULE ID ===============================//
		@GetMapping(value = "/submodule/bymodule/{id}")
		  public ResponseEntity<Object> getALLSubModulesByModuleId(@PathVariable Long id) 
		{
			List <SubModule> subModuleList = subModuleService.getAllSubModulesByModuleId(id);
			List<SubModuleDTO> subModuleDTOList = mapper.map(subModuleList, SubModuleDTO.class);
			//return moduleDTOList;
			return new ResponseEntity<>(new ListContentResponse<SubModuleDTO>("List",subModuleDTOList, RestApiResponseStatus.RECEIVED), HttpStatus.OK);
		}
		
		@GetMapping(value = "/submodule/name/{id}")
		public String getModuleNameById(@PathVariable Long id)
		{
			SubModule subModule = subModuleService.getSubModuleById(id);
			SubModuleDTO SubModuleDto = mapper.map(subModule, SubModuleDTO.class);
			String name = SubModuleDto.getName();	
			return name;
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
