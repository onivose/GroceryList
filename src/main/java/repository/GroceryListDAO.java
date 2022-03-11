package repository;

import models.GroceryList;

import java.util.List;

public interface GroceryListDAO {
    List<GroceryList> getAllListsGivenUserId(Integer userID);
    void createList(GroceryList groceryList);
    void deleteList(Integer listID);

}
