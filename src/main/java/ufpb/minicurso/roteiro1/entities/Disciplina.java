package ufpb.minicurso.roteiro1.entities;

import org.springframework.data.annotation.Id;


public class Disciplina {
    @Id
    private int id;
    private String nome;        
    private double nota;

    public Disciplina(int id, String nome, double nota){
        this.id = id;
        this.nome = nome;
        this.nota = nota;
    }

    public Disciplina(String nome, double nota){
        this.nome = nome;
        this.nota = nota;
    }


    public int getId(){
        return this.id;
    } 

    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public double getNota(){
        return this.nota;
    }

    public void setNota(double nota){
        this.nota = nota;
    }
}
