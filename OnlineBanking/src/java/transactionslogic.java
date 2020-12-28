
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sairam goud
 */
public class transactionslogic extends HttpServlet {

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
        String Query=String.format("select *from transactionsdb where sender='%s' or receiver='%s'",uname,uname);
        
        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","sairam","Sairam@789");
            Statement stat = con.createStatement();
            ResultSet rs=stat.executeQuery(Query);
            out.println("<h1>YOUR TRANSACTIONS ARE <br></h1>");
            while (rs.next()) {
                String s = rs.getString("sender");
                String re = rs.getString("receiver");
                String amt = rs.getString("amt");
                String date = rs.getString("date");
                
                out.println("<br>"+s + "&nbsp&nbsp&nbsp" + re+"&nbsp&nbsp&nbsp"+amt+"&nbsp&nbsp&nbsp"+date+"\n");
                
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
            Logger.getLogger(transactionslogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(transactionslogic.class.getName()).log(Level.SEVERE, null, ex);
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
