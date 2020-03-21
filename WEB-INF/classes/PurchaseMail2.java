import java.util.*;  
import java.io.*;
import javax.mail.*;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class PurchaseMail2 extends HttpServlet
{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {    
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String to=request.getParameter("EMAIL2");
        String subject="Purchase Confirmation";  
        String msg="Hi, You have purchased item 2 successfully from 'Agri'gate..\n Have a blast!!!";  
        String p2=request.getParameter("PHONE_NO2");
        //DBLogic
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
            PreparedStatement pst=con.prepareStatement("INSERT INTO CONTACTS VALUES(?,?,?)");
            pst.setString(1,to);
            pst.setString(2,p2);
            pst.setString(3,"2");
            pst.executeUpdate();
            pst.close();
            con.close();
        }
        catch(SQLException | ClassNotFoundException e)
        {
            out.print("<h2 align=center>Unable to continue...."+e+"</h2>");
        }    
        
        Mailer.send(to, subject, msg);  
        response.sendRedirect("services.html");
        out.close();
    }
}