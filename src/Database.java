import java.sql.*;
import java.util.HashMap;

public class Database{

    Connection conn = null;
//Database constructor
    public Database(){
        createConnection();
        createTable();
    }
//Connecting to database
    private void createConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:wms.db");
            System.out.println("Opened database successfully");
        } catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
//Creating a database table
    public void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS KAUBAD (ID INT AUTO_INCREMENT, NAME TEXT, QTY INT, LOCATION INT);";
        applyChanges(sql);
    }
//Applying changes to database table
    public void applyChanges(String sql){
        try{
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            stat.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
//Closing the connection
    public void closeConnection(){
        try{
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Connection closed");
    }

}