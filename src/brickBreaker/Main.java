package brickBreaker;

import java.sql.SQLException;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws SQLException {

//        Login login = new Login();
        DatabaseManager db = new DatabaseManager();
        System.out.println(Arrays.deepToString(db.getLeaderboard()));
//        System.out.println(Arrays.deepToString(db.getLeaderboard()));
    }

}



