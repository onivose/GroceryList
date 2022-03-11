package repository;

import models.User;

import java.sql.*;
/*
* What is needed to connect to our postgresql database on AWS unsing JDBC?
*   - endpoint (url) (Location of our database)
*       - syntax: jdbc:postgresql://[AWS_RDS_ENDPOINT]/[DATABASE_NAME]
*   - username for our aws database
*   - password for our aws database
*   - driver
*
* Interfaces and classes of JDBC:
*   - Connection (interface) : this is the active connection with the database
*   - DriverManager: this retrieves the connection from our database
*   - Statement : object representation of the sql statement (does NOT prevent sql injection)
*       - PreparedStatement: object representation of the sql statement (DOES prevent sql injection)
*   - ResultSet : object representation of the result set
*
* The most common error I see when connecting to JDBC is "no suitable driver found"
*   - you don't have the driver
*   - url string is incorrect
*
* */

public class UserDAOImpl implements UserDAO {
    String url = "jdbc:postgresql://on-fsj-db.c4ackd6nqaen.us-east-1.rds.amazonaws.com/grocerylist";
    String username = "postgres";
    String password ="p4ssw0rd";

    @Override
    public User getUserGivenUsername(String username) {
        User user = null;

        try {
            // retrieve active connection from our database
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "select * from users where user_username = ?;";

            //preparing our sql statement
            PreparedStatement ps = conn.prepareStatement(sql);

            // we are adding the username into the question mark in the sql statement dynamically
            ps.setString(1,username); // the 1 references the first question mark
            // NOTE: you would need multiple set string statements if you have multiple question marks

            ResultSet rs = ps.executeQuery();

            while (rs.next()){ // rs.next()
                 user = new User(rs.getInt(1),
                         rs.getString(2),
                         rs.getString(3),
                         rs.getString(4),
                         rs.getString(5),
                         rs.getTimestamp(6)
                         );

            }


        } catch (SQLException sqle){
            sqle.printStackTrace();
        }

        return user;
    }

    @Override
    public void createUser(User user) {

        try{
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);

            String sql = "insert into users (user_username, user_password, user_firstname, user_lastname) values (?,?,?,?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getFirstname());
            ps.setString(4,user.getLastname());

            ps.executeUpdate(); // executeUpdate for DML statements


        } catch (SQLException sqle){
            sqle.printStackTrace();
        }

    }
}

