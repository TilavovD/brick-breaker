package brickBreaker;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        //When program starts, SignUp page is shown
        new SignUp();
        DatabaseManager.getObject().closeConnection();
    }

}



