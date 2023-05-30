package ma.barkouch.laptopapp.model.impl;

import ma.barkouch.laptopapp.model.Dao;
import ma.barkouch.laptopapp.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements Dao<User,String> {
    private Connection conn = DB.getConnection();  //? etablir la connection vers la DB

    @Override
    public void insert(User object) {
        PreparedStatement ps = null;  //? creer un objet PrepareStatement pour prepare la requete sql

        try {
            ps = conn.prepareStatement("INSERT INTO User (username, passwd ) VALUES (?,?)");

            ps.setString(1, object.getUsername());
            ps.setString(2, object.getPasswd());

            int rowsAffected = ps.executeUpdate(); //? execute la requete

            if (rowsAffected > 0) {
                System.out.println("ligne envoyée");
            } else {
                System.out.println("Aucune ligne renvoyée");
            }
        } catch (SQLException e) {
            System.err.println("problème d'insertion d'un config");;
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(User object) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public List<User> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                    "SELECT * FROM user");
            rs = ps.executeQuery();
            ArrayList<User> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new User(rs.getString("username"),rs.getString("passwd")));
            }

            return list;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public User findById(String id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                    "SELECT * FROM user WHERE username = ?");

            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                User usr = new User(rs.getString("username"), rs.getString("passwd"));
                return usr;
            }
            return null;
        } catch (SQLException e) {
            System.err.println("problème de requête pour trouver l'utilisateur");
            return null;
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public boolean exist(User object) {
        List<User> list = findAll();
        return list.contains(object);
    }
}
