package QUERY;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Eps {
    String sql;
    Statement statement;

    public Eps(Statement statement) {
        sql = "SELECT \"EPS_NUM\" FROM \"ANIME\" WHERE \"ANIME_ID\" IN(SELECT \"ANIME_ID\" " +
                "FROM \"WATCHING\" WHERE \"USER_ID\" IN(SELECT \"USER_ID\" FROM \"USERS\" " +
                "WHERE \"NAME\" = '";
        this.statement = statement;
    }

    public ResultSet epsCount(String name) throws SQLException {
        sql += (name + "'))");
        return statement.executeQuery(sql);
    }
}

