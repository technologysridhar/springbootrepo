package com.javainuse.controller;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeVO implements Serializable
{
    private static final long serialVersionUID = 1L;
     
    public EmployeeVO(Integer id, String firstName, String lastName, String email) {
        super();
        this.employeeId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
 
    public EmployeeVO() {
    }
 
    private Integer employeeId;
 
    @NotEmpty(message = "first name must not be empty")
    private String firstName;
 
    @NotEmpty(message = "last name must not be empty")
    private String lastName;
 
    @NotEmpty(message = "email must not be empty")
    @Email(message = "email should be a valid email")
    private String email;
     
    //Removed setter/getter for readability
}