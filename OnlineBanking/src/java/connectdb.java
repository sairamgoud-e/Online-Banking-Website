import static java.awt.SystemColor.window;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
//import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;
import static jdk.nashorn.internal.objects.NativeFunction.function;

public class connectdb extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        String uname = request.getParameter("uname");
        String pswd = request.getParameter("pswd");
        String mailid = request.getParameter("mailid");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String phno = request.getParameter("phno");
        String gender = request.getParameter("gender");
        String repswd = request.getParameter("repswd");
        String qsn = request.getParameter("qsn");
        String ans = request.getParameter("ans");
        String Query = String.format("insert into userdetails(uname,pswd,mailid,fname,lname,phno,gender,qsn,ans) values('%s','%s','%s','%s','%s','%s','%s','%s','%s');",uname,pswd,mailid,fname,lname,phno,gender,qsn,ans);
        String Query1=String.format("select uname from userdetails where uname='%s'",uname);
        
        //if uname not in database:
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","sairam","Sairam@789");
            Statement stat = con.createStatement();
            ResultSet rs=stat.executeQuery(Query1);
            //int rs1=stat.exec
            if(!(rs.next())) {
             
                stat.executeUpdate(Query);
                RequestDispatcher view=request.getRequestDispatcher("thankupage.html");
                view.forward(request, response);
                con.close();
            }
            else{
                response.setContentType("text/html");
                PrintWriter pw=response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Username unavailable..!!Enter another username');");
                pw.println("</script>");
                RequestDispatcher rd=request.getRequestDispatcher("registerpage.html");
                rd.include(request, response);
            //RequestDispatcher view=request.getRequestDispatcher("registerpage.html");
            //view.forward(request, response);
            //out.println("ewfe");
            con.close();
            }
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(connectdb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(connectdb.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
