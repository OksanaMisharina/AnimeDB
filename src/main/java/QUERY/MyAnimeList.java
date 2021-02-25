package QUERY;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyAnimeList {
    String sql;
    Statement statement;

    public MyAnimeList(Statement statement) {
        sql = new String("SELECT \"TITLE\" FROM \"ANIME\" WHERE \"ANIME_ID\" IN(SELECT " +
                "\"ANIME_ID\" FROM \"WATCHING\" WHERE \"USER_ID\" IN(SELECT \"USER_ID\" " +
                "FROM \"USERS\" WHERE \"NAME\" = '");
        this.statement = statement;
    }

    public ResultSet showMyAnime(String name) throws SQLException {
        sql+=(name +"'))");
        return statement.executeQuery(sql);
    }

}
