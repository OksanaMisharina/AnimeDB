package QUERY;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteAnime{

    private String sql;
    private Statement statement;

    public DeleteAnime(Statement statement) {

        sql =new String("");
        this.statement = statement;
    }



    public boolean delete(String title, String userName) throws SQLException {
        sql = "delete from \"WATCHING\" WHERE \"ANIME_ID\" IN(SELECT \"ANIME_ID\" " +
                "FROM \"ANIME\" WHERE \"TITLE\" = '"+title+"')AND \"USER_ID\" " +
                "IN(SELECT \"USER_ID\" FROM \"USERS\" WHERE \"NAME\" = '"+userName+"')";

        statement.executeUpdate(sql);
        return true;
    }
}
