package PharmaGest;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class databaseConnection {

    public  boolean writeToDatabase(String nom, String password) throws SQLException {


        String url = "jdbc:postgresql://localhost:5432/pharmagest"; //url PostgreSql
        String databaseuser = "postgres"; //nom de la bdd
        String databasePassword = "Nissaboss976"; // mdp de bdd

        String query = "SELECT * FROM utilisateur WHERE nom = ? and password = ?";

        try( Connection conn = DriverManager.getConnection(url, databaseuser, databasePassword);
             PreparedStatement pst = conn.prepareStatement(query);) {

            pst.setString(1, nom);
            pst.setString(2, password);

            System.out.println(pst);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return false;

    }



    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
            }
            System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
            System.err.println("Message: " + e.getMessage());
            Throwable t = ex.getCause();
            while (t != null) {
                System.out.println("Cause: " + t);
                t = t.getCause();
            }
        }
    }

    public static void inscriptionToDatabase(String nomuser , String prenomuser, String emailuser, String adresseuser, String teluser, String passworduser) throws SQLException {


        String url = "jdbc:postgresql://localhost:5432/pharmagest"; //url PostgreSql
        String databaseuser = "postgres"; //nom de la base de donnée
        String databasePassword = "Nissaboss976"; // mdp de l'ordinateur

        String nom = nomuser;
        String prenom = prenomuser;
        String email = emailuser;
        String adresse = adresseuser;
        String tel = teluser;
        String password = passworduser;

        String query = "INSERT INTO utilisateur(nom, prenom, email, adresse, tel, password) VALUES(?,?,?,?,?,?)";

        try { Connection conn = DriverManager.getConnection(url, databaseuser, databasePassword);
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1,nom);
            pst.setString(2,prenom);
            pst.setString(3,email);
            pst.setString(4,adresse);
            pst.setInt(5,Integer.parseInt(tel));
            pst.setString(6,password);
            pst.executeUpdate();

            System.out.println("Connection Valide ! ");}

        catch (Exception e) {
            Logger lgr = Logger.getLogger(databaseConnection.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);}
    }
}