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
@WebServlet(urlPatterns = {"/bb"})
public class bb extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException
{

response.setContentType("text/html");
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection(
"jdbc:mysql://localhost:3307/connect","root","mysql");

             Statement stm = con.createStatement();
           ResultSet rs = stm.executeQuery("select* from travels");

             PrintWriter out = response.getWriter();
         /*    out.println("<html><body><b><center><h1><font size='30px'><strong>Acc Details!!"
                      + "</strong></font></h1></center></b></body></html>");*/
            out.println("<html>");
            out.println("<body  bgcolor='#ffccdd';color='#cc9966'>");
           while(rs.next())
{
if(rs.isLast()){
               out.println("<h2> <center><font  size='10px' family='Courier New'><strong>ACCOUNT DETAILS<br></h2></center></font></strong>"+"<h3><center><font ><strong>Firstname : "+rs.getString(1)+"<br><br>Lastname : "+rs.getString(2)+"<br><br>Username : "+rs.getString(3)+"</h3></center></strong></font>");
}
}
           //  while(rs.next())
           //    out.println("<h2><center>PACKAGE DETAILS<br></h2>"+"<h3><center>location : "+rs.getString(1)+"<br>date : "+rs.getString(2)+"<br>number of tickets : "+rs.getInt(3)+"<br>Total amount to be paid :Rs. : "+rs.getString(4)+"</h3></center>");

              con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
