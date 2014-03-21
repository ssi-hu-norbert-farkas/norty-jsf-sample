package hu.norty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Norbert_Farkas on 3/19/14.
 */
public class DBData {
    public Connection getConnection() {
        Connection con = null;

        String url = "jdbc:sqlserver://localhost;database=Panel";
        String user = "sa";
        String password = "rozi";
        try {
            con = DriverManager.getConnection(url, user, password);
            //System.out.println("Connection completed.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
        }
        return con;
    }
}
