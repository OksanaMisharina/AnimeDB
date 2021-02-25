package GUI;

import Connection.MyConnection;
import QUERY.AddUser;
import QUERY.UserExist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Registration extends JFrame {

    private JPanel mainPanel;
    private JLabel registrationLabel;
    private JTextField loginTextField;
    private JTextField PasswordTextField;
    private JTextField repeatPasswordTextField;
    private JButton registrationButton;
    private JLabel loginLabel;
    private JLabel errorLabel;

    public Registration() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        LoginTextHide();
        LoginTextAdd();
        PasswordTextHide();
        PasswordTextAdd();
        RepeatPasswordTextHide();
        RepeatPasswordTextAdd();

        GoToLogin();

        registrationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    UserExist userExist = new UserExist(MyConnection.getStatement());

                    if (userExist.execute(loginTextField.getText())) {
                        errorLabel.setText("Такой логин уже существует");
                        loginTextField.setBackground(new Color(255, 227, 250));
                    } else {
                        errorLabel.setText("");
                        loginTextField.setBackground(new Color(253, 253, 253));
                        if (!PasswordTextField.getText().equals(repeatPasswordTextField.getText())) {
                            errorLabel.setText("Пароли отличаются");
                            PasswordTextField.setBackground(new Color(255, 227, 250));
                            repeatPasswordTextField.setBackground(new Color(255, 227, 250));
                        } else {
                            errorLabel.setText("");
                            PasswordTextField.setBackground(new Color(253, 253, 253));
                            repeatPasswordTextField.setBackground(new Color(253, 253, 253));

                            AddUser addUser = new AddUser(loginTextField.getText(), PasswordTextField.getText());
                            addUser.execute(MyConnection.getStatement());

                            Login login = new Login();
                            login.setVisible(true);
                            login.pack();
                            login.setLocationRelativeTo(null);
                            login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            dispose();
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private void GoToLogin() {
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Login login = new Login();
                login.setVisible(true);
                login.pack();
                login.setLocationRelativeTo(null);
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });
    }

    private void LoginTextHide() {
        loginTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if (loginTextField.getText().trim().equals("Введите логин")) {
                    loginTextField.setText("");
                    loginTextField.setForeground(new Color(61, 40, 20));
                }
            }
        });
    }

    private void LoginTextAdd() {
        loginTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if (loginTextField.getText().trim().equals("")) {
                    loginTextField.setForeground(new Color(150, 150, 150));
                    loginTextField.setText("Введите логин");
                }
            }
        });
    }

    private void PasswordTextHide() {
        PasswordTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if (PasswordTextField.getText().trim().equals("Введите пароль")) {
                    PasswordTextField.setText("");
                    PasswordTextField.setForeground(new Color(61, 40, 20));
                }
            }
        });
    }

    private void PasswordTextAdd() {
        PasswordTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if (PasswordTextField.getText().trim().equals("")) {
                    PasswordTextField.setForeground(new Color(150, 150, 150));
                    PasswordTextField.setText("Введите пароль");
                }
            }
        });
    }

    private void RepeatPasswordTextHide() {
        repeatPasswordTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if (repeatPasswordTextField.getText().trim().equals("Повторите пароль")) {
                    repeatPasswordTextField.setText("");
                    repeatPasswordTextField.setForeground(new Color(61, 40, 20));
                }
            }
        });
    }

    private void RepeatPasswordTextAdd() {
        repeatPasswordTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if (repeatPasswordTextField.getText().trim().equals("")) {
                    repeatPasswordTextField.setForeground(new Color(150, 150, 150));
                    repeatPasswordTextField.setText("Повторите пароль");
                }
            }
        });
    }

}
