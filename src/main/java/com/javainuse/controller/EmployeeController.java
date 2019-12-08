package com.javainuse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.javainuse.ExceptionResponse;
import com.javainuse.exceptionhandling.ResourceNotFoundException;
import com.javainuse.model.Employee;
import com.javainuse.service.EmployeeService;
import com.javainuse.service.EmployeeServiceException;

@RestController
public class EmployeeController implements ErrorController{

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public Employee getEmpl() throws ResourceNotFoundException, EmployeeServiceException {

		Employee emp = employeeService.getEmployee();

		if (emp == null) {
			throw new ResourceNotFoundException("Employee not found");
		}
		return emp;
	}

	@RequestMapping(value = "/employee2", method = RequestMethod.GET)
	public Employee getEmp2() throws ResourceNotFoundException, EmployeeServiceException {

		Employee emp = employeeService.getEmployeeNull();
		if (emp == null) {
			throw new ResourceNotFoundException("Employee not found");
		}

		return emp;
	}

	@RequestMapping(value = "/employee3", method = RequestMethod.GET)
	public Employee getEmp3() throws ResourceNotFoundException, EmployeeServiceException {
		try {
			Employee emp = employeeService.getEmployeeException();
			if (emp == null) {
				throw new ResourceNotFoundException("Employee not found");
			}
			return emp;
		} catch (EmployeeServiceException e) {
			throw new EmployeeServiceException("Internal Server Exception while getting exception");
		}
	}
	
	@RequestMapping(value = "/employee4", method = RequestMethod.GET)
	public void  getEmp3(HttpServletResponse response) throws Exception {
		response.sendError(response.SC_BAD_REQUEST);
	}
	
	 @Autowired private ErrorAttributes errorAttributes;

	  public void setErrorAttributes(ErrorAttributes errorAttributes) {
	    this.errorAttributes = errorAttributes;
	  }
	  
	  @PostMapping(value = "/employees")
	  public ResponseEntity<EmployeeVO> addEmployee (@Valid @RequestBody EmployeeVO employee)
	  {
	       System.out.println("Invalid checking ");
	      return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
	  }
	  
	  @RequestMapping(value = "error")
	  @ResponseBody
	  public ExceptionResponse error(WebRequest webRequest, HttpServletResponse response) {
		  System.out.println("error Page so JSON Goes from Here");
	    return new ExceptionResponse(response.getStatus(), getErrorAttributes(webRequest));
	  }

	  @Override
	  public String getErrorPath() {
	    return "error";
	  }

	  private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
	    Map<String, Object> errorMap = new HashMap<>();
	    errorMap.putAll(errorAttributes.getErrorAttributes(webRequest, false));
	    return errorMap;
	  }
}
