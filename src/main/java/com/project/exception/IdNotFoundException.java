package com.project.exception;

public class IdNotFoundException extends RuntimeException
{
   @Override
   public String getMessage()
   {
	   return "Id is not Present " ;
   }
}
