package com.persequor.exceptions;

class Enum_Exception{ 
	
	enum Exceptionss{   
		PER_200("Successful"), PER_400_1("Null Values"), PER_400_2("Blank Values"), PER_(""); 
		
		private String value;  
		private Exceptionss(String value){  
			this.value=value;  
		}  
	}  
	public static void main(String args[]){  
		for (Exceptionss e : Exceptionss.values())  
			System.out.println(e+" "+e.value); 
	}
}
