package brickBreaker;

import java.sql.*;
import java.util.Objects;

public class DatabaseManager {
    Statement stmt;

    public DatabaseManager() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://65.108.218.58:33306/mysql?autoReconnect=true&useSSL=false", "root", "aet4gieh9etie3Nokoo7bai4");
        this.stmt = conn.createStatement();
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

    public void registerUser(String username, String email, int phone, String password) throws SQLException {
        String query = "insert into users (username, email, phone_number, password, high_score) values ('" + username + "', '" + email + "', " + phone + ", '" + password + "', 0);";
        stmt.executeUpdate(query);
    }

    public boolean loginUser(String name, String password) throws SQLException {
        String query = "select password from users where username like '" + name + "';";
        ResultSet result = stmt.executeQuery(query);
        String user_password = null;
        while (result.next()) {
            user_password = result.getString("password");
        }
        return Objects.equals(user_password, password);

    }

    public boolean usernameExists(String name) throws SQLException {
        String query = "select username from users where username like '" + name + "';";
        ResultSet result = stmt.executeQuery(query);
        return result.next();
    }

    public String[][] getLeaderboard() throws SQLException {
        String query = "select username, high_score from users limit 1;";
        ResultSet result = stmt.executeQuery(query);
        String[][] leaderboard = new String[10][2];
        String[] record = new String[2];
        int count = 0, score;
        String name;
        while (result.next()){
            name = result.getString("username");
            score = result.getInt("high_score");
            record[0] = name;
            record[1] = String.valueOf(score);
            leaderboard[count] = record;
        }
        return leaderboard;
    }
}
