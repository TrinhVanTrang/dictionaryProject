package jfxcode;

import java.sql.*;
import java.util.Vector;

public class ConnectDatabase {
    public final static String LINKDB = "jdbc:sqlite:database/wordDB.db";

    public static Connection connectToDataBase(String url) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static Vector<Vocabulary> querySelectAllDic() {
        Vector<Vocabulary> result = new Vector<Vocabulary>();
        String query = "select * from av ";
        Connection connection = connectToDataBase(ConnectDatabase.LINKDB);
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    Vocabulary word = new Vocabulary(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4), resultSet.getString(4));
                    result.add(word);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection error!");
            return null;
        }
        return result;
    }

    public static Vector<Vocabulary> querySelectAllHistory() {
        Vector<Vocabulary> result = new Vector<Vocabulary>();
        String query = "SELECT *\n" +
                "FROM av\n" +
                "INNER JOIN history ON history.wordID=av.id;";
        Connection connection = connectToDataBase(ConnectDatabase.LINKDB);
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    Vocabulary word = new Vocabulary(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4), resultSet.getString(4));
                    result.add(word);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection error!");
            return null;
        }
        return result;
    }

    public static Vector<Vocabulary> querySelectAllMyWord() {
        Vector<Vocabulary> result = new Vector<Vocabulary>();
        String query = "SELECT *\n" +
                "FROM av\n" +
                "INNER JOIN myword ON myword.wordID=av.id;";
        Connection connection = connectToDataBase(ConnectDatabase.LINKDB);
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    Vocabulary word = new Vocabulary(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4), resultSet.getString(4));
                    result.add(word);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection error!");
            return null;
        }
        return result;
    }

    public static Vector<Vocabulary> querySelect(String search, String url, String table) {
        Vector<Vocabulary> result = new Vector<Vocabulary>();
        if (search.equals("")) {
            return result;
        }
        String query = String.valueOf('"') + search + "%" + String.valueOf('"');
        query = "select id,word,html from " + table + " where word like" + query;
        Connection connection = connectToDataBase(url);
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    Vocabulary word = new Vocabulary(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), "", "");
                    result.add(word);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection error!");
            return null;
        }
        return result;
    }

    public static Vocabulary querySelectOne(String search, String url, String table) {
        Vocabulary result = new Vocabulary();
        if (search.equals("")) {
            return result;
        }
        String query = String.valueOf('"') + search + String.valueOf('"');
        query = "select * from " + table + " where word =" + query;
        Connection connection = connectToDataBase(url);
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                result.setAll(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection error!");
            return null;
        }
        return result;
    }

    public static void queryInsert(Vocabulary vocabulary, String url, String table) {
        Connection connection = connectToDataBase(LINKDB);
        String query = "insert into " + table + "(wordID,word) values (" + String.valueOf(vocabulary.getId()) + "," + "'" + vocabulary.getWord() + "'" + ")";
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                statement.executeUpdate(query);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection error!");
        }
    }

    public static Vector<Vocabulary> querySelectMyWord(String search,String url) {
        Vector<Vocabulary> result = new Vector<Vocabulary>();
//        if (search.equals("")) {
//            return result;
//        }
        String query = String.valueOf('"') + search + "%" + String.valueOf('"');
        query = "select wordID,word from " + "myword" + " where word like" + query;
        Connection connection = connectToDataBase(url);
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    Vocabulary word = new Vocabulary(resultSet.getInt(1), resultSet.getString(2), "", "", "");
                    result.add(word);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection error!");
            return null;
        }
        return result;
    }

    public static void queryDelete(Vocabulary vocabulary, String url, String table) {
        Connection connection = connectToDataBase(LINKDB);
        //System.out.println(vocabulary.getId());
        String query = "delete from " + table + "where wordID =" + String.valueOf(vocabulary.getId());
        if(connection!=null) {
            try {
                PreparedStatement preparedStatement=connection.prepareStatement("delete from history where wordID =?");
                preparedStatement.setInt(1,vocabulary.getId());
                preparedStatement.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection error!");
        }

    }

    public static boolean IsConcern(Vocabulary vocabulary, String url, String table) {
        Connection connection = connectToDataBase(LINKDB);
        String query = "select id,word from " + table + " where wordID =" + String.valueOf(vocabulary.getId());
        if(connection!=null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    connection.close();
                    return true;
                }
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("Connection error!");
        }
        return false;
    }

}

