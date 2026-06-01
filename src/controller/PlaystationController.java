/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import model.Playstation;
/**
 *
 * @author Asus
 */
public class PlaystationController {
    
    Connection conn;

    public PlaystationController() {

        conn = Koneksi.getKoneksi();

    }
    
    public DefaultTableModel tampilData() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama PS");
        model.addColumn("Tipe");
        model.addColumn("Status");
        model.addColumn("Harga/Jam");

        try {

            String sql = "SELECT * FROM playstation";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getInt("id_ps"),
                    rs.getString("nama_ps"),
                    rs.getString("tipe_ps"),
                    rs.getString("status_ps"),
                    rs.getInt("harga_per_jam")
                });

            }

        } catch (Exception e) {

            System.out.println("Error tampil data : " + e);

        }

        return model;
    }
    
    public boolean tambahData(Playstation ps) {

        try {

            String sql = "INSERT INTO playstation "
                    + "(nama_ps, tipe_ps, status_ps, harga_per_jam) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, ps.getNamaPs());
            pst.setString(2, ps.getTipePs());
            pst.setString(3, ps.getStatusPs());
            pst.setInt(4, ps.getHargaPerJam());

            pst.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println("Error tambah data : " + e);

            return false;

        }

    }
    
    public boolean updateData(Playstation ps) {

        try {

            String sql =
            "UPDATE playstation SET "
            + "nama_ps=?, "
            + "tipe_ps=?, "
            + "status_ps=?, "
            + "harga_per_jam=? "
            + "WHERE id_ps=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, ps.getNamaPs());
            pst.setString(2, ps.getTipePs());
            pst.setString(3, ps.getStatusPs());
            pst.setInt(4, ps.getHargaPerJam());
            pst.setInt(5, ps.getIdPs());

            pst.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println(e);

            return false;
        }

    }
    
    public boolean hapusData(int idPs){

        try{

            String sql =
                    "DELETE FROM playstation "
                    + "WHERE id_ps=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, idPs);

            pst.executeUpdate();

            return true;

        }catch(Exception e){

            System.out.println(e);

            return false;

        }

    }
}