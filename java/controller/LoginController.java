package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Users;

public class LoginController {

    public static void login(String uname, String pass){
        
        try {
            String sql = "select * from users where email like ?";
            Connection con = ConnectionManager.getConnection();
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, uname);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                sql = "select * from users where email like ? AND password like ?";
                rs = preparedStmt.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Login Berhasil");
                    Users user = new Users();
                    user.setId(rs.getInt(1));
                    user.setName(rs.getString(2));
                    user.setEmail(rs.getString(3));
                    user.setPassword(rs.getString(4));
                    new ArtworkListController(user);
                    con.close();
                } else{
                    JOptionPane.showMessageDialog(null, "Password Salah");
                }
            } else{
                JOptionPane.showMessageDialog(null, "Username Tidak ketemu");
            }
            con.close();
        } catch(SQLException e){
            
        }
    }
}
