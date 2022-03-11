package repository;

import models.GroceryList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroceryListDAOImpl implements GroceryListDAO {
    String url = "jdbc:postgresql://on-fsj-db.c4ackd6nqaen.us-east-1.rds.amazonaws.com/grocerylist";
    String username = "postgres";
    String password ="p4ssw0rd";

    @Override
    public List<GroceryList> getAllListsGivenUserId(Integer userID) { //dql statement
        List<GroceryList> lists = new ArrayList<>();

        try{
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "select * from lists where user_id_fk = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                lists.add(new GroceryList(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }

        } catch (SQLException sqle){
            sqle.printStackTrace();
        }

        return lists;
    }

    @Override
    public void createList(GroceryList groceryList) { // DML Statement
        try{
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "";

        } catch (SQLException sqle){
            sqle.printStackTrace();
        }

    }

    @Override
    public void deleteList(Integer listID) { // DML statement

    }
}
