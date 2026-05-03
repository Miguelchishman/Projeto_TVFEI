/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Miguel
 */
public class Serie extends Video implements Situacao{
    private int numTemporadas, numEpisodios;
    private String situacao;

    public Serie(int numTemporadas, int numEpisodios, String situacao, String nome, String descricao, String autor, int id) {
        super(nome, descricao, autor, "Serie", id);
        this.numTemporadas = numTemporadas;
        this.numEpisodios = numEpisodios;
        this.situacao = situacao;
    }

    public int getNumTemporadas() {
        return numTemporadas;
    }

    public int getNumEpisodios() {
        return numEpisodios;
    }

    
    @Override
    public String getSituacao(){
        return situacao;
    }
    
    @Override 
    public void setSituacao(String situacao){
        this.situacao = situacao;
    }
}
