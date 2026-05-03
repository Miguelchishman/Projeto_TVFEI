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
import view.Logado;
import view.ResultadoPesquisa;
public class ControleLogado {
    private Logado telaLogado;
    private Usuario usuario;
    
    public ControleLogado(Logado telaLogado, Usuario usuario){
        this.telaLogado = telaLogado;
        this.usuario = usuario;
    }
    
    public void abrirPesquisa(String pesquisa){
        ResultadoPesquisa telaResultados = new ResultadoPesquisa(pesquisa);
        telaResultados.setVisible(true);
    }
    
    
}
