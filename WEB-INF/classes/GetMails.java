import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;

public class GetMails extends HttpServlet
{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String to=request.getParameter("EMAIL_ID");
	    String subject="User Subscription";  
        String msg="Hi, You have subscribed successfully to 'Agri'gate..\n Have a blast!!!";  

        //DBLogic
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
            PreparedStatement pst=con.prepareStatement("INSERT INTO MAILDATA VALUES(?)");
            pst.setString(1,to);
            pst.executeUpdate();
            pst.close();
            con.close();
        }
        catch(SQLException | ClassNotFoundException e)
        {
            out.print("<h2 align=center>Unable to continue...."+e+"</h2>");
        }    
        
        Mailer.send(to, subject, msg);  
        response.sendRedirect("index.html");
        out.close();
    }
}