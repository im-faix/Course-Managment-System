package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ResponseStructure;
import com.project.entity.Enrolment;
import com.project.service.EnrolmentService;

@RestController
@RequestMapping("/enrolment")
public class EnrolmentController 
{
	@Autowired
	private EnrolmentService enrolmentService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Enrolment>> enrollStudent(@RequestBody Enrolment enrolment)
	{
		return enrolmentService.enrollStudent(enrolment);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Enrolment>>> getAllEnrolment()
	{
		return enrolmentService.getAllEnrolment();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Enrolment>> getEnrolmentById(@PathVariable Integer id)
	{
		return enrolmentService.getEnrolmentById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteEnrolment(@PathVariable Integer id)
	{
		return enrolmentService.deleteEnrolment(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Enrolment>> updateEnrolment(@RequestBody Enrolment enrolment)
	{
		return enrolmentService.updateEnrolment(enrolment);
	}
	
	@GetMapping("/std/{id}")
	public ResponseEntity<ResponseStructure<List<Enrolment>>> getEnrolmentByStudentId(@PathVariable Integer id)
	{
		return enrolmentService.getEnrolmentByStudentId(id);
	}
	
	@GetMapping("/cor/{id}")
	public ResponseEntity<ResponseStructure<List<Enrolment>>> getEnrolmentByCourseId(@PathVariable Integer id)
	{
		return enrolmentService.getEnrolmentByCourseId(id);
	}
	
	@GetMapping("/enroll/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Enrolment>>> getEnrolmentByPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field)
	{
		return enrolmentService.getEnrolmentByPaginationAndSorting(pageNumber, pageSize, field);
	}
	
}
