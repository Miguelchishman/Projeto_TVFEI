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
import model.Video;
import model.Filme;
import model.Serie;

public class ControleResultados {
    private ResultadoPesquisa telaResultados; 
    
    public ControleResultados(ResultadoPesquisa telaResultados){
        this.telaResultados = telaResultados;
    }
    
    public DefaultListModel pesquisarNomes(String pesquisa){
        Conexao conexao = new Conexao();
        DefaultListModel<Video> model = new DefaultListModel<>();
        try {
            Connection conn = conexao.getConnection();
            VideoDAO dao = new VideoDAO(conn);
            
            ResultSet resultados = dao.procurarVideos(pesquisa);
            while (resultados.next()){
                String tipo = resultados.getString("tipo");
                if (tipo.equals("Filme")){
                    model.addElement(new Filme(resultados.getInt("duracao"),
                                               resultados.getString("nome"),
                                               resultados.getString("descricao"),
                                               resultados.getString("autor"),
                                               resultados.getInt("id")
                    ));
                } else if (tipo.equals("Serie")){
                    model.addElement(new Serie(resultados.getInt("numTemporadas"),
                                               resultados.getInt("numEpisodios"),
                                               resultados.getString("situacao"),
                                               resultados.getString("nome"),
                                               resultados.getString("descricao"),
                                               resultados.getString("autor"),
                                               resultados.getInt("id")
                    ));
                }
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
