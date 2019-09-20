package demo;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SamplesetupServlet
 */
@WebServlet("/SamplesetupServlet")
public class SamplesetupServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {

		      
		      response.setContentType("text/html");
		      PrintWriter out = response.getWriter();     
		      
		      try {
		         
		         Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/EMP","root","12345678");

		         // Execute SQL query
		         Statement stmt = con.createStatement();
		         String sql;
		         sql = "SELECT ID, Employee_Name FROM employee";
		         ResultSet rs = stmt.executeQuery(sql);
		         String str= "<table border=1><tr><th>ID</th><th>Employee Name</th></tr>";
		         // Extract data from result set
		         while(rs.next()){
		            //Retrieve by column name
		            int id  = rs.getInt("ID");
		            String name = rs.getString("Employee_Name");
		            str += "<tr><td>"+id+"</td><td>"+name+"</tr></td>";
		         }  
		           
		            //Display values
		            str += "</table>";
		            out.println(str);
		           
		         rs.close();
		         stmt.close();
		         con.close();
		      } catch(SQLException se) {
		         //Handle errors for JDBC
		         se.printStackTrace();
		      } catch(Exception e) {
		         //Handle errors for Class.forName
		         e.printStackTrace();
		      }
		      
		   }
}
