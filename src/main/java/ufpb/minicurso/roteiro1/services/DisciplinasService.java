package ufpb.minicurso.roteiro1.services;

import ufpb.minicurso.roteiro1.entities.Disciplina;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class DisciplinasService {
    List<Disciplina> disciplinas = new ArrayList<>();

    public Disciplina setNovaDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
        return disciplinas.get(disciplinas.size() -1);
    }

    public List<Disciplina> getDisciplinas(){
        return this.disciplinas;
    }

    public Disciplina getDisciplina(int id){
        for(Disciplina disciplina: this.disciplinas){
            if(disciplina.getId() == id)
                return disciplina;
        }
        throw new ArrayIndexOutOfBoundsException("Id não encontrado.");
    }

    public boolean removeDisciplina(int id){
        for (Disciplina disciplina: this.disciplinas){
            if(disciplina.getId() == id){
                this.disciplinas.remove(disciplina);
                return true;
            }
        }
        return false;
    }

    public Disciplina setNewNome(int id, String nome){
        for (Disciplina disciplina: this.disciplinas){
            if(disciplina.getId() == id){
                disciplina.setNome(nome);
                return disciplina;
            }
        }
        throw new ArrayIndexOutOfBoundsException("Id não encontrado.");
    }

	public Disciplina setNewNota(int id, double nota) {
		for(Disciplina disciplina : this.disciplinas){
            if(disciplina.getId() == id)
                disciplina.setNota(nota);
                return disciplina;
        }
        throw new ArrayIndexOutOfBoundsException("Id não encontrado.");
    }
    
    public List<Disciplina> listarRanking(){
        List<Disciplina> ranking = this.disciplinas.stream()
            .sorted(Comparator.comparingDouble(Disciplina::getNota)
                .reversed())
            .collect(Collectors.toList());
        
        return ranking; 
    }
}