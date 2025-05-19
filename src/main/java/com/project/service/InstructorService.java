package com.project.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.dao.InstructorDao;
import com.project.dto.ResponseStructure;
import com.project.entity.Instructor;
import com.project.entity.Student;

@Service
public class InstructorService 
{

	@Autowired
	private InstructorDao instructorDao;
	
	public ResponseEntity<ResponseStructure<Instructor>> saveInstructor(Instructor instructor)
	{
		ResponseStructure<Instructor> structure = new ResponseStructure<Instructor>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("1 Record Added");
		structure.setData(instructorDao.saveInstructor(instructor));
		
		return new ResponseEntity<ResponseStructure<Instructor>>(structure,HttpStatus.OK);
	}
	
	
	public ResponseEntity<ResponseStructure<List<Instructor>>> getAllInstructor()
	{
		List<Instructor> ls = instructorDao.getAllInstructor();
		ResponseStructure<List<Instructor>> structure = new ResponseStructure<List<Instructor>>();
		if(!ls.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("All Records Fetched");
			structure.setData(ls);
			
			return new ResponseEntity<ResponseStructure<List<Instructor>>>(structure,HttpStatus.OK);
		}
		else
		{
			structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			structure.setMessage("No Records are present ");
			return new ResponseEntity<ResponseStructure<List<Instructor>>>(structure,HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<ResponseStructure<Instructor>> getInstructorById(Integer id)
	{
		Optional<Instructor> ot = instructorDao.getInstructorById(id);
		ResponseStructure<Instructor> structure = new ResponseStructure<Instructor>();
		if(ot.isPresent())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Record Fetched");
			structure.setData(ot.get());
			return new ResponseEntity<ResponseStructure<Instructor>>(structure,HttpStatus.OK);
		}
		else
		{
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("No Records Found");
			
			return new ResponseEntity<ResponseStructure<Instructor>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<Instructor>> updateInstructor(Instructor instructor) {
	    ResponseStructure<Instructor> structure = new ResponseStructure<>();

	    if (instructor.getId() == null) {
	        structure.setStatusCode(HttpStatus.CREATED.value());
	        structure.setMessage("Updated");
	        structure.setData(instructorDao.saveInstructor(instructor)); 
	        return new ResponseEntity<>(structure, HttpStatus.CREATED);
	    }

	    Optional<Instructor> op = instructorDao.getInstructorById(instructor.getId());

	    if (op.isPresent()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Updated");
	        structure.setData(instructorDao.saveInstructor(instructor)); 
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value()); 
	        structure.setMessage("Student ID not found, cannot update");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteInstructor(Integer id)
	{
		Optional<Instructor> ot = instructorDao.getInstructorById(id);
		ResponseStructure<String> structure = new ResponseStructure<String>();
		if(ot.isPresent())
		{
				structure.setStatusCode(HttpStatus.OK.value());
		        structure.setMessage("Deleted");
		        instructorDao.deleteInstructor(id);
		        structure.setData("1 Record Deleted");
		      return new ResponseEntity<>(structure, HttpStatus.OK);     
		}
		else
		{
			  structure.setStatusCode(HttpStatus.NOT_FOUND.value()); 
		        structure.setMessage("Instructor ID not found, cannot deleted");
		        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
		}
		
	}

	public ResponseEntity<ResponseStructure<List<Student>>> getStudentByInstructorId(Integer id)
	{
		List<Student> ls = instructorDao.getStudentByInstructorId(id);
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		if(!ls.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Fetched");
			structure.setData(ls);
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.OK);
		}
		else
		{
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("No Record Found");
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<ResponseStructure<Page<Instructor>>> getInstructorByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	    
//	    Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending());
	    Page<Instructor> page = instructorDao.getInstructorByPaginationAndSorting(pageNumber, pageSize, field);
	    
	    ResponseStructure<Page<Instructor>> structure = new ResponseStructure<>();
	    
	    if (!page.isEmpty()) {
	        structure.setStatusCode(HttpStatus.FOUND.value());
	        structure.setMessage("Fetched all the records");
	        structure.setData(page);
	        return new ResponseEntity<>(structure, HttpStatus.FOUND);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("No records found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}
	
}
