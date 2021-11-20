package sample;

import java.sql.Connection;
import java.sql.DriverManager ;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException,SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");


        dbConnection = DriverManager.getConnection(connectionString,
                dbUser,dbPass);

        return dbConnection;
    }

    public void  signUpUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLE +"("+
                Const.USER_FIRSTNAME + "," + Const.USER_LASTNAME + "," +
                Const.USER_USERNAME + "," + Const.USER_PASSWORD + "," +
                Const.USER_DATEBIRTH + "," + Const.USER_LOCATION  + "," +
                Const.USER_GENDER + ")" + "VALUES(?,?,?,?,?,?,?)";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,user.getFirstname());
            prSt.setString(2,user.getLastname());
            prSt.setString(3,user.getUsername());
            prSt.setString(4,user.getPassword());
            prSt.setString(5,user.getDatebirth());
            prSt.setString(6,user.getLocation());
            prSt.setString(7,user.getGender());

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user) {
        ResultSet reSet = null;

        //String select = "SELECT * FROM" + Const.USER_TABLE + "WHERE" +
        //                Const.USER_USERNAME + "=? AND" + Const.USER_PASSWORD + "=?";

        String select = "SELECT * FROM users WHERE username =? AND password=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,user.getUsername());
            prSt.setString(2,user.getPassword());

            reSet= prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return reSet;

    }

}
