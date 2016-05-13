package com.srinivas.hemanth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.http.*;

public class App extends HttpServlet{

	Logger logger = LoggerFactory.getLogger(App.class);
	String foo;
	
	private String message;

	  public void init() throws ServletException
	  {
	      // Do required initialization
	      message = "Hello World";
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
	      // Set response content type
	      response.setContentType("text/html");

	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
	      out.println("<h1>" + message + "</h1>");
	      

			InitialContext cxt;
			try {
				cxt = new InitialContext();
				DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/postgres" );
				

				if ( ds == null ) {
				   System.out.println("Data source not found!");
				   logger.info("Data source not found");
				}else
				{
					System.out.println("Data source found!Yaay!");
					logger.info("Data source found! Yaay!");
					Connection conn = ds.getConnection();
					
					if(conn!=null)
					{
						foo= "Got Connection "+conn.toString();
						System.out.println(foo);
						Statement stmt = conn.createStatement();
	                    ResultSet rst = stmt.executeQuery("select * from product");
	                    while(rst.next())
	                    {
	                    	System.out.println(rst.getString(2));
	                    }
					}
					
				}
								
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (SQLException s){
				s.printStackTrace();
			}

	  }
	  
	  public void destroy()
	  {
	      // do nothing.
	  }

}
