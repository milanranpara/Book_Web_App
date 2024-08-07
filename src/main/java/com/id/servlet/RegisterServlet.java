package com.id.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet
{
	
		//	SET QUERY
		private static final String query = "INSERT INTO BOOKDATA (BOOKNAME, BOOKEDITION, BOOKPRICE) VALUES (?,?,?)";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{

		//		GET PRINTWRITER
			PrintWriter pw = res.getWriter();
			
			//	SET CONTENT TYPE
			res.setContentType("text/html");			
			
			String bookName = req.getParameter("bookName");
			String bookEdition = req.getParameter("bookEdition");
			float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
			
			
			//	1. LOAD DRIVERS
			try
			{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			//	2. CREATE CONNECTION
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","root");
			
			//	3. PREPARE STATEMENT
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			
			//	4. EXECUTE QUERY
			int count = ps.executeUpdate();
			
			//	5. FETCH THE RESULT
			if(count==1)
			{
				pw.println("<h2>Record inserted successfully !!!</h2>");
			}
			else
			{
				pw.println("<h2>Record was not inserted !!!</h2>");
			}
			
			//	6. CLOSE CONNECTION
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.println("<a href='home.html'>Home</a>");
			pw.println("<br>");
			pw.println("<a href='bookList'>Book list</a>");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		doGet(req,res);
	}
}
