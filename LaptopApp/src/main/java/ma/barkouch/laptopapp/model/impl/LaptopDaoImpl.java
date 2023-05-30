package ma.barkouch.laptopapp.model.impl;


import ma.barkouch.laptopapp.model.Dao;
import ma.barkouch.laptopapp.entities.Laptop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaptopDaoImpl implements Dao<Laptop,Integer> {
    private Connection conn = DB.getConnection();  //? etablir la connection vers la DB

    @Override
    public void insert(Laptop object) {
        PreparedStatement ps = null;  //? creer un objet PrepareStatement pour prepare la requete sql
        try {
            ps = conn.prepareStatement("INSERT INTO laptop (serial, marque, os, size ) VALUES (NULL,?,?,?)");

            ps.setString(1, object.getMarque());
            ps.setString(2, object.getOs());
            ps.setString(3, String.valueOf(object.getSize()));

            int rowsAffected = ps.executeUpdate(); //? execute la requete

            if (rowsAffected > 0) {
                System.out.println("Insertion bien fait");
            } else {
                System.out.println("Aucune ligne renvoyée");
            }
        } catch (SQLException e) {
            System.err.println("problème d'insertion d'un laptop");;
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Laptop object) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                    "UPDATE laptop SET marque = ?, os = ?, size = ? WHERE serial = ?");

            ps.setString(1, object.getMarque());
            ps.setString(2, object.getOs());
            ps.setString(3, String.valueOf(object.getSize()));
            ps.setInt(4, object.getSerial());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("problème de mise à jour d'un Laptop");;
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM laptop WHERE serial = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("problème de suppression d'un laptop");;
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public List<Laptop> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                    "SELECT * FROM laptop");
            rs = ps.executeQuery();
            List<Laptop> list = new ArrayList<>();

            while (rs.next()) {
                Laptop laptop = new Laptop(
                        rs.getInt("serial"),
                        rs.getString("marque"),
                        rs.getString("os"),
                        Laptop.Size.valueOf(rs.getString("size"))
                );
                list.add(laptop);
            }

            return list;
        } catch (SQLException e) {
            System.err.println("problème de requête pour sélectionner les Laptops");
            return null;
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Laptop findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                    "SELECT * FROM laptop WHERE serial = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Laptop laptop = new Laptop(
                        rs.getInt("serial"),
                        rs.getString("marque"),
                        rs.getString("os"),
                        Laptop.Size.valueOf(rs.getString("size")));
                return laptop;
            }
            return null;
        } catch (SQLException e) {
            System.err.println("problème de requête pour trouver le laptop");
            return null;
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
    @Override
    public boolean exist(Laptop object) {
        List<Laptop> list = findAll();
        return list.contains(object);
    }
}
