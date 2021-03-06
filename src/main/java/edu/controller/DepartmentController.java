package edu.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.model.Department;
import edu.service.DepartmentCRUD;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentCRUD departmentCrudRepo;
	static final Logger logger = LogManager.getLogger(DepartmentController.class.getName());

	@RequestMapping(method=RequestMethod.GET)
	public List<Department> getAllDepartments() {
		List<Department> DepartmentList =new ArrayList<Department>();
		DepartmentList = (List<Department>) departmentCrudRepo.findAll();
		return DepartmentList;
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Department getDepartment(@PathVariable("id") Long id) {
		Department Department=new Department();
		try {
			Department = departmentCrudRepo.findOne(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
		return Department;
	} 

	@RequestMapping(method=RequestMethod.POST)
	public Department createDepartment(@RequestBody Department Department) {
		logger.info("Creating Bill : {}", Department);
		try {
			departmentCrudRepo.save(Department);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return Department;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public Department updateDepartment(@PathVariable("id") Long id,@RequestBody Department updateDepartment) {
		updateDepartment.setId(id);
		departmentCrudRepo.save(updateDepartment);
		return updateDepartment;
	}
}
