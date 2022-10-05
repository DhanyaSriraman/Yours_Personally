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

@WebServlet(urlPatterns = {"/endd"})
public class endd extends HttpServlet {
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
String award=request.getParameter("award");
String year=request.getParameter("year");
String player="Someone";
if(award.equals("Khel ratna") && year.equals("1962"))
player="T Balaram";
else if(award.equals("Arjuna Award") && year.equals("1962"))
 player="C Singh";
else if(award.equals("Khel ratna") && year.equals("1983"))
 player="S Mullick";
else if(award.equals("Arjuna Award") && year.equals("1983"))
 player="Sandhesh";

         PreparedStatement st = con.prepareStatement("insert into awards  values(?,?,?)");

            st.setString(1,year);
            st.setString(2,award);
            st.setString(3,player);

            st.executeUpdate();
            st.close();
            con.close();
           
                  if(player.equals("T Balaram"))
                    response.sendRedirect("T Balaram.pdf"); 
              else  if(player.equals("C Singh"))
                    response.sendRedirect("C Singh.pdf"); 
              else  if(player.equals("S Mullick"))
                    response.sendRedirect("S Mullick.pdf"); 
               else  if(player.equals("Sandhesh"))
                    response.sendRedirect("Sandhesh.pdf"); 

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

         