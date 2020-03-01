package com.dell.password;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Lockme {
	HashMap<String,String> hd = new HashMap<>(); 
	public static void main(String[] args) {
		
		new Lockme().login();
		
	}

	public void login() {
		System.out.println("Welcome to Locker Management portal........");
        System.out.println("Enter the below preferred  options");
        System.out.println("1. New user");
        System.out.println("2. Registered  user");
        System.out.println("3. Exit");
        Scanner sc=new Scanner(System.in);
        String s =sc.next();
        Integer user_input = Integer.parseInt(s);
        try{
        switch (user_input){
		case 1:
			register();
			break;
		case 2:
			validatecredentials();
			break;
		case 3:
			System.out.println("Thanks for Visiting Locker Management Portal......");
			break;
	
		default :
			System.out.println("Incorrect input entered, try again ");
			login();
			break;
			}  		
        }catch(Exception e){
        	
        	System.out.println("Wrong Value entered . Please choose either option 1 or 2");
        	login();
	        	
		
	}

}

	private void validatecredentials() {
		Scanner sc=new Scanner(System.in);
		String user_name,pwd;
		System.out.println("Enter your user_name");
		user_name=sc.nextLine();
		adduser();                
        if(hd.containsKey(user_name)==true){
        	System.out.println("Enter your password");
    		pwd=sc.next(); 	
        	if((hd.get(user_name)).equals(pwd)){
        		System.out.println("Logged in Successfully!! ");
        		URLdetails ur=new URLdetails(user_name,pwd);
        		       		
        	}
        	else{
        		System.out.println("Incorrect password .. Try once again");
        		 login();
        	}
        }else{
        	System.out.println("User not registered. Please register before logging in");
        	 login();
        }
		
	}

	

	private void adduser() {
		try{
			
			
			FileInputStream fin=new FileInputStream(new File("userdetails.txt"));

			boolean cont = true;
        	try{
        	   	  while(cont){
        	   		
        		   ObjectInputStream input = new ObjectInputStream(fin);
        	       Object obj = input.readObject();
        	       if(obj != null)
        	       {
        	    	   User ul = (User) obj;
       			       String uName=ul.getUname();
       			       String uPas=ul.getU_pwd();
     			       hd.put(uName, uPas);
        	    	          	    	   }  
        	       else
        	        cont = false;
        	      
        	       }

			fin.close();
			
			        	
			}catch(Exception e){
				e.getLocalizedMessage();
	}}catch(Exception e){System.out.println(e.getMessage());}
	}
		
	

	private void register() {
		try{
			
			String user_name,pwd;
			System.out.println("Enter  user name you required to keep");
			Scanner sn=new Scanner(System.in);
			user_name=sn.nextLine();
			
			adduser();
	        if(hd.containsKey(user_name)==true){
	        		System.out.println("User already exists. Try new user name or login to the existing account ");
	        		login();
	        	}
	        	else{
	        		System.out.println("Enter Password you wish to keep");
	                pwd=sn.next();
	        		validating_user(user_name,pwd);}
	        	               
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
	}

	private void validating_user(String user_name, String pwd) {
		try{
			User ud=new User(user_name,pwd);
			FileOutputStream os = new FileOutputStream(new File("userdetails.txt"), true);
			ObjectOutputStream objectOut = new ObjectOutputStream(os);
			objectOut.writeObject(ud);
			objectOut.flush();
			objectOut.close();
			System.out.println("Congratz...Successfully Registered !!!!!");
			login();
			
			}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}}
