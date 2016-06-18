package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Contact;

public class Authent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connect = null;
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 //ArrayList<Contact> l=new ArrayList<Contact>();
			 connect = DriverManager.getConnection("jdbc:mysql://localhost/contact_bd","root","");
			 Statement stmt = connect.createStatement();
			 String myQuery =  "SELECT * FROM contact";
			 ResultSet rs=stmt.executeQuery(myQuery);
			 Contact c=new Contact();
			 String connecter="false";
			 while(rs.next()){
				 int id=rs.getInt(1);
				 String login=rs.getString(2);
				 String mdp=rs.getString(3);
				 if(login.equals(request.getParameter("login")) && mdp.equals(request.getParameter("mdp")))
				 {
				 c.setId(id);
				 c.setLogin(login);
				 c.setMdp(mdp);
				 //l.add(c);
				 connecter="true";
				 request.setAttribute("contact",c);
				 request.getRequestDispatcher("Connexion.jsp").forward(request, response);
				 }
			 }
			 if(connecter.equals("false")){
				 request.getRequestDispatcher("Reconnexion.jsp").forward(request, response);
			 }
				connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
	}

