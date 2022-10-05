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

@WebServlet(urlPatterns = {"/ab"})
public class ab extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException
{

response.setContentType("text/html");
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection(
"jdbc:mysql://localhost:3306/connect","root","mysql");
String firstname=request.getParameter("first");
String lastname=request.getParameter("last");


         PreparedStatement st = con.prepareStatement("insert into travels values(?,?,?,?)");

            st.setString(1,firstname);
             st.setString(2,lastname);
            st.setString(3, request.getParameter("user"));
            st.setString(4, request.getParameter("password"));

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
