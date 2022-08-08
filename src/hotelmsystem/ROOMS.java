
package hotelmsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abdullahi Rilwan
 */
public class ROOMS {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    //create a function to display all rooms type in jTable
     public void fillRooms_TYPE_JTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM type";
        
        try {
            
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            
            Object[] row;
            while(rs.next())
            {
               row = new Object[3];
               row[0] = rs.getInt(1);
               row[1] = rs.getString(2);
               row[2] = rs.getString(3);
               
               
               tableModel.addRow(row);
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
     
     
     
      //create a function to display all rooms in jTable
     public void fillRoomsJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM rooms";
        
        try {
            
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            
            Object[] row;
            while(rs.next())
            {
               row = new Object[4];
               row[0] = rs.getInt(1);
               row[1] = rs.getInt(2);
               row[2] = rs.getString(3);
               row[3] = rs.getString(4);
               
               tableModel.addRow(row);
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
     
     
    
    //create a function to fill a combobox with the rooms type id
     public void fillRooms_TYPE_JCombobox(JComboBox combobox)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM type";
        
        try {
            
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
           
            while(rs.next())
            {
                
               combobox.addItem(rs.getInt(1));
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
     
     
    //create a function to add a new room
      public boolean addRoom(int number, int type, String phone)
    {
        PreparedStatement ps;
     
        String addQuery = "INSERT INTO hotel_db.rooms (r_number, types, phone, reserved) VALUES (?, ?, ?, ?) ";
        
        try {
            ps =  my_connection.createConnection().prepareStatement(addQuery);
            
            ps.setInt(1, number);
            ps.setInt(2, type);
            ps.setString(3, phone);
            
            //when we add a new room
            //the reserved column will be set to no
            //the reserved column means i this column is free or not
            ps.setString(4, "No");
            
            
            return ps.executeUpdate() > 0;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
       //crate a function to edit the selected room
      public boolean editRoom(int number, int type, String phone, String isReserved)
    {
        PreparedStatement ps;
     
        String editQuery = "UPDATE rooms SET   types=?, phone=?, reserved=? WHERE r_number=? ";
        
        try {
            ps =  my_connection.createConnection().prepareStatement(editQuery);
            
            
            ps.setInt(1, type);
            ps.setString(2, phone);
            ps.setString(3, isReserved);
            ps.setInt(4, number);
            
         
            return ps.executeUpdate() > 0;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
      
      
     //create a function to remove the selected room
     public boolean removeRoom(int roomNumber)
    {
        PreparedStatement ps;
        String deleteQuery = "DELETE FROM rooms WHERE r_number=?";
        
        try {
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            
            ps.setInt(1, roomNumber);
            return (ps.executeUpdate() > 0);
                    
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
        
    }
     
    
     //create a function to set a room to reserve or not
     public boolean setRoomToReserved(int number, String isReserved)
    {
        PreparedStatement ps;
     
        String editQuery = "UPDATE rooms SET reserved=? WHERE r_number=? ";
        
        try {
            ps =  my_connection.createConnection().prepareStatement(editQuery);
           
            ps.setString(1, isReserved);
            ps.setInt(2, number);
            
         
            return ps.executeUpdate() > 0;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
      
    // create a function to check if a room is already reserved
    
      public String isRoomReserved(int number)
    {
        PreparedStatement ps;
        ResultSet rs;
        String editQuery = "SELECT reserved FROM rooms WHERE r_number=?";
        
        try {
            ps =  my_connection.createConnection().prepareStatement(editQuery);
           
            ps.setInt(1, number);
            
            rs = ps.executeQuery();
         
            if(rs.next())
            {
                return rs.getString(1);
            }else{
                return "";
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }

    }
      
      
      
}
