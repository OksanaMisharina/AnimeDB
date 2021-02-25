package GUI;

import Connection.MyConnection;
import QUERY.AddAnime;
import QUERY.DeleteAnime;
import QUERY.Eps;
import QUERY.MyAnimeList;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainWindow extends JFrame {

    DefaultTableModel model = new DefaultTableModel();

    private JPanel mainPanel;
    private JPanel PANEL;
    private JPanel titlePanel;
    private JPanel epsPanel;
    private JLabel epsLabel;
    private JLabel userNameLabel;
    private JPanel animePanel;
    private JButton searchButton;
    private JPanel deletePanel;
    private JPanel animeListPanel;
    private JTextField searchTextField;
    private JPanel ratingPanel;
    private JPanel epsPane;

    public MainWindow(String userName) {
        this.userNameLabel.setText(userName);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);


        animeListLabel();
        setEpsCount();
        searchAnime();

    }

    public void searchAnime(){
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                SerchWindow serchWindow = new SerchWindow(userNameLabel.getText(), searchTextField.getText());
                serchWindow.setVisible(true);
                serchWindow.pack();
                serchWindow.setLocationRelativeTo(null);
                serchWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });
    }

    public void deleteAnime(JLabel label, JLabel title) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                DeleteAnime del = new DeleteAnime(MyConnection.getStatement());
                try {
                    del.delete(title.getText(), userNameLabel.getText());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                label.setForeground(new Color(255,0,0));
                title.setForeground(new Color(255,0,0));

            }
        });
    }

    public void setEpsCount() {
        Eps eps = new Eps(MyConnection.getStatement());
        int count = 0;
        ResultSet resultSet = null;
        try {
            resultSet = eps.epsCount(userNameLabel.getText());
            while (resultSet.next()) {
                count += resultSet.getInt("EPS_NUM");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (count % 10 == 1)
            epsLabel.setText("Просмотрено " + count + " эпизод");
        else if (count % 10 == 2 || count % 10 == 3 || count % 10 == 4)
            epsLabel.setText("Просмотрено " + count + " эпизода");
        else
            epsLabel.setText("Просмотрено " + count + " эпизодов");
    }

    public void animeListLabel() {

        try {
            ResultSet res = null;
            MyAnimeList aList = new MyAnimeList(MyConnection.getStatement());
            res = aList.showMyAnime(userNameLabel.getText());
            int n = 0;
            while (res.next()) {
                n++;
            }
            MyAnimeList animeList = new MyAnimeList(MyConnection.getStatement());
            ResultSet resultSet = animeList.showMyAnime(userNameLabel.getText());


            while (resultSet.next()) {

                deletePanel.setLayout(new GridLayout(n, 1));

                JLabel label = new JLabel("X", JLabel.CENTER);
                deletePanel.add(label);
                label.setForeground(new Color(61, 40, 20));
                label.setFont(new Font("Droid Serif", 0, 15));
                label.setMinimumSize(new Dimension(30, 30));

                animeListPanel.setLayout(new GridLayout(n, 1));
                JLabel jLabel = new JLabel(resultSet.getString("TITLE"));
                jLabel.setHorizontalAlignment(JLabel.LEFT);
                animeListPanel.add(jLabel);
                jLabel.setForeground(new Color(61, 40, 20));
                jLabel.setFont(new Font("Droid Serif", 0, 15));
                jLabel.setMinimumSize(new Dimension(-1, 30));

                deleteAnime(label, jLabel);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
