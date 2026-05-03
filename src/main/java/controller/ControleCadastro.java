/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Conexao;
import dao.UsuarioDAO;
import view.Cadastro;
import model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControleCadastro {
    private Cadastro telaCadastro;
    
    public ControleCadastro(Cadastro telaCadastro){
        this.telaCadastro = telaCadastro;
    }
    
    public void salvarUsuario(){
        try {
        String nome = telaCadastro.getTxtNome().getText();
        String usuario = telaCadastro.getTxtUsuario().getText();
        int senha = Integer.parseInt(telaCadastro.getTxtSenha().getText());
        
        Usuario usuarioSistema = new Usuario(nome, usuario, senha);
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        UsuarioDAO dao = new UsuarioDAO(conn);
        dao.inserir(usuarioSistema);
        JOptionPane.showMessageDialog(telaCadastro, "Usuario Cadastrado!","Aviso", 
                                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(telaCadastro, "Usuário não cadastrado!" + ex,"Erro", 
                                        JOptionPane.ERROR_MESSAGE);
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(telaCadastro, "A senha precisa ser um número!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
