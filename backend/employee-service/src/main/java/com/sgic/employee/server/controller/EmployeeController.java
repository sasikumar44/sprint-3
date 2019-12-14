package com.sgic.employee.server.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.sgic.common.api.response.BasicResponse;
import com.sgic.common.api.response.ContentResponse;
import com.sgic.common.api.response.ListContentResponse;
import com.sgic.common.api.response.ValidationFailure;
import com.sgic.employee.dto.mapper.Mapper;
import com.sgic.employee.server.dto.EmployeeDto;
import com.sgic.employee.server.entities.Designation;
import com.sgic.employee.server.entities.Employee;
import com.sgic.employee.server.services.DesignationService;
import com.sgic.employee.server.services.EmployeeService;
import com.sgic.employee.server.util.Constants;
import com.sgic.employee.server.util.ErrorCodes;
import com.sgic.employee.server.util.ValidationMessages;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DesignationService designationService;

	@Autowired
	ErrorCodes errorMessages;

	@Autowired
	private Mapper mapper;

	private static final Logger logger = Logger.getLogger(EmployeeController.class);
	
	// ADD EMPLOYEE =========================================================================================================

	@PostMapping(value = "/employee")
	public ResponseEntity<Object> createEmployee(@RequestBody EmployeeDto employeeData) {
		if (employeeService.isEmailAlreadyExist(employeeData.getEmail())) {
			logger.debug("Email already exists: createEmployee(), email: {}");
			return new ResponseEntity<>(
					new BasicResponse<>(new ValidationFailure(Constants.EMAIL, errorMessages.getEmailAlreadyExist()),
							RestApiResponseStatus.VALIDATION_FAILURE, ValidationMessages.EMAIL_EXIST),
					HttpStatus.BAD_REQUEST);
		}

		Employee employee = mapper.map(employeeData, Employee.class);
		employeeService.createEmployee(employee);
		return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.CREATED), HttpStatus.OK);
	}
	
	// ADD EMPLOYEE END =====================================================================================================
	
	// LIST ALL EMPLOYEE ====================================================================================================

	@GetMapping(value = "/employee")
	public ResponseEntity<Object> getEmployee() {
		List<Employee> employeeData = employeeService.getAllEmployee();
		List<EmployeeDto> employeeDtoData = mapper.map(employeeData, EmployeeDto.class);
		System.out.println(employeeDtoData);
		for(EmployeeDto employeeDto : employeeDtoData) {
			Designation designation = designationService.findDesignationById(employeeDto.getDesignationId());
			employeeDto.setDesignationName(designation.getDesignationName());
		}
		return new ResponseEntity<>(new ListContentResponse<EmployeeDto>("listAllEmployee",employeeDtoData, RestApiResponseStatus.RECEIVED), HttpStatus.OK);	
	}
	
	// LIST ALL EMPLOYEE END ================================================================================================
	
	// GET EMPLOYEE BY ID ===================================================================================================

	@GetMapping(value = "/employee/{id}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
		EmployeeDto employeeDtoData = mapper.map(employeeService.findEmployeeById(id), EmployeeDto.class);
			Designation designation = designationService.findDesignationById(employeeDtoData.getDesignationId());
			employeeDtoData.setDesignationName(designation.getDesignationName());
			return new ResponseEntity<>(new ContentResponse<EmployeeDto>("listEmployee", employeeDtoData, RestApiResponseStatus.RECEIVED), HttpStatus.OK);	
	}
	
	// GET EMPLOYEE BY ID END ===============================================================================================
	
	// UPDATE EMPLOYEE ======================================================================================================

	@PutMapping(value = "/employee")
	public ResponseEntity<Object> updateBook(@Valid @RequestBody EmployeeDto employeeData) {
		Employee employee = mapper.map(employeeData, Employee.class);
		employeeService.updateEmployee(employee);
		return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.UPDATED), HttpStatus.OK);
		// return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.OK),
		// HttpStatus.OK);
	}
	
	// UPDATE EMPLOYEE END ==================================================================================================
	
	// DELETE EMPLOYEE ======================================================================================================

	@DeleteMapping(value = "/employee/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.DELETED), HttpStatus.OK);
	}
	
	// DELETE EMPLOYEE END ==================================================================================================
	
	// LIST EMPLOYEE BY DESIGNATION =========================================================================================

//	@GetMapping(value = "/employee/job/{designation}")
//	public ResponseEntity<Object> getEmployeeByDesignation(@PathVariable Long designationId) {
//		List<EmployeeDto> employeeDtoData = mapper.map(employeeService.findEmployeeByDesignation(designationId),
//				EmployeeDto.class);
//		return new ResponseEntity<>(new ListContentResponse<EmployeeDto>("listEmployee",employeeDtoData, RestApiResponseStatus.RECEIVED), HttpStatus.OK);
//	}
	
	// LIST EMPLOYEE BY DESIGNATION END =====================================================================================

	@GetMapping(value = "/employee/name/{id}")
	public String getEmployeeNameById(@PathVariable Long id)
	{
		Employee employee = employeeService.findEmployeeById(id);
		EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
		String uname = employeeDto.getUsername();
		return uname;
	}
}
