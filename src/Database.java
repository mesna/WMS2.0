import java.sql.*;
import java.util.HashMap;

public class Database{

    private static Connection conn;
    private static boolean notEmpty = false;

    public ResultSet displayProducts() throws ClassNotFoundException, SQLException{

        if(conn == null){
            createConnection();
        }
        Statement state = conn.createStatement();
        ResultSet result = state.executeQuery("SELECT nimi, kogus, asukoht FROM kaubad");
        return result;
    }

    private void createConnection() throws ClassNotFoundException, SQLException{

        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:wms.db");
        runConnection();
    }

    private void runConnection() throws SQLException{

        if(!notEmpty){
            notEmpty = true;

            Statement state = conn.createStatement();
            ResultSet result = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='kaubad'");
            if(!result.next()){
                System.out.println("Creating a table for Products");

                Statement state2 = conn.createStatement();
                state2.execute("CREATE TABLE kaubad (id integer,nimi varchar(30),kogus integer, asukoht integer, primary key(id))");
            }
        }
    }

    public void addProduct(String pName, Integer pQty, Integer pLocation) throws ClassNotFoundException, SQLException{

        if(conn == null){
            createConnection();
        }else{
            PreparedStatement pAdd = conn.prepareStatement("INSERT INTO kaubad VALUES(?,?,?,?);");
            pAdd.setString(2,pName);
            pAdd.setInt(3,pQty);
            pAdd.setInt(4,pLocation);
            pAdd.execute();
            conn.close();
        }
    }

    public void deleteProduct() throws ClassNotFoundException, SQLException{

    }

}