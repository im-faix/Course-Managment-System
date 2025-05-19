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
import com.project.entity.Instructor;
import com.project.entity.Student;
import com.project.service.InstructorService;

@RestController
@RequestMapping("/instructor")
public class InstructorController 
{
	@Autowired
	private InstructorService instructorService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Instructor>> saveInstructor(@RequestBody Instructor instructor)
	{
		return instructorService.saveInstructor(instructor);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Instructor>>> getAllInstructor()
	{
		return instructorService.getAllInstructor();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Instructor>> getInstructorId(@PathVariable Integer id)
	{
		return instructorService.getInstructorById(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Instructor>> updateInstructor(@RequestBody Instructor instructor)
	{
		return instructorService.saveInstructor(instructor);
	} 
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteInstructor(@PathVariable Integer id)
	{
		return instructorService.deleteInstructor(id);
	}
	
	@GetMapping("/inst/{id}")
	public ResponseEntity<ResponseStructure<List<Student>>> getStudentByInstructorId(@PathVariable Integer id)
	{
		return instructorService.getStudentByInstructorId(id);
	}
	
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Instructor>>> getInstructorByPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field) 
	{
		return instructorService.getInstructorByPaginationAndSorting(pageNumber, pageSize, field);
	}
}
