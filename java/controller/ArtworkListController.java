package controller;

import view.ArtworkList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.chrono.IsoEra;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Artworks;

public class ArtworkListController {

    public ArtworkListController(Users user) {
         try {
            String sql = "select * from Artworks where user_id = ?";
            Connection con = ConnectionManager.getConnection();
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, user.getId());
            ResultSet rs = preparedStmt.executeQuery();
            List<Artworks> artworks = new ArrayList<>();
            while (rs.next()) {
                Artworks artwork = new Artworks();
                artwork.setId(rs.getInt(1));
                artwork.setTitle(rs.getString(2));
                artwork.setDescription(rs.getString(3));
                artwork.setImage_path(rs.getString(4));
                artwork.setUser_id(rs.getInt(5));
                artworks.add(artwork);
            }
            new ArtworkList(artworks, user);
            con.close();
        } catch(SQLException e){
            
        }
    }
    
}
