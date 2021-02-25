package GUI;

import QUERY.UserExist;
import Connection.MyConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Login extends JFrame{

    private JPanel mainPanel;
    private JTextField LoginTextField;
    private JPasswordField PasswordField;
    private JButton EnterButton;
    private JPanel AuthorizatoinPanel;
    private JLabel RegistrationLabel;
    private JLabel AuthorizationLabel;
    private JLabel errorLabel;

    public Login(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        LoginTextHide();
        LoginTextAdd();
        PasswordTextHide();
        PasswordTextAdd();

        GoToRegistration();
        loginApp();
    }

    private void loginApp() {
        EnterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                UserExist userExist = new UserExist(MyConnection.getStatement());
                try {
                    if(userExist.execute(LoginTextField.getText(), PasswordField.getText())){
                        errorLabel.setText("");
                        LoginTextField.setBackground(new Color(253, 253, 253));
                        PasswordField.setBackground(new Color(253, 253, 253));

                        MainWindow mainWindow = new MainWindow(LoginTextField.getText());

                        mainWindow.setVisible(true);
                        mainWindow.pack();
                        mainWindow.setLocationRelativeTo(null);
                        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        dispose();
                    }
                    else{
                        errorLabel.setText("Неверный логин или пароль");
                        LoginTextField.setBackground(new Color(255, 227, 250));
                        PasswordField.setBackground(new Color(255, 227, 250));

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private void GoToRegistration() {
        RegistrationLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Registration registration = new Registration();
                registration.setVisible(true);
                registration.pack();
                registration.setLocationRelativeTo(null);
                registration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });
    }

    private void LoginTextHide() {
        LoginTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if(LoginTextField.getText().trim().equals("Логин")){
                    LoginTextField.setText("");
                    LoginTextField.setForeground(new Color(61, 40, 20));
                }
            }
        });
    }

    private void LoginTextAdd() {
        LoginTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
               super.focusLost(e);

                if(LoginTextField.getText().trim().equals("")){

                    LoginTextField.setForeground(new Color(150, 150, 150));
                    LoginTextField.setText("Логин");

                }
            }
        });
    }

    private void PasswordTextHide() {
        PasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if(PasswordField.getText().trim().equals("Пароль")){
                    PasswordField.setText("");
                    PasswordField.setForeground(new Color(61, 40, 20));
                }
            }
        });
    }

    private void PasswordTextAdd() {
        PasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if(PasswordField.getText().trim().equals("")){
                    PasswordField.setForeground(new Color(150, 150, 150));
                    PasswordField.setText("Пароль");
                }
            }
        });
    }

}
