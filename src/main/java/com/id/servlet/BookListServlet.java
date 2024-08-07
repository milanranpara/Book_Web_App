package com.id.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {

	private static final String query = "SELECT ID, BOOKNAME, BOOKEDITION, BOOKPRICE FROM BOOKDATA";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//		GET PRINTWRITER
			PrintWriter pw = res.getWriter();
			
			//	SET CONTENT TYPE
			res.setContentType("text/html");
						
			//	1. LOAD DRIVERS
			try
			{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			//	2. CREATE CONNECTION
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","root");
			
			//	3. PREPARE STATEMENT
			PreparedStatement ps = con.prepareStatement(query);
			
			//	4.	EXECUTE QUERY
			ResultSet rs = ps.executeQuery();
			pw.println("<table border='1' align='center'>");
			pw.println("<tr>");
			pw.println("<th>Book ID</th>");
			pw.println("<th>Book Name</th>");
			pw.println("<th>Book Edition</th>");
			pw.println("<th>Book Price</th>");
			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");
			while(rs.next())
			{
				pw.println("<tr>");
				pw.println("<td>"+rs.getInt(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("<td>"+rs.getFloat(4)+"</td>");
				pw.println("<td><a href='editScreen?id=" + rs.getInt(1) + "'>Edit</a></td>");
                pw.println("<td><a href='deleteurl?id=" + rs.getInt(1) + "'>Delete</a></td>");                
				pw.println("</tr>");
			}
			pw.println("</table>");

			//	6. CLOSE CONNECTION
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pw.println("<a href='home.html'>Home</a>");
	}
}
