/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Miguel
 */
public abstract class Video {
    private String nome, descricao, autor, tipo;
    private int id;

    public Video(String nome, String descricao, String autor, String tipo, int id) {
        this.nome = nome;
        this.descricao = descricao;
        this.autor = autor;
        this.tipo = tipo;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getAutor() {
        return autor;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    
    
    
    
    
    
    
    @Override
    public String toString() {
        return nome;
    }
    
    
}
