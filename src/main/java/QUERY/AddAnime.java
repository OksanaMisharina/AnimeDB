package QUERY;

import java.sql.SQLException;
import java.sql.Statement;

public class AddAnime{
    String sql;
    Statement statement;

    public AddAnime(Statement statement) {

        sql = new String("INSERT INTO \"WATCHING\"(\"ANIME_ID\", \"USER_ID\") " +
                "VALUES((SELECT \"ANIME_ID\" FROM \"ANIME\" WHERE \"TITLE\" = '");
        this.statement = statement;

    }

    public void add(String title, String userName) throws SQLException {
        statement.executeUpdate(sql+title+"'),(SELECT \"USER_ID\" FROM \"USERS\" WHERE \"NAME\" = '"+userName+"'))");

    }
}
