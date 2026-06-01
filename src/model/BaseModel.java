/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class BaseModel {
    protected String createdAt;

    public BaseModel() {this.createdAt = "SYSTEM";}

    public String getCreatedAt() {return createdAt;}
    
    public String info() {
        return "Data sistem rental PS";
    }
}
