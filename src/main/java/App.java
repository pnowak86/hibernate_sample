import com.mysql.jdbc.*;
import com.mysql.jdbc.Driver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

/**
 * Created by RENT on 2017-07-21.
 */
public class App {
    public static void main(String[] args) {


        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        Configuration configuration = new Configuration();
        configuration.configure(App.class.getClassLoader().getResource("hibernate/hibernate.cfg.xml"));
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();



        List<Material> materials = (List<Material>)session.createQuery("from Material").list();

        materials.forEach((material) -> {
            System.out.println(material.toString());
        });



        session.close();

        // public void start(){
        // DatabaseServer databaseServer = new DatabaseServer("localhost", "furniture_db", "user0","pas123");
//        DatabaseServer databaseServer = new DatabaseServer(
//                "localhost", "furniture_db", "user0", "pas123");
//
//        DatabaseSellersDAO.init(databaseServer);
        DatabaseSellersDAO dbsDAO = new DatabaseSellersDAO();
        dbsDAO.get();

        //Seller seller = new Seller (1,"Krzysztof","Szczepanski");
        // dbsDAO.update(seller);
        Seller seller = new Seller(7,"xxx","xxxx");
        //dbsDAO.add(seller);
        //dbsDAO.delete(seller);
        //dbsDAO.resetAutoIncrement(5);
        System.out.println("");
        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("table after change");
        dbsDAO.get();
    }


}
