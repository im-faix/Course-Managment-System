package com.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.project.entity.Instructor;
import com.project.entity.Student;
import com.project.repository.InstructorRepository;

@Repository
public class InstructorDao 
{
	@Autowired
	private InstructorRepository repository;

	public Instructor saveInstructor(Instructor instructor)
	{
		return repository.save(instructor);
	}
	
	public List<Instructor> getAllInstructor()
	{
		return repository.findAll();
	}
	
	public Optional<Instructor> getInstructorById(Integer id)
	{
		return repository.findById(id);
	}
	
	public void deleteInstructor(Integer id)
	{
		repository.deleteById(id);
	}
	
	public List<Student> getStudentByInstructorId(Integer id)
	{
		return repository.getStudentByInstructorId(id);
	}
	public Page<Instructor> getInstructorByPaginationAndSorting(int pageNumber, int pageSize, String field)
	{
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending());
	    return repository.findAll(pageable);
	}
}
