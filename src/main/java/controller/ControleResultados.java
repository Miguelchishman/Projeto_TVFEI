/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Conexao;
import dao.UsuarioDAO;
import dao.VideoDAO;
import view.Login;
import model.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import view.Logado;
import view.ResultadoPesquisa;

public class ControleResultados {
    private ResultadoPesquisa telaResultados; 
    
    public ControleResultados(ResultadoPesquisa telaResultados){
        this.telaResultados = telaResultados;
    }
    
    public DefaultListModel pesquisarNomes(String pesquisa){
        Conexao conexao = new Conexao();
        DefaultListModel<String> model = new DefaultListModel<>();
        try {
            Connection conn = conexao.getConnection();
            VideoDAO dao = new VideoDAO(conn);
            
            ResultSet resultados = dao.procurarVideos(pesquisa);
            while (resultados.next()){
                model.addElement(resultados.getString("nome"));
            }
            
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(telaResultados, "Erro de conexão" + ex, "Erro", 
                                                JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(model);
        return model;
    }
}
