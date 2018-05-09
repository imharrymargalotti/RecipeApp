package edu.ithaca.recipeApp;

import javax.security.auth.callback.TextOutputCallback;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Favorites {

    //User adds a favorite
    public static void addFavorite(int userID, int recipeID){
        Connection connection = null;

        String url = "jdbc:sqlite:src/test/resources/db/recipes.db";

        String driverName = "com.mysql.jdbc.Driver";

        try{
            Class.forName(driverName).newInstance();
            connection = DriverManager.getConnection(url);

            try{

                Statement stmt = connection.createStatement();
                String selectquery = "UPDATE USER_TO_RECIPE SET DID_SAVE = " + 1 + " WHERE USER_ID = " + userID + " AND RECIPE_ID = " + recipeID;

                stmt.executeQuery(selectquery);

            }
            catch(SQLException s){
                System.out.println(s);
            }
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //when user is related or recipe is added set defaults in db
    //PRINT USERS FAVORITES
    public static String viewFavorites(int userID){
        //System.out.println("called");
        Connection connection = null;
        String favorites = "Favorites: ";
        String url = "jdbc:sqlite:src/test/resources/db/recipes.db";

        String driverName = "com.mysql.jdbc.Driver";
        ArrayList<String> rowArray = new ArrayList<String>();

        Scanner reader = new Scanner(System.in);

        try{
            Class.forName(driverName).newInstance();
            connection = DriverManager.getConnection(url);

            try{

                Statement stmt = connection.createStatement();
                String selectquery = "SELECT * FROM USER_TO_RECIPE JOIN RECIPES ON RECIPE_ID=RECIPES.ID WHERE USER_ID = " + userID + " AND DID_SAVE = " + 1;

                ResultSet rs = stmt.executeQuery(selectquery);
                while(rs.next()){
                    favorites+=rs.getString("TITLE")+"\n";
                }

            }
            catch(SQLException s){
                System.out.println(s);
            }
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return favorites;

    }


    public static void main(String[] args) {
        addFavorite(2,3);
        viewFavorites(2);
    }

}
