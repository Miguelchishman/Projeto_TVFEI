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
//        String sql = "SELECT nome FROM public.tbvideos where nome LIKE '%" + pesquisa + "%'";
        String sql = "SELECT v.*, f.duracao, s.numtemporadas, s.numepisodios, s.situacao\n" +
                    "FROM public.tbvideos v\n" +
                    "LEFT JOIN public.tbfilmes f ON f.id = v.id\n" +
                    "LEFT JOIN public.tbseries s ON s.id = v.id\n" +
                    "WHERE v.nome LIKE '%" + pesquisa + "%'";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
   
    public ResultSet pesquisarFavoritos(Usuario usuario) throws SQLException{
        String sql = "SELECT v.*, f.duracao, s.numTemporadas, s.numEpisodios, s.situacao " +
                 "FROM tbfavoritos fav " +
                 "JOIN tbvideos v ON v.id = fav.id " +
                 "LEFT JOIN tbfilmes f ON f.id = v.id " +
                 "LEFT JOIN tbseries s ON s.id = v.id " +
                 "WHERE fav.usuario = '" + usuario.getUsuario() + "'";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    
    
    
    public boolean estaCurtido(int video_id, String usuario) throws SQLException{
        String sql = "SELECT * FROM tbcurtidas WHERE id = '" + video_id +
                "' AND usuario = '" + usuario + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
        ResultSet resultado = ps.getResultSet();
        return resultado.next();
    }
    
    public void curtirVideo(int video_id, String usuario) throws SQLException{
        String sql = "INSERT INTO tbcurtidas (id, usuario) VALUES (" + video_id + ", '" + usuario + "')";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
    }
    
    public void descurtirVideo(int video_id, String usuario) throws SQLException{
        String sql = "DELETE FROM tbcurtidas WHERE id = " + video_id + " AND usuario = '" + usuario + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
    }
    
    
    
    // FAVORITOS!!
    
    public boolean estaFavoritado(int video_id, String usuario) throws SQLException{
        String sql = "SELECT * FROM tbfavoritos WHERE id = '" + video_id +
                "' AND usuario = '" + usuario + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
        ResultSet resultado = ps.getResultSet();
        return resultado.next();
    }
    
    public void favoritarVideo(int video_id, String usuario) throws SQLException{
        String sql = "INSERT INTO tbfavoritos (id, usuario) VALUES (" + video_id + ", '" + usuario + "')";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
    }
    
    public void desfavoritarVideo(int video_id, String usuario) throws SQLException{
        String sql = "DELETE FROM tbfavoritos WHERE id = " + video_id + " AND usuario = '" + usuario + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
    }
    
    
    
    
    
    
    
    
    
}
