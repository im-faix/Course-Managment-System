package com.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.project.entity.Course;
import com.project.entity.Student;
import com.project.repository.StudentRepository;

@Repository
public class StudentDao 
{
	@Autowired
	private StudentRepository repository;
	
	public Student addStudent(Student student)
	{
		return repository.save(student);
	}
	
	public List<Student> getAllStudent()
	{
		return repository.findAll();
	}
	
	public Optional<Student> getStudentById(Integer Id)
	{
		return repository.findById(Id);
	}
	
	public void deleteStudent(Student student)
	{
		 repository.delete(student);
	}
	
	
	public List<Course> getCourseBtStudentId(Integer id)
	{
		return repository.getCourseByStudentId(id);
	}

	public Page<Student> getStudentByPaginationAndSorting(int pageNumber, int pageSize, String field)
	{
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending());
	    return repository.findAll(pageable);
	}

}
