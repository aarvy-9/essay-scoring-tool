/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.auth;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author pc
 */
public class ConnectionObj {
    static Connection con;
    public static Connection getConnection()
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
            return con;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();   
            return null;
        }
    }
    
}
