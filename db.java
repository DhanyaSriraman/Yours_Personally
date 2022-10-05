import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/db"})
public class db extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException 
{

response.setContentType("text/html");
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection(
"jdbc:mysql://localhost:3307/rev","root","mysql");
String comm=request.getParameter("revi");



         PreparedStatement st = con.prepareStatement("insert into par values(?)");
            
            st.setString(1,comm);
            
            st.executeUpdate();
  
            st.close();
            con.close();
               response.sendRedirect("frontendmodifieds.html"); 
        }
        catch (Exception e) {
            System.out.println(e);
        }
}
}