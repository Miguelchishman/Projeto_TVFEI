/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Conexao;
import dao.UsuarioDAO;
import view.Login;
import model.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.Logado;

public class ControleLogin {
    private Login telaLogin;
    
    public ControleLogin(Login telaLogin){
        this.telaLogin = telaLogin;
    }
    
    public void loginUsuario(){
        String usuario = telaLogin.getTxtUsuario().getText();
        int senha = Integer.parseInt(telaLogin.getTxtSenha().getText());
        
        Usuario usuarioSistema = new Usuario(null, usuario, senha);
        
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            UsuarioDAO dao = new UsuarioDAO(conn);
            ResultSet res = dao.procurar(usuarioSistema);
            if(res.next()){
                JOptionPane.showMessageDialog(telaLogin, "Login efetuado", "Aviso", 
                                                JOptionPane.INFORMATION_MESSAGE);
                String nomeConsulta = res.getString("nome");
                String usuarioConsulta = res.getString("usuario");
                int senhaConsulta = res.getInt("senha");
                Logado telaLogado = new Logado(new Usuario(nomeConsulta, usuarioConsulta, senhaConsulta));
                telaLogado.setVisible(true);
                telaLogin.dispose();
            } else{
                JOptionPane.showMessageDialog(telaLogin, "Login não efetuado", "Erro", 
                                                JOptionPane.ERROR_MESSAGE);
                
            }
            
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(telaLogin, "Erro de conexão", "Erro", 
                                                JOptionPane.ERROR_MESSAGE);
        }
    }
}
