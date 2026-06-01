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
import model.Transaksi;

/**
 *
 * @author Asus
 */
public class TransaksiController {
    Connection conn;

    public TransaksiController() {

        conn = Koneksi.getKoneksi();

    }
    
    public void loadMember(
            JComboBox<String> cbMember){

        cbMember.removeAllItems();

        try{

            String sql = "SELECT nama_member FROM member";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                cbMember.addItem(rs.getString("nama_member"));
            }

        }catch(Exception e){

            System.out.println(e);

        }

    }
    
    public void loadPS(
            JComboBox<String> cbPS){

        cbPS.removeAllItems();

        try{

            String sql =
                    "SELECT nama_ps FROM playstation";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                cbPS.addItem(rs.getString("nama_ps"));
            }

        }catch(Exception e){

            System.out.println(e);

        }

    }
    
    public void loadPaket(
            JComboBox<String> cbPaket){

        cbPaket.removeAllItems();

        try{

            String sql =
                    "SELECT nama_paket FROM paket_rental";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                cbPaket.addItem(rs.getString("nama_paket"));
            }

        }catch(Exception e){

            System.out.println(e);

        }

    }
    
    public int getHargaPaket(
            String namaPaket){

        try{

            String sql =
            "SELECT harga_paket FROM paket_rental WHERE nama_paket=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, namaPaket);
            
            ResultSet rs =pst.executeQuery();

            if(rs.next()){
                return rs.getInt("harga_paket");
            }

        }catch(Exception e){

            System.out.println(e);

        }

        return 0;

    }
    
    public int getIdMember(String namaMember){

        try{

            String sql =
            "SELECT id_member FROM member WHERE nama_member=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, namaMember);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                return rs.getInt("id_member");
            }

        }catch(Exception e){

            System.out.println(e);

        }

        return 0;

    }
    
    public int getIdPS(String namaPS){

        try{

            String sql =
            "SELECT id_ps FROM playstation WHERE nama_ps=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, namaPS);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                return rs.getInt("id_ps");
            }

        }catch(Exception e){

            System.out.println(e);

        }

        return 0;

    }
    
    public int getIdPaket(String namaPaket){

        try{

            String sql =
            "SELECT id_paket FROM paket_rental WHERE nama_paket=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, namaPaket);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                return rs.getInt("id_paket");
            }

        }catch(Exception e){

            System.out.println(e);

        }

        return 0;

    }
    
    public boolean simpanTransaksi(Transaksi transaksi){

        try{

            String sql =
            "INSERT INTO transaksi"
            + "(tanggal, id_member, id_ps, id_paket, id_user, total_bayar, status_transaksi)"
            + " VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1,transaksi.getTanggal());
            pst.setInt(2,transaksi.getIdMember());
            pst.setInt(3,transaksi.getIdPs());
            pst.setInt(4,transaksi.getIdPaket());
            pst.setInt(5,transaksi.getIdUser());
            pst.setInt(6,transaksi.getTotalBayar());
            pst.setString(7,transaksi.getStatusTransaksi());
            
            pst.executeUpdate();

            return true;

        }catch(Exception e){

            System.out.println(e);

            return false;

        }

    }
    
    public DefaultTableModel tampilData(){

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Member");
        model.addColumn("PS");
        model.addColumn("Paket");
        model.addColumn("Tanggal");
        model.addColumn("Total");
        model.addColumn("Status");

        try{

            String sql =
            "SELECT t.id_transaksi, "
            + "m.nama_member, "
            + "p.nama_ps, "
            + "pk.nama_paket, "
            + "t.tanggal, "
            + "t.total_bayar, "
            + "t.status_transaksi "
            + "FROM transaksi t "
            + "JOIN member m "
            + "ON t.id_member = m.id_member "
            + "JOIN playstation p "
            + "ON t.id_ps = p.id_ps "
            + "JOIN paket_rental pk "
            + "ON t.id_paket = pk.id_paket";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){

                model.addRow(new Object[]{

                    rs.getInt("id_transaksi"),

                    rs.getString("nama_member"),

                    rs.getString("nama_ps"),

                    rs.getString("nama_paket"),

                    rs.getString("tanggal"),

                    rs.getInt("total_bayar"),

                    rs.getString("status_transaksi")

                });

            }

        }catch(Exception e){

            System.out.println(e);

        }

        return model;

    }
    
    public int getDurasiPaket(String namaPaket){

        try{

            String sql =
            "SELECT durasi_jam FROM paket_rental WHERE nama_paket=?";

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            pst.setString(1, namaPaket);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                return rs.getInt("durasi_jam");
            }

        }catch(Exception e){
            System.out.println(e);
        }

        return 0;
    }
}
