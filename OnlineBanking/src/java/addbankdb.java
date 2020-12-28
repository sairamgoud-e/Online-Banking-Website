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
import javax.servlet.http.HttpSession;
import javax.sql.*;
import static jdk.nashorn.internal.objects.NativeFunction.function;

public class addbankdb extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        String bank = request.getParameter("bank");
        String accno = request.getParameter("accno");
        String ifsc = request.getParameter("ifsc");
        String regmobno = request.getParameter("regmobno");
        String db="jdbc:mysql://localhost:3306/";
        db=db+bank;
        String fetchbal=String.format("select *from details where Accno='%s'",accno);
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(db,"sairam","Sairam@789");
            Statement stat = con.createStatement();
            ResultSet rs=stat.executeQuery(fetchbal);
            if (rs.next()){
            String ba = rs.getString("bankbalance");
            
             con.close();  
            
            // catch (ClassNotFoundException ex) {
            //   Logger.getLogger(addbankdb.class.getName()).log(Level.SEVERE, null, ex);
            //} catch (SQLException ex) {
            //    Logger.getLogger(addbankdb.class.getName()).log(Level.SEVERE, null, ex);
            //}
        
            int bankbalance=Integer.parseInt(ba);
            //int bankbalance=200;
            HttpSession session = request.getSession();
            String uname=session.getAttribute("uname").toString();
            String Query = String.format("insert into bankdetails(bankname,Accno,IFSC,regmobno,bankbalance,uname) values('%s','%s','%s','%s','%s','%s');",bank,accno,ifsc,regmobno,bankbalance,uname);
            //String Query1=String.format("select uname from userdetails where uname='%s'",uname);
           
        
            //if uname not in database:
        
            Class.forName("com.mysql.jdbc.Driver");
            Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","sairam","Sairam@789");
            Statement sta = con1.createStatement();
            //ResultSet rs=stat.executeQuery(Query);
            
             
            sta.executeUpdate(Query);
            RequestDispatcher view=request.getRequestDispatcher("linked.html");
            view.forward(request, response);
            con1.close();
            }
            
        }  
         catch (ClassNotFoundException ex) {
            Logger.getLogger(addbankdb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addbankdb.class.getName()).log(Level.SEVERE, null, ex);
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
