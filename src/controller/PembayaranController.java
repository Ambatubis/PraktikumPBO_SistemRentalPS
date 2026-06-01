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
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import model.Pembayaran;
/**
 *
 * @author Asus
 */
public class PembayaranController {
    Connection conn;

    public PembayaranController() {

        conn = Koneksi.getKoneksi();

    }
    
    public void loadTransaksi(
            JComboBox<String> cbTransaksi){

        cbTransaksi.removeAllItems();

        try{

            String sql = "SELECT id_transaksi FROM transaksi";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                cbTransaksi.addItem(rs.getString("id_transaksi"));
            }

        }catch(Exception e){

            System.out.println(e);

        }

    }
    
    public int getTotalTagihan(
            int idTransaksi){

        try{

            String sql =
            "SELECT total_bayar FROM transaksi WHERE id_transaksi=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, idTransaksi);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                return rs.getInt("total_bayar");
            }

        }catch(Exception e){

            System.out.println(e);

        }

        return 0;

    }
    
    public boolean simpanPembayaran(
            Pembayaran pembayaran){

        try{

            String sql =
            "INSERT INTO pembayaran("
            + "id_transaksi,"
            + "metode_bayar,"
            + "jumlah_bayar,"
            + "kembalian)"
            + " VALUES(?,?,?,?)";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, pembayaran.getIdTransaksi());
            pst.setString(2, pembayaran.getMetodeBayar());
            pst.setInt(3, pembayaran.getJumlahBayar());
            pst.setInt(4, pembayaran.getKembalian());

            pst.executeUpdate();

            return true;

        }catch(Exception e){

            e.printStackTrace();

            return false;

        }

    }
    
    public DefaultTableModel tampilData(){

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("ID Transaksi");
        model.addColumn("Metode");
        model.addColumn("Bayar");
        model.addColumn("Kembalian");
        model.addColumn("Tanggal");

        try{

            String sql = "SELECT * FROM pembayaran";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){

                model.addRow(new Object[]{

                    rs.getInt("id_pembayaran"),

                    rs.getInt("id_transaksi"),

                    rs.getString("metode_bayar"),

                    rs.getInt("jumlah_bayar"),

                    rs.getInt("kembalian"),

                    rs.getString("tanggal_bayar")

                });

            }

        }catch(Exception e){

            System.out.println(e);

        }

        return model;

    }
}
