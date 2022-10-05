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

@WebServlet(urlPatterns = {"/end"})
public class end extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException
{

response.setContentType("text/html");
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection(
"jdbc:mysql://localhost:3307/play","root","mysql");
String name=request.getParameter("name");
String dob=request.getParameter("dob");
String sports=request.getParameter("sport");

         PreparedStatement st = con.prepareStatement("insert into user  values(?,?,?)");

            st.setString(1,name);
            st.setString(2,dob);
           st.setString(3,sports);

            st.executeUpdate();
            st.close();
            con.close();
               if(sports.equals("cricket") || sports.equals("Cricket"))
                    response.sendRedirect("end1.html"); 
              else  if(sports.equals("football") || sports.equals("Cricket"))
                    response.sendRedirect("end2.html"); 
              else  if(sports.equals("hockey") || sports.equals("Cricket"))
                    response.sendRedirect("end3.html"); 

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
