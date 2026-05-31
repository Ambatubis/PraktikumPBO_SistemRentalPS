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
import model.PaketRental;

/**
 *
 * @author Asus
 */
public class PaketRentalController {
    Connection conn;

    public PaketRentalController() {

        conn = Koneksi.getKoneksi();

    }
    
    public DefaultTableModel tampilData() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama Paket");
        model.addColumn("Durasi");
        model.addColumn("Harga");

        try {

            String sql = "SELECT * FROM paket_rental";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){

                model.addRow(new Object[]{

                    rs.getInt("id_paket"),
                    rs.getString("nama_paket"),
                    rs.getInt("durasi_jam"),
                    rs.getInt("harga_paket")

                });

            }

        } catch(Exception e){

            System.out.println(e);

        }

        return model;

    }
    
    public boolean tambahData(PaketRental paket){

        try{

            String sql =
                    "INSERT INTO paket_rental "
                    + "(nama_paket, durasi_jam, harga_paket)"
                    + " VALUES(?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1,paket.getNamaPaket());
            pst.setInt(2,paket.getDurasiJam());
            pst.setInt(3,paket.getHargaPaket());
            pst.executeUpdate();

            return true;

        }catch(Exception e){

            System.out.println("Error tambah data : " + e);

            return false;

        }

    }
    
    public boolean updateData(PaketRental paket){

        try{

            String sql =
            "UPDATE paket_rental SET "
            + "nama_paket=?, "
            + "durasi_jam=?, "
            + "harga_paket=? "
            + "WHERE id_paket=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, paket.getNamaPaket());
            pst.setInt(2, paket.getDurasiJam());
            pst.setInt(3, paket.getHargaPaket());
            pst.setInt(4, paket.getIdPaket());

            pst.executeUpdate();

            return true;

        }catch(Exception e){

            System.out.println(e);

            return false;

        }

    }
    
    public boolean hapusData(int idPaket){

        try{

            String sql =
                    "DELETE FROM paket_rental "
                    + "WHERE id_paket=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1,idPaket);

            pst.executeUpdate();

            return true;

        }catch(Exception e){

            System.out.println(e);

            return false;

        }

    }
}