/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Asus
 */
public class FileHelper {
    public static void cetakStruk(
            int idTransaksi,
            int totalTagihan,
            int jumlahBayar,
            int kembalian,
            String metodeBayar){

        try{

            FileWriter writer = new FileWriter("struk.txt", true);

            writer.write("=================================\n");
            writer.write("      STRUK RENTAL PS\n");
            writer.write("=================================\n");
            writer.write("ID Transaksi : " + idTransaksi + "\n");
            writer.write("Metode Bayar : " + metodeBayar + "\n");
            writer.write("Total Tagihan : Rp" + totalTagihan + "\n");
            writer.write("Jumlah Bayar : Rp" + jumlahBayar + "\n");
            writer.write("Kembalian : Rp" + kembalian + "\n");
            writer.write("=================================\n\n");

            writer.close();

        }catch(IOException e){

            e.printStackTrace();

        }

    }
}
