package QUERY;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindAnime {

    String sql;
    Statement statement;

    public FindAnime(Statement statement) {
        sql = new String("");
        this.statement = statement;
    }

    public ResultSet find(String title) throws SQLException {

        sql = "SELECT \"TITLE\" FROM \"ANIME\" WHERE SUBSTRING(\"TITLE\", " +
                "position('";
        return statement.executeQuery(sql+title+"' in \"TITLE\"), "+title.length()+")  = '"+title+"'");
    }
}
