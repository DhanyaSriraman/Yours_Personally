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

@WebServlet(urlPatterns = {"/hb"})
public class hb extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException
{

response.setContentType("text/html");
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection(
"jdbc:mysql://localhost:3307/thrif","root","mysql");
String location=request.getParameter("location");
String date=request.getParameter("date");
          PreparedStatement st = con.prepareStatement( "insert into singapore values (?,?,?,?)");

              st.setString(1,location);
              st.setString(2,date);
              st.setInt(3,Integer.parseInt(request.getParameter("num")));
             st.setInt(4,Integer.parseInt(request.getParameter("total")));
             st.executeUpdate();
              st.close();
           /*  st.close();
            con.close();
            PrintWriter out = response.getWriter();
            out.println("<html><body><b>Successfully registered"
                      + "</b></body></html>");*/
           Statement stm = con.createStatement();
           ResultSet rs = stm.executeQuery("select* from singapore where location='"+location+"'and date= '"+date+"'");
              int total=Integer.parseInt(request.getParameter("total"));
            int count=0;
               Statement statement= con.createStatement();
              ResultSet ras = statement.executeQuery("select* from singapore");
             PrintWriter out = response.getWriter();
             out.println("<html><body bgcolor='#ffccdd';color='#cc9966'><b><center><h1>Successfully Registered!!"
                      + "</h1></center></b></body></html>");
            out.println("<html>");
            out.println("<body>");
    while(ras.next())
     {             
                    count=count+1;
     }
            
           if(count>3)
                {
                 out.println("<center><h2>Your coupon code is valid</h2></center>");
                out.println("<center><h3>Congrats You have booked a package more than 3 times with your device and we are happy to provide you with a 20% discount</center></h3>");
                double toto=total-(total*0.2);
                   while(rs.next())
                         out.println("<h2><center>PACKAGE DETAILS<br></h2>"+"<h3><center>location : "+rs.getString(1)+"<br>date : "+rs.getString(2)+"<br>number of tickets : "+rs.getInt(3)+"<br>Total amount to be paid :Rs. : "+toto+"</h3></center>");
                }
             else if(count<=3){
                       out.println("<center><h2>Your coupon is not valid</h2></center>");
                       out.println("<center><h3>Register more than three times to get a discount</center></h3>");
                       double toto=total;
                       while(rs.next())
                         out.println("<h2><center>PACKAGE DETAILS<br></h2>"+"<h3><center>location : "+rs.getString(1)+"<br>date : "+rs.getString(2)+"<br>number of tickets : "+rs.getInt(3)+"<br>Total amount to be paid :Rs. : "+toto+"</h3></center>");
                 }
              con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
