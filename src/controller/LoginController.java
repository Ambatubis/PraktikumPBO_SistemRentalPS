/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;
/**
 *
 * @author Asus
 */
public class LoginController {
    
    Connection conn;

    public LoginController() {

        conn = Koneksi.getKoneksi();

    }

    public User login(String username, String password) {

        User user = null;

        try {

            String sql = "SELECT * FROM user WHERE username=? AND password=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user = new User();

                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setNama(rs.getString("nama"));
                user.setRole(rs.getString("role"));

            }

        } catch (Exception e) {

            System.out.println("Error Login : " + e);

        }

        return user;
    }
}
