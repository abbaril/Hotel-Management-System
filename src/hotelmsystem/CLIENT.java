/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP 14-AL
 */
public class CLIENT { //the client class
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    
    //create a function to add a client
    public boolean addClient(String fname, String lname, String phone, String email)
    {
        PreparedStatement ps;
     
        String addQuery = "INSERT INTO hotel_db.clients (first_name, last_name, phone, email) VALUES (?, ?, ?, ?) ";
        
        try {
            ps =  my_connection.createConnection().prepareStatement(addQuery);
            
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, phone);
            ps.setString(4, email);
            
            return ps.executeUpdate() > 0;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    //crate a function to edit the selected  client
    public boolean editClient(int id, String fname, String lname, String phone, String email)
    {
        PreparedStatement ps;
     
        String editQuery = "UPDATE clients SET   first_name=?, last_name=?, phone=?, email=? WHERE id=? ";
        
        try {
            ps =  my_connection.createConnection().prepareStatement(editQuery);
            
            
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setInt(5, id);
            
            
            
            
            return ps.executeUpdate() > 0;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
        
    }
    
    
    //create a function to remove the selected client
    public boolean removeClient(int id)
    {
        PreparedStatement ps;
        String deleteQuery = "DELETE FROM clients WHERE id=?";
        
        try {
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            
            ps.setInt(1, id);
            return (ps.executeUpdate() > 0);
                    
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
        
        
    }
    
    
    
    
    //create a function to populate the jTbale with all the clients in the databbase
    
    public void fillClientsJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM clients";
        
        try {
            
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            
            Object[] row;
            while(rs.next())
            {
               row = new Object[5];
               row[0] = rs.getInt(1);
               row[1] = rs.getString(2);
               row[2] = rs.getString(3);
               row[3] = rs.getString(4);
               row[4] = rs.getString(5);
               
               tableModel.addRow(row);
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
