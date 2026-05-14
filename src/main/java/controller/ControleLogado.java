/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Conexao;
import dao.VideoDAO;
import view.Login;
import model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import model.Filme;
import model.Serie;
import model.Video;
import view.Logado;
import view.ResultadoPesquisa;

public class ControleLogado {
    private Logado telaLogado;
    private Usuario usuario;
    
    public ControleLogado(Logado telaLogado, Usuario usuario){
        this.telaLogado = telaLogado;
        this.usuario = usuario;
    }
    
    public void abrirPesquisa(String pesquisa, Usuario usuario){
        ResultadoPesquisa telaResultados = new ResultadoPesquisa(pesquisa, pesquisarNomes(pesquisa), usuario, "Resultado das Pesquisas");
        telaResultados.setVisible(true);
    }
    
    public void abrirFavoritos(Usuario usuario){
        ResultadoPesquisa telaResultados = new ResultadoPesquisa("Sem pesquisa", pesquisarFavoritos(usuario), usuario, "Favoritos");
        telaResultados.setVisible(true);
    }
    
    
    public DefaultListModel<Video> pesquisarNomes(String pesquisa){
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
                    model.addElement(new Serie(resultados.getInt("numtemporadas"),
                                               resultados.getInt("numepisodios"),
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
            JOptionPane.showMessageDialog(telaLogado, "Erro de conexão" + ex, "Erro", 
                                                JOptionPane.ERROR_MESSAGE);
        }
        return model;
    }
    
    public DefaultListModel<Video> pesquisarFavoritos(Usuario usuario){
        Conexao conexao = new Conexao();
        DefaultListModel<Video> model = new DefaultListModel<>();
        try{
            Connection conn = conexao.getConnection();
            VideoDAO dao = new VideoDAO(conn);
            ResultSet resultados = dao.pesquisarFavoritos(usuario);
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
                    model.addElement(new Serie(resultados.getInt("numtemporadas"),
                                               resultados.getInt("numepisodios"),
                                               resultados.getString("situacao"),
                                               resultados.getString("nome"),
                                               resultados.getString("descricao"),
                                               resultados.getString("autor"),
                                               resultados.getInt("id")
                    ));
                }
            }
            
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(telaLogado, "Erro de conexão" + ex, "Erro", 
                                                JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println(model);
        return model;
    }
    
    
}
