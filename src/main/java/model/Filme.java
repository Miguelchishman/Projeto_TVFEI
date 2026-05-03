/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Miguel
 */
public class Filme extends Video{
    private int duracao;

    public Filme(int duracao, String nome, String descricao, String autor, int id) {
        super(nome, descricao, autor, "Filme", id);
        this.duracao = duracao;
    }

    public int getDuracao() {
        return duracao;
    }
    
}
