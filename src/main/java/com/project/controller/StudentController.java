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
import com.project.entity.Student;
import com.project.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController 
{
	@Autowired
	private StudentService studentService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Student>> addStudent(@RequestBody Student student)
	{
		return studentService.addStudent(student);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Student>>> getAllStudents()
	{
		return studentService.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Student>> getStudentById(@PathVariable Integer id)
	{
		return studentService.getStudentById(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Student>> updateStudent(@RequestBody Student student)
	{
		return studentService.updateStudent(student);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteStudent(@PathVariable Integer id)
	{
		return studentService.deleteStudent(id);
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<ResponseStructure<List<Course>>> getCourseByStudentId(@PathVariable Integer id)
	{
		return studentService.getCourseByStudentId(id);
	}
	
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Student>>> getStudentByPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field) 
	{
		return studentService.getStudentByPaginationAndSorting(pageNumber, pageSize, field);
	}
	
}
