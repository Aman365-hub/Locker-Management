package com.dell.password;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class URLdetails {
	
	ArrayList<Webdetails> al=new ArrayList<>();
	Lockme lk=new Lockme();

	public URLdetails(String user_name, String pwd) {
		
		URLadd(user_name,pwd);
			
		}

	private void URLadd(String user_name, String pwd) {
		System.out.println(".................Welcome to Locker Management portal................");
        System.out.println("Enter from the below preferred  options");
        System.out.println("1.Adding details");
        System.out.println("2.Viewing details");
        System.out.println("3.Exit");
        Scanner sc=new Scanner(System.in);
        String s =sc.next();
        Integer user_ip = Integer.parseInt(s);
        try{
        switch (user_ip){
		case 1:
			add_URLdetails(user_name, pwd);
			break;
		case 2:
			URLdetails_Check(user_name, pwd);
			break;
		
		case 3:
			System.out.println("Thanks for Visiting Locker Management Portal...................");
		    Lockme lk=new Lockme();
		    lk.login();
			break;
		default :
			System.out.println("Wrong input entered, try again ");
			URLadd(user_name, pwd);
			break;
			}  		
        }catch(Exception e){
        	
        	System.out.println("Incorrect input entered ....... Please choose either option 1 or 2");
        	URLadd(user_name, pwd);
	        	
	        }
		
	}

	private void URLdetails_Check(String user_name, String pwd) {
try{
			
			view_webdetails(user_name,pwd);
			
			String web_name,web_pwd,web_uname;
			System.out.println("\n Please find the URL details provided with Password ");
			
			
		for (Webdetails wx: al)
		{
			if(wx.getUname().equals(user_name))
			{
			  web_uname=wx.getWeb_user_name();
			  web_name=wx.getWebsite_name();
			  web_pwd=wx.getWeb_pwd();
			  
			  System.out.println("\n Website name :"+web_name);
			  System.out.println("\n User_name:" + web_uname);
			  System.out.println("\n Website_password:"+web_pwd);
			}
		}
		
		login_URLdetail(user_name,pwd);
			

		
			  }
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		
	}
	private void view_webdetails(String user_name,String pwd) {
		try{
			
			
			FileInputStream fin=new FileInputStream(new File("URLpwd.txt"));

			boolean cont = true;
        	try{
        	   	  while(cont){
        	   		
        		   ObjectInputStream input = new ObjectInputStream(fin);
        	       Object obj = input.readObject();
        	       if(obj != null)
        	       {
        	    	   Webdetails wl=(Webdetails) obj;
       			       al.add(wl);
       			       
       			          			             			      
       			       	    	   }  
        	       else
        	        cont = false;
        	      
        	       }

			fin.close();
			
			        	
			}catch(Exception e){
				e.getLocalizedMessage();
	}
	
		}catch(Exception e){System.out.println(e.getMessage());}
	}
	
	private void add_URLdetails(String user_name, String pwd) {
		
			Scanner sc=new Scanner(System.in);
			System.out.println("Please enter Website name");
			String Web_name, web_uname,web_pwd,u_name,u_ip;
			Web_name=sc.next();
			System.out.println("Please enter user_name");
			web_uname=sc.next();
			System.out.println("Please enter password");
			web_pwd=sc.next();
			User ud =new User(user_name,pwd);
			
			Validating_URLdetails(user_name,Web_name,web_uname,web_pwd);
			System.out.println("Do you wish to add more website passwords");
			System.out.println("Press y or N ");
			u_ip=sc.next();
			if (u_ip .equalsIgnoreCase("y")||u_ip.equalsIgnoreCase("yes")){
				add_webdetails(user_name, pwd);
			}
			else{
				System.out.println("Thanks. All the provided details are added.");
				login_URLdetail(user_name, pwd);
			}
			
			
			
		}

	private void add_webdetails(String user_name, String pwd) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter Website name");
		String Web_name, web_uname,web_pwd,u_name,u_ip;
		Web_name=sc.next();
		System.out.println("Please enter user_name");
		web_uname=sc.next();
		System.out.println("Please enter password");
		web_pwd=sc.next();
		User ud =new User(user_name,pwd);
		
		Validating_URLdetails(user_name,Web_name,web_uname,web_pwd);
		System.out.println("Do you wish to add more website passwords");
		System.out.println("Press y or N ");
		u_ip=sc.next();
		if (u_ip .equalsIgnoreCase("y")||u_ip.equalsIgnoreCase("yes")){
			add_webdetails(user_name, pwd);
		}
		else{
			System.out.println("Thanks. All the provided details are added.");
			login_URLdetail(user_name, pwd);
		}
		
		
		
	}

	private void login_URLdetail(String user_name, String pwd) {
		System.out.println("Welcome to Locker Management portal......");
        System.out.println("Enter from the below preferred  options");
        System.out.println("1.Adding details");
        System.out.println("2.Viewing details");
        System.out.println("3.Exit");
        Scanner sc=new Scanner(System.in);
        String s =sc.next();
        Integer user_ip = Integer.parseInt(s);
        try{
        switch (user_ip){
		case 1:
			add_webdetails(user_name, pwd);
			break;
		case 2:
			URLdetails_Check(user_name, pwd);
			break;
		
		case 3:
			System.out.println("Thanks for Visiting Locker Management Portal. Have a Great day!!!!!!!!");
			lk.login();
			break;
		default :
			System.out.println("Wrong input entered, try again ");
			login_URLdetail(user_name, pwd);
			break;
			}  		
        }catch(Exception e){
        	
        	System.out.println("Incorrect input entered . Please choose either option 1 or 2");
        	login_URLdetail(user_name, pwd);
	        	
	        }
		
		
	}

	private void Validating_URLdetails(String user_name, String web_name, String web_uname, String web_pwd) {
		try{
			Webdetails wd= new Webdetails(user_name,web_name,web_uname,web_pwd);
			FileOutputStream os = new FileOutputStream(new File("URLpwd.txt"), true);
			ObjectOutputStream objectOut = new ObjectOutputStream(os);
			objectOut.writeObject(wd);
			objectOut.flush();
			objectOut.close();
			System.out.println("Successfully added web details");
			}
			catch (Exception e){
				System.out.println(e.getMessage());
			}
		
	}
	}
	


