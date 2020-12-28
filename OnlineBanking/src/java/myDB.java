
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sairam goud
 */
public class myDB {
    //Connection con;
    public Connection getCon() throws SQLException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","sairam","Sairam@789");
            //return con;
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(myDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(myDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //return con;
        return null;
        
        
                
    }

    
    
}
