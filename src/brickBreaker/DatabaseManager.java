package brickBreaker;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.Objects;

public class DatabaseManager {
    Statement stmt;
    Connection conn;

    static DatabaseManager db_manager;

    static {
        try {
            db_manager = new DatabaseManager();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private DatabaseManager() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:mysql://65.108.218.58:33306/mysql?autoReconnect=true&useSSL=false", "root", "aet4gieh9etie3Nokoo7bai4");
        this.stmt = this.conn.createStatement();
    }

    public int getHighScore(String name) throws SQLException {
        String query = "select high_score from users where username like '" + name + "'";
        ResultSet result = stmt.executeQuery(query);
        int high_score = 0;
        while (result.next()) {
            high_score = result.getInt("high_score");
        }
        return high_score;
    }

    public void setHighScore(String username, int score) throws SQLException {
        String query = "update users set high_score =" + score + " where username like '" + username + "'";
        stmt.executeUpdate(query);
    }

    public void registerUser(String username, String password) throws SQLException {
        String query = "insert into users (username, password, high_score) values ('" + username + "', '" + password + "', 0);";
        stmt.executeUpdate(query);
    }

    public boolean usernameExists(String name) throws SQLException {
        String query = "select username from users where username like '" + name + "';";
        ResultSet result = stmt.executeQuery(query);
        return result.next();
    }

    public boolean userExists(String name, String password) throws SQLException {
        String query = "select username from users where username like '" + name + "' and password like '" + password + "';";
        ResultSet result = stmt.executeQuery(query);
        return result.next();
    }

    public String[][] getLeaderboard() throws SQLException {
        String query = "select username, high_score from users order by high_score desc limit 10;";
        ResultSet result = stmt.executeQuery(query);
        String[][] leaderboard = new String[10][3];
        String[] record;
        int count = 0, score;
        String name;
        while (result.next()) {
            record = new String[3];
            name = result.getString("username");
            score = result.getInt("high_score");
            record[0] = String.valueOf(count + 1);
            record[1] = name;
            record[2] = String.valueOf(score);
            leaderboard[count] = record;

            count++;
        }
        return leaderboard;
    }

    public void closeConnection() throws SQLException {
        this.conn.close();
    }
}
