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
import com.project.entity.Course;
import com.project.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController 
{

	@Autowired
	private CourseService courseService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Course>> createCourse(@RequestBody Course course)
	{
		return courseService.createCourse(course);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteCourse(@PathVariable Integer id)
	{
		return courseService.deleteCourse(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Course>> getCourseById(@PathVariable Integer id)
	{
		return courseService.getCourseById(id);
	}
	
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Course>>> getAllCourse()
	{
		return courseService.getAllCourse();
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Course>> updateCourse(@RequestBody Course course)
	{
		return courseService.updateCourse(course);
	}
	
	@GetMapping("/ins/{id}")
	public ResponseEntity<ResponseStructure<List<Course>>> getCourseByInstructorId(@PathVariable Integer id)
	{
		return courseService.getCourseByInstructorId(id);
	}
	
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Course>>> getCourseByPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field)
	{
		return courseService.getCourseByPaginationAndSorting(pageNumber, pageSize, field);
	}
}
