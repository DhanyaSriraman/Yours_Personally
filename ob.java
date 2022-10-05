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

@WebServlet(urlPatterns = {"/ob"})
public class ob extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException
{

response.setContentType("text/html");
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection(
"jdbc:mysql://localhost:3307/hot","root","mysql");
String name=request.getParameter("hotels");
String date=request.getParameter("checkins");
String date1=request.getParameter("checkouts");
int room=Integer.parseInt(request.getParameter("rooms"));
int room1=Integer.parseInt(request.getParameter("roomtpe"));
int total=Integer.parseInt(request.getParameter("total"));

          PreparedStatement st = con.prepareStatement( "insert into hote values (?,?,?,?,?,?)");

              st.setString(1,name);
              st.setString(2,date);
              st.setString(3,date1);
             st.setInt(4,room);
              st.setInt(5,room1);
             st.setInt(6,total);
             st.executeUpdate();
             st.close();
             con.close();
            PrintWriter out = response.getWriter();
            out.println("<html><body bgcolor='#ffccdd';color='#cc9966'><b><center><h1>Your Room has been booked Successfully!!"
                      + "</h1></center></b></body></html>");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
