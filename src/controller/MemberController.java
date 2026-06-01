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
import model.Member;

/**
 *
 * @author Asus
 */
public class MemberController {
    Connection conn;

    public MemberController() {

        conn = Koneksi.getKoneksi();

    }
    
    public DefaultTableModel tampilData(){

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("No HP");
        model.addColumn("Alamat");
        model.addColumn("Status");

        try{

            String sql = "SELECT * FROM member";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){

                model.addRow(new Object[]{

                    rs.getInt("id_member"),
                    rs.getString("nama_member"),
                    rs.getString("no_hp"),
                    rs.getString("alamat"),
                    rs.getString("status_member")

                });

            }

        }catch(Exception e){

            System.out.println(e);

        }

        return model;

    }
    
    public boolean tambahData(Member member){

        try{

            String sql =
                    "INSERT INTO member "
                    + "(nama_member, no_hp, alamat, status_member)"
                    + " VALUES(?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, member.getNamaMember());
            pst.setString(2, member.getNoHp());
            pst.setString(3, member.getAlamat());
            pst.setString(4, member.getStatusMember());

            pst.executeUpdate();

            return true;

        }catch(Exception e){

            System.out.println("Error tambah data : " + e);

            return false;

        }

    }
    
    public boolean updateData(Member member){

        try{

            String sql =
            "UPDATE member SET "
            + "nama_member=?, "
            + "no_hp=?, "
            + "alamat=?, "
            + "status_member=? "
            + "WHERE id_member=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, member.getNamaMember());
            pst.setString(2, member.getNoHp());
            pst.setString(3, member.getAlamat());
            pst.setString(4, member.getStatusMember());
            pst.setInt(5,member.getIdMember());

            pst.executeUpdate();

            return true;

        }catch(Exception e){

            System.out.println(e);

            return false;

        }

    }
    
    public boolean hapusData(int idMember){

        try{

            String sql =
                    "DELETE FROM member WHERE id_member=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1,idMember);

            pst.executeUpdate();

            return true;

        }catch(Exception e){

            System.out.println(e);

            return false;

        }

    }
}