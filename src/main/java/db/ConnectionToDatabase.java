package db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionToDatabase {

    private ConnectionToDatabase(){
        //empty constructor
    }

    private static ConnectionToDatabase instance = null;

    public static ConnectionToDatabase getInstance(){
        if (instance==null)
            instance = new ConnectionToDatabase();
        return instance;
    }

    public Connection getConnection(){
        Connection c = null;
        Context ctx;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mydatabase");
            c = ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void close(Connection c){
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
