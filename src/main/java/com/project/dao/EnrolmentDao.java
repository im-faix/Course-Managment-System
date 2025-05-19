package com.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.project.entity.Enrolment;
import com.project.repository.EnrolmentRepository;

@Repository
public class EnrolmentDao 
{
	@Autowired
	private EnrolmentRepository repository;
	
	public Enrolment enrollStudent(Enrolment enrolment)
	{
		return repository.save(enrolment);
	}
	
	public List<Enrolment> getAllEnrolment()
	{
		return repository.findAll();
	}
	
	public Optional<Enrolment> getEnrolmentById(Integer id)
	{
		return repository.findById(id);
	}
	
	public void deleteEnrolment(Integer id)
	{
		repository.deleteById(id);
	}
	
	public List<Enrolment> getEnrolmentByStudentId(Integer id)
	{
		return repository.getEnrolmentByStudentId(id);
	}
	
	public List<Enrolment> getEnrolmentByCourseId(Integer id)
	{
		return repository.getEnrolmentByCourseId(id);
	}
	
	public Page<Enrolment> getEnrolmentByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending());
	    return repository.findAll(pageable);
	}

}
