import GUI.Login;
import Connection.MyConnection;
import QUERY.AddUser;

import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        MyConnection myConnection = new MyConnection();

        JFrame jFrame = new Login();
        jFrame.setVisible(true);

    }
}
