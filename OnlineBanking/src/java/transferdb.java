
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




public class transferdb extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        String runame = request.getParameter("runame");
        String rmobno = request.getParameter("rmobno");
        String am = request.getParameter("amt");
        int amt= Integer.parseInt(am.trim());
        String pswd = request.getParameter("pswd");
        //String regmobno = request.getParameter("regmobno");
        String dat;
        Date date=null;
        date=new Date();
        dat=date.toString();
        //int bankbalance=200000;
        HttpSession session = request.getSession();
        String uname=session.getAttribute("uname").toString();
        //String curbal=session.getAttribute("bankbalance").toString();
        //int bal=Integer.parseInt(curbal);
        //String Query4=String.format("select *from userdetails where uname='%s' and pswd='%s'",uname,pswd);
        String Query1=String.format("select *from bankdetails where uname='%s' and regmobno='%s'",runame,rmobno);
        String Query = String.format("insert into transactionsdb(sender,receiver,amt,date) values('%s','%s','%d','%s');",uname,runame,amt,dat);
        String suff=String.format("select *from bankdetails where uname='%s'",uname);
        String Query2=String.format("update bankdetails set bankbalance=bankbalance+'%d' where uname='%s'",amt,runame);
        String Query3=String.format("update bankdetails set bankbalance=bankbalance-'%d' where uname='%s'",amt,uname);

        //if uname not in database:
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","sairam","Sairam@789");
            Statement stat = con.createStatement();
            ResultSet rs=stat.executeQuery(Query1);
            //Statement st=con.createStatement();
            
            
            if((rs.next()) ) {      //verify user
                //Class.forName("com.mysql.jdbc.Driver");
                //Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","sairam","Sairam@789");
                //Statement st = con1.createStatement();
                ResultSet r=stat.executeQuery(suff);
                
                if (r.next()) {
                String ba = r.getString("bankbalance");
                int bal=Integer.parseInt(ba);
                if(bal>=amt)
                {
                //Class.forName("com.mysql.jdbc.Driver");
                //Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","sairam","Sairam@789");
                //Statement st = con1.createStatement();
                stat.executeUpdate(Query);
                stat.executeUpdate(Query2);
                stat.executeUpdate(Query3);
                RequestDispatcher view=request.getRequestDispatcher("sentpage.html");
                view.forward(request, response);
                con.close();
                }
                
                else{
                    response.setContentType("text/html");
                PrintWriter pw=response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('INSUFFICIENT FUNDS');");
                pw.println("</script>");
                RequestDispatcher rd=request.getRequestDispatcher("transfer.html");
                rd.include(request, response);
           
                    con.close();
                }
                }
                
            }
            else{
                response.setContentType("text/html");
                PrintWriter pw=response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Invalid User Details, please enter correct details');");
                pw.println("</script>");
                RequestDispatcher rd=request.getRequestDispatcher("transfer.html");
                rd.include(request, response);
           
            con.close();
            }
            
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(transferdb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(transferdb.class.getName()).log(Level.SEVERE, null, ex);
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
