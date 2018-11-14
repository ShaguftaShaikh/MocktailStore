package com.api.mocktailstore.exception;

/**
 * @author Shagufta
 *
 */
public class UserAlreadyExists extends Exception{

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Username already exists";
	}
	
	

}
