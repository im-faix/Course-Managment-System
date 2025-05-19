package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.dao.CourseDao;
import com.project.dao.InstructorDao;
import com.project.dto.ResponseStructure;
import com.project.entity.Course;
import com.project.entity.Instructor;
import com.project.exception.IdNotFoundException;

@Service
public class CourseService
{
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private InstructorDao instructorDao;
	
	public ResponseEntity<ResponseStructure<Course>> createCourse(Course course)
	{
		Optional<Instructor> instr = instructorDao.getInstructorById(course.getInstructor().getId());
		ResponseStructure<Course> structure = new ResponseStructure<Course>();
		if(instr.isPresent())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("1 Row Added ");
			course.setInstructor(instr.get());
			structure.setData(courseDao.createCourse(course));
			
			return new ResponseEntity<ResponseStructure<Course>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteCourse(Integer id)
	{
		Optional<Course> ot = courseDao.getCourseById(id);
		ResponseStructure<String> structure = new ResponseStructure<String>();
		if(ot.isPresent())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Deleted ");
			courseDao.deleteCourse(id);
			structure.setData("Deleted 1 Record");
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
		}
		else
		{
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found ");
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Course>> getCourseById(Integer id)
	{
		Optional<Course> ot = courseDao.getCourseById(id);
		ResponseStructure<Course> structure = new ResponseStructure<Course>();
		if(ot.isPresent())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Fetched ");
			structure.setData(ot.get());
			return new ResponseEntity<ResponseStructure<Course>>(structure,HttpStatus.OK);
		}
		else
		{
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found ");
			return new ResponseEntity<ResponseStructure<Course>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Course>>> getAllCourse()
	{
		List<Course> ls = courseDao.getAllCourse();
		ResponseStructure<List<Course>> structure = new ResponseStructure<List<Course>>();
		if(!ls.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Fetched ");
			structure.setData(ls);
			return new ResponseEntity<ResponseStructure<List<Course>>>(structure,HttpStatus.OK);
		}
		else
		{
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found ");
			return new ResponseEntity<ResponseStructure<List<Course>>>(structure,HttpStatus.NOT_FOUND);
		}
		
	}
 
	public ResponseEntity<ResponseStructure<Course>> updateCourse(Course course)
	{
		ResponseStructure<Course> structure = new ResponseStructure<Course>();
		if (course.getId() == null) {
	        structure.setStatusCode(HttpStatus.CREATED.value());
	        structure.setMessage("New Record Created");
	        structure.setData(courseDao.createCourse(course)); // Assuming addStudent handles new records
	        return new ResponseEntity<>(structure, HttpStatus.CREATED);
	    }

	    // Fetch Course by ID
	    Optional<Course> op = courseDao.getCourseById(course.getId());

	    
	    Optional<Instructor> ot = instructorDao.getInstructorById(course.getInstructor().getId());
	    
	    if (op.isPresent()) 
	    {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Updated");
	        if(ot.isPresent())
	        {
	        	structure.setData(courseDao.createCourse(course)); 
		        return new ResponseEntity<ResponseStructure<Course>>(structure, HttpStatus.OK);
	        }
	        else
	        {
	        	throw new IdNotFoundException();
	        }  
	    } 
	    else
	    {
	    	throw new IdNotFoundException();
	    }
	}
	
	public ResponseEntity<ResponseStructure<List<Course>>> getCourseByInstructorId(Integer id)
	{
		List<Course> ls = courseDao.getCourseByInstructorId(id);
		ResponseStructure<List<Course>> structure = new ResponseStructure<List<Course>>();
		if(!ls.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Fetched");
			structure.setData(ls);
			
			return new ResponseEntity<ResponseStructure<List<Course>>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new IdNotFoundException();
		}
	}
	
public ResponseEntity<ResponseStructure<Page<Course>>> getCourseByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	    
//	    Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending());
	    Page<Course> page = courseDao.getCourseByPaginationAndSorting(pageNumber, pageSize, field);
	    
	    ResponseStructure<Page<Course>> structure = new ResponseStructure<>();
	    
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
