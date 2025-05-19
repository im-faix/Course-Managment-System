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
import com.project.repository.CourseRepository;

@Repository
public class CourseDao 
{
	@Autowired
	private CourseRepository repository;
	
	public Course createCourse(Course course)
	{
		return repository.save(course);
	}
	
	public void deleteCourse(Integer id)
	{
		 repository.deleteById(id);
	}
	
	public Optional<Course> getCourseById(Integer id)
	{
		return repository.findById(id);
	}
	
	public List<Course> getAllCourse()
	{
		return repository.findAll();
	}
	
	public List<Course> getCourseByInstructorId(Integer id)
	{
		return repository.getCourseByInstructorId(id);
	}
	
	public Page<Course> getCourseByPaginationAndSorting(int pageNumber, int pageSize, String field)
	{
	    Pageable pageable = PageRequest.of(pageNumber, pageSize,Sort.by(field).ascending());
	    return repository.findAll(pageable);
	}
}
