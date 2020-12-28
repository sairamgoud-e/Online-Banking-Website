/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author sairam goud
 */
public class balance extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         //HttpSession session = request.getSession();
         HttpSession session = request.getSession();
        String uname=session.getAttribute("uname").toString();
       // String uname=session.getAttribute("uname").toString();
        String Query=String.format("select bankbalance from bankdetails where uname='%s'",uname);
        
        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","sairam","Sairam@789");
            Statement stat = con.createStatement();
            ResultSet rs=stat.executeQuery(Query);
            //out.println("<h1><center>YOUR BALANCE <br></center></h1>");
            //out.println("");
            if (rs.next()) {
                String bal = rs.getString("bankbalance");
                out.println("<!DOCTYPE html>\n" +
"<!--\n" +
"To change this license header, choose License Headers in Project Properties.\n" +
"To change this template file, choose Tools | Templates\n" +
"and open the template in the editor.\n" +
"-->\n" +
"<html>\n" +
"    <head>\n" +
"        \n" +
"        <link rel=\"stylesheet\" type=\"text/css\" href=\"style1.css\">\n" +
"        <title>Transfer</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <header>\n" +
"    <center>\n" +
"        <div class=\"cont\">\n" +
"        <div class=\"inp\">\n" +
"            <br><h1>YOUR CURRENT BALANCE IS</h1><br><br>\n" +
"        <fieldset>  \n" +
"            <h1>\n" +
"                \n" +bal+
"    &nbsp  rupees   \n" +
"       <br><br>  <a href=\"welcomepage.html\" class=\"btn\">OK</a>\n" +
"    <br><br>    </h1>\n" +
"            </fieldset>\n" +
"        </div>\n" +
"            </div>\n" +
"    </center>\n" +
"      </header>\n" +
"    </body>\n" +
"</html>");
                //out.println("<br><br><b>Current Balance:</b>&nbsp&nbsp"+bal);
                //out.println("<br>"+name + "&nbsp&nbsp&nbsp" + mail+"&nbsp&nbsp&nbsp"+fname+"&nbsp&nbsp&nbsp"+lname+"\n");
                
            }
            else{
                //response.setContentType("text/html");
                //PrintWriter pw=response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('BANK ACCOUNT NOT ADDED..!!! ADD BANK ACCOUNT');");
                out.println("</script>");
                RequestDispatcher rd=request.getRequestDispatcher("welcomepage.jsp");
                rd.include(request, response);
           
            con.close();
            }
            
            //String ans=rs.toString();
            
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet transactionslogic</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Transactions are " + ans + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(balance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(balance.class.getName()).log(Level.SEVERE, null, ex);
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
