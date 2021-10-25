package jfxcode;

import java.sql.*;
import java.util.Vector;

public class ConnectDatabase {
    final static String LINKDB = "jdbc:sqlite:database/wordDB.db";

    public static Connection connectToDataBase(String url) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static Vector<Vocabulary> querySelect(String search, String url) {
        Vector<Vocabulary> result = new Vector<Vocabulary>();
        if (search.equals("")) {
            return result;
        }
        String query = String.valueOf('"') + search + "%" + String.valueOf('"');
        query = "select id,word,html from av where word like" + query;
        Connection connection = connectToDataBase(url);
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    Vocabulary word = new Vocabulary(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), "", "");
                    result.add(word);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection error!");
            return null;
        }
        return result;
    }

    public static void queryInsert(Vocabulary vocabulary,String url,String table) {
        Connection connection=connectToDataBase(LINKDB);
        try {
            Statement statement=connection.createStatement();
            String query="insert into "+table+"(wordID,word)"+"values ("+String.valueOf(vocabulary.getId())+","+String.valueOf(vocabulary.getWord())+")";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryDelete(Vocabulary vocabulary,String url,String table) {
        Connection connection=connectToDataBase(LINKDB);
        try {
            Statement statement=connection.createStatement();
            String query="delete from "+table+"where id= "+String.valueOf(vocabulary.getId());
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean IsConcern(Vocabulary vocabulary,String url,String table) {
        Connection connection = connectToDataBase(LINKDB);
        try {
            Statement statement = connection.createStatement();
            String query = "select id,word from " + table + " where id =" + String.valueOf(vocabulary.getId());
            ResultSet resultSet=statement.executeQuery(query);
            if(resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}

