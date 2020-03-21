import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;

public class FeedbackForm extends HttpServlet
{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String fname=request.getParameter("FNAME");
        String lname=request.getParameter("LNAME");
        String phone_no=request.getParameter("PHONE_NO");
        String email=request.getParameter("EMAIL");
        String htcu=request.getParameter("HTCU");
        String feedback=request.getParameter("FEEDBACK");
        //DBLogic
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
            PreparedStatement pst=con.prepareStatement("INSERT INTO FEEDBACKS VALUES(?,?,?,?,?,?)");
            pst.setString(1,fname);
            pst.setString(2,lname);
            pst.setString(3,phone_no);
            pst.setString(4,email);
            pst.setString(5,htcu);
            pst.setString(6,feedback);
            pst.executeUpdate();
            pst.close();
            con.close();
        }
        catch(SQLException | ClassNotFoundException e)
        {    
          out.print("<h2 align=center>Unable to continue...."+e+"</h2>");
        }    
        response.sendRedirect("index.html");
        out.close();
    }
}