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

public class UsuarioDAO {
    private Connection conn;
    
    public UsuarioDAO(Connection conn){
        this.conn = conn;
    }
    
    public void inserir(Usuario usuario) throws SQLException{
        String sql = "insert into tbusuarios (nome, usuario, senha) values ('"
                      + usuario.getNome()    + "', '" 
                      + usuario.getUsuario() + "', '"
                      + usuario.getSenha()   + "')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
    }
    
    public ResultSet procurar(Usuario usuario) throws SQLException {
        String sql = "select * from tbusuarios where usuario = '" + usuario.getUsuario() + "' and senha = '" + usuario.getSenha() + "'";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    
    
    
}
