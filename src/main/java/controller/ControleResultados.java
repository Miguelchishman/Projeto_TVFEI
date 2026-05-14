/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Conexao;
import dao.UsuarioDAO;
import dao.VideoDAO;
import java.awt.Color;
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
    private Usuario usuario;
    
    public ControleResultados(ResultadoPesquisa telaResultados, Usuario usuario){
        this.telaResultados = telaResultados;
        this.usuario = usuario;
    }
    
    
  
    
    public void mostrarResultados(Usuario usuario){
        Video selecionado = telaResultados.getjListResultados().getSelectedValue();
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            VideoDAO dao = new VideoDAO(conn);
            if (selecionado != null){
                telaResultados.getTxtAutor().setText(selecionado.getAutor());
                telaResultados.getTxtDescricao().setText(selecionado.getDescricao());
                telaResultados.getTxtNome().setText(selecionado.getNome());
                telaResultados.getTxtTipo().setText(selecionado.getTipo());
                
                telaResultados.getLblNome().setVisible(true);
                telaResultados.getLblDescricao().setVisible(true);
                telaResultados.getLblAutor().setVisible(true);
                telaResultados.getLblTipo().setVisible(true);
                telaResultados.getTxtNome().setVisible(true);
                telaResultados.getTxtDescricao().setVisible(true);
                telaResultados.getTxtAutor().setVisible(true);
                telaResultados.getTxtTipo().setVisible(true);
                telaResultados.getBtnCurtir().setVisible(true);
                telaResultados.getBtnFavoritar().setVisible(true);


                if (dao.estaCurtido(selecionado.getId(), usuario.getUsuario())){
                   
                    telaResultados.getBtnCurtir().setBackground(new Color(0, 142, 185));
                    telaResultados.getBtnCurtir().setText("Descurtir");
                } else {
                    telaResultados.getBtnCurtir().setBackground(new Color(207, 205, 200));
                    telaResultados.getBtnCurtir().setText("Curtir");
                }
                
                if (dao.estaFavoritado(selecionado.getId(), usuario.getUsuario())){
                   
                    telaResultados.getBtnFavoritar().setText("Desfavoritar");
                } else {
                    telaResultados.getBtnFavoritar().setText("Favoritar");
                }
                
                
                

                if (selecionado instanceof Filme filme){
                    telaResultados.getTxtDuracao().setText(String.valueOf(filme.getDuracao() ) );
                    telaResultados.getLblDuracao().setVisible(true);
                    telaResultados.getTxtDuracao().setVisible(true);

                    telaResultados.getLblTemporadas().setVisible(false);
                    telaResultados.getTxtTemporadas().setVisible(false);               
                    telaResultados.getLblEpisodios().setVisible(false);
                    telaResultados.getTxtEpisodios().setVisible(false);      
                    telaResultados.getLblSituacao().setVisible(false);
                    telaResultados.getTxtSituacao().setVisible(false);


                } else if (selecionado instanceof Serie serie){

                    telaResultados.getTxtTemporadas().setText(String.valueOf(serie.getNumTemporadas()));
                    telaResultados.getTxtEpisodios().setText(String.valueOf(serie.getNumEpisodios()));
                    telaResultados.getTxtSituacao().setText(serie.getSituacao());

                    telaResultados.getLblTemporadas().setVisible(true);
                    telaResultados.getTxtTemporadas().setVisible(true);

                    telaResultados.getLblEpisodios().setVisible(true);
                    telaResultados.getTxtEpisodios().setVisible(true);

                    telaResultados.getLblSituacao().setVisible(true);
                    telaResultados.getTxtSituacao().setVisible(true);

                    telaResultados.getLblDuracao().setVisible(false);
                    telaResultados.getTxtDuracao().setVisible(false);
                }
        } 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(telaResultados, "Erro de conexão" + e, "Erro", 
                                                JOptionPane.ERROR_MESSAGE);
        }
    }
        
    public void ControleLike(Usuario usuario){
        Video selecionado = telaResultados.getjListResultados().getSelectedValue();
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            VideoDAO dao = new VideoDAO(conn);
            if (selecionado != null){
                
                if (dao.estaCurtido(selecionado.getId(), usuario.getUsuario() )){                             
                    dao.descurtirVideo(selecionado.getId(), usuario.getUsuario());
                    telaResultados.getBtnCurtir().setBackground(new Color(207, 205, 200));
                    telaResultados.getBtnCurtir().setText("Curtir");
                } else {
                    dao.curtirVideo(selecionado.getId(), usuario.getUsuario());
                    telaResultados.getBtnCurtir().setBackground(new Color(0, 142, 185));
                    telaResultados.getBtnCurtir().setText("Descurtir");
                }
                
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(telaResultados, "Erro de conexão" + e, "Erro", 
                                                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void ControleFavorito(Usuario usuario){
        Video selecionado = telaResultados.getjListResultados().getSelectedValue();
        DefaultListModel<Video> model = (DefaultListModel<Video>) telaResultados.getjListResultados().getModel();
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            VideoDAO dao = new VideoDAO(conn);
            if (selecionado != null){
                
                if (dao.estaFavoritado(selecionado.getId(), usuario.getUsuario() )){                             
                    dao.desfavoritarVideo(selecionado.getId(), usuario.getUsuario());
                    model.removeElement(selecionado);
                    limparCampos();
                } else {
                    dao.favoritarVideo(selecionado.getId(), usuario.getUsuario());
                    telaResultados.getBtnFavoritar().setText("Desfavoritar");
                }
                
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(telaResultados, "Erro de conexão" + e, "Erro", 
                                                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void limparCampos(){
        telaResultados.getLblNome().setVisible(false);
        telaResultados.getLblDescricao().setVisible(false);
        telaResultados.getLblAutor().setVisible(false);
        telaResultados.getLblTipo().setVisible(false);
        telaResultados.getTxtNome().setVisible(false);
        telaResultados.getTxtDescricao().setVisible(false);
        telaResultados.getTxtAutor().setVisible(false);
        telaResultados.getTxtTipo().setVisible(false);
        telaResultados.getBtnCurtir().setVisible(false);
        telaResultados.getLblDuracao().setVisible(false);
        telaResultados.getLblTemporadas().setVisible(false);
        telaResultados.getLblEpisodios().setVisible(false);
        telaResultados.getLblSituacao().setVisible(false);    
        telaResultados.getTxtDuracao().setVisible(false);
        telaResultados.getTxtTemporadas().setVisible(false);
        telaResultados.getTxtEpisodios().setVisible(false);
        telaResultados.getTxtSituacao().setVisible(false);
        telaResultados.getBtnFavoritar().setVisible(false);
        telaResultados.getLblSemResultados().setVisible(false);
    }
    
}
 
