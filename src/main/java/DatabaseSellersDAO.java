import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-07-21.
 */
public class DatabaseSellersDAO implements  SellersDAO {
    DatabaseServer databaseServer = new DatabaseServer(
            "localhost", "furniture_db", "user0", "pas123");


public List<Seller> get(){
    List<Seller> sellers = new ArrayList<>();
    Statement statement = null;

    try {

        databaseServer.connect();
        statement = databaseServer.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from sellers;");
        while(resultSet.next()){
            int id = resultSet.getInt(resultSet.findColumn("id"));
            String name = resultSet.getString(resultSet.findColumn("first_name"));
            String surname = resultSet.getString(resultSet.findColumn("surname"));
            sellers.add(new Seller(id,name,surname));
            System.out.println(id+" " +name + " "+ surname);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }


    return sellers;
    }

    public void add(Seller seller){
        Statement statement = null;
        try {
            databaseServer.connect();
            statement = databaseServer.createStatement();

            //statement.executeUpdate("insert into sellers (first_name, surname) values (\""+ seller.getName() +"\", \""+seller.getSurname() + "\"");

            statement.executeUpdate("insert into sellers (first_name, surname) values (\"" + seller.getName() +"\", \""+ seller.getSurname()+"\")");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update(Seller seller) {
        Statement statement = null;
        try {
            statement = databaseServer.createStatement();

            statement.executeUpdate("update sellers set first_name = \"" + seller.getName() +
                    "\", surname = \"" + seller.getSurname() +
                    "\" where id = " + seller.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void delete(Seller seller) {
        Statement statement = null;
        try {
            statement = databaseServer.createStatement();

            statement.executeUpdate("delete from sellers where id = " + seller.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void resetAutoIncrement(int idx){
        Statement statement = null;
        try {
            statement = databaseServer.createStatement();

            statement.executeUpdate("ALTER TABLE sellers AUTO_INCREMENT =" + idx);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
