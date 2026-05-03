/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.Usuario;
import model.Video;

public class VideoDAO {
    private Connection conn;
    
    public VideoDAO(Connection conn){
        this.conn = conn;
    }
    
    public ResultSet procurarVideos(String pesquisa) throws SQLException{
        String sql = "SELECT nome FROM public.tbvideos where nome LIKE '%" + pesquisa + "%'";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        conn.close();
        return resultado;
    }
}
