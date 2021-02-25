package GUI;

import Connection.MyConnection;
import QUERY.AddAnime;
import QUERY.DeleteAnime;
import QUERY.FindAnime;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SerchWindow extends JFrame {
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JPanel animeSearchList;
    private JPanel searchPanel;
    private JLabel goToMainLabel;
    private JPanel addPanel;
    private JPanel animeListPanel;

    private String username;

    public SerchWindow(String userName, String search) {
        this.username = userName;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.searchTextField.setText(search);

        goToMainWindow();
        searchAnime();


    }
    public void addAnimeToMyList(JLabel label, JLabel title) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                AddAnime addAnime = new AddAnime(MyConnection.getStatement());
                try {
                    addAnime.add(title.getText(), username);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                label.setForeground(new Color(0,255,0));
                title.setForeground(new Color(0,255,0));
            }
        });
    }

    public void searchAnime() {

        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                addPanel.removeAll();
                animeListPanel.removeAll();

                try {
                    ResultSet res = null;
                    FindAnime fA = new FindAnime(MyConnection.getStatement());
                    res = fA.find(searchTextField.getText());
                    int n = 0;
                    while (res.next()) {
                        n++;
                    }
                    FindAnime findAnime = new FindAnime(MyConnection.getStatement());
                    ResultSet resultSet = findAnime.find(searchTextField.getText());

                    while (resultSet.next()) {

                        System.out.println(resultSet.getString("TITLE"));

                        addPanel.setLayout(new GridLayout(n, 1));

                        JLabel label = new JLabel("+");
                        label.setHorizontalAlignment(JLabel.LEFT);
                        addPanel.add(label);
                        label.setForeground(new Color(61, 40, 20));
                        label.setFont(new Font("Droid Serif", 0, 18));
                        label.setMinimumSize(new Dimension(15, 30));
                        label.setMaximumSize(new Dimension(15, 30));

                        animeListPanel.setLayout(new GridLayout(n, 1));
                        JLabel jLabel = new JLabel(resultSet.getString("TITLE"));
                        jLabel.setHorizontalAlignment(JLabel.LEFT);
                        animeListPanel.add(jLabel);
                        jLabel.setForeground(new Color(61, 40, 20));
                        jLabel.setFont(new Font("Droid Serif", 0, 15));
                        jLabel.setMinimumSize(new Dimension(-1, 30));

                        addAnimeToMyList(label, jLabel);

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });
    }

    public void goToMainWindow() {
        goToMainLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                MainWindow mainWindow = new MainWindow(username);

                mainWindow.setVisible(true);
                mainWindow.pack();
                mainWindow.setLocationRelativeTo(null);
                mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });
    }

    private void searchTextHide() {
        searchTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if (searchTextField.getText().trim().equals("Введите название")) {
                    searchTextField.setText("");
                    searchTextField.setForeground(new Color(61, 40, 20));
                }
            }
        });
    }

    private void searchTextAdd() {
        searchTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if (searchTextField.getText().trim().equals("")) {

                    searchTextField.setForeground(new Color(150, 150, 150));
                    searchTextField.setText("Введите название");

                }
            }
        });
    }

}
