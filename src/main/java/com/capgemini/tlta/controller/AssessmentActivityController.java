package com.capgemini.tlta.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capgemini.tlta.exception.AssesmentException;
import com.capgemini.tlta.model.Assessment;
import com.capgemini.tlta.sevice.AssessmentActivityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/api/assessments")
//@Slf4j
public class AssessmentActivityController {
	//Autowired byName
	//@Resource(name = "productServiceSpringData")
	@Autowired(required = false)
	@Qualifier(value = "assessmentActivityService")
	private AssessmentActivityService assessmentService;

	//get assessments by Id
	//http://localhost:8081/springfox/api/assessments/1
	@ApiOperation(value = "Get Product By Id",
			response = Assessment.class,
			tags = "get-Assessment",
			consumes = "assessmentId",
			httpMethod = "GET") 
	@GetMapping("/{id}")
	public ResponseEntity<Assessment> getAssessmentById(@PathVariable Integer id){
		try {
			Assessment assessment= assessmentService.searchAssessmentActivityById(id);
//			log.info("Product added"+ product);
			return new ResponseEntity<>(assessment,HttpStatus.OK);
		}catch(AssesmentException e) {
//			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//get all assessments
	//http://localhost:8081/springfox/api/assessments/
	@ApiOperation(value = "Get All Assessment",
			response = Assessment.class,
			tags = "get-Assessment",
			httpMethod = "GET") 
	
	@GetMapping("/")
	public ResponseEntity<List<Assessment>> getAllAssessments(){
		try {
			List<Assessment> assessmentList = assessmentService.getAllAssessmentActivity();
//			log.info("Returning all product details");
			return new ResponseEntity<>(assessmentList,HttpStatus.OK);
		}catch(AssesmentException e) {
//			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//http://localhost:8081/springfox/api/assessments/
	//add product	
	@ApiOperation(value = "Add Assessment",
			response = String.class,
			tags = "get-Assessment",
			consumes = "receives Assessment object as request body",
			httpMethod = "POST") 
	
	@PostMapping("/")
	public String addAssessment(@RequestBody Assessment assessment) {
		try {
			Assessment status= assessmentService.addAssessmentActivity(assessment);
			if(status != null) {
//				log.info("product:"+product.getProductName()+" added to database");
				return "product:"+assessment.getAssesment_name()+" added to database";
			}else {
//				log.debug("Unable to add product");
				return "Unable to add product to database";
			}

		}catch(AssesmentException e) {
//			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//http://localhost:8081/springfox/api/products/1
	//delete product
	@ApiOperation(value = "Delete Product By Id",
			response = String.class,
			tags = "delete-product",
			consumes = "product Id",
			httpMethod = "DELETE") 
	
	@DeleteMapping("/{id}")
	public String deleteAssessment(@PathVariable Integer id) {
		try {
			Integer status= assessmentService.deleteAssessmentActivity(id);
			if(status ==1) {
//				log.info("product: "+id+" deleted from database");
				return "Assessment: "+id+" deleted from database";
			}else {
//				log.debug("Unable to delete product from database");
				return "Unable to delete Assessment from database";
			}

		}catch(AssesmentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//http://localhost:8081/springfox/api/products/
	//update product
	@ApiOperation(value = "Update Assessment",
			response = Assessment.class,
			tags = "update-Assessment",
			consumes = "Assessment object sents as response body",
			httpMethod = "PUT") 
	@PutMapping("/")
	public ResponseEntity<Assessment> updateAssessment(@RequestBody Assessment assessment) {
		try {
			Assessment updatedAssessment= assessmentService.updateAssessmentActivity(assessment);
//			log.info("Product: "+ product.getProductId()+ " updated");
			return new ResponseEntity<>(updatedAssessment,HttpStatus.OK);

		}catch(AssesmentException e) {
//			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

}
