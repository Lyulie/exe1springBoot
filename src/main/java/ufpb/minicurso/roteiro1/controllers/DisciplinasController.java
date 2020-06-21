package ufpb.minicurso.roteiro1.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.var;
import ufpb.minicurso.roteiro1.entities.Disciplina;
import ufpb.minicurso.roteiro1.services.DisciplinasService;

    // POST "/v1/api/disciplinas" v
    // GET "/v1/api/disciplinas/{id}" v
    // GET /v1/api/disciplinas (id numerico, nome, nota) v
    // PUT /v1/api/disciplinas/{id}/nome v
    // PUT /v1/api/disciplinas/{id}/nota v
    // DELETE /v1/api/disciplinas/{id} v
    // GET /v1/api/disciplinas/ranking v

@RestController
public class DisciplinasController {
    
    @Autowired
    DisciplinasService disciplinasService;

    @PostMapping("/v1/api/disciplinas")
    public ResponseEntity<Disciplina> setNovaDisciplina(
        @RequestBody Disciplina newDisciplina
    ){
        return new ResponseEntity<Disciplina>(
            disciplinasService.setNovaDisciplina(new Disciplina(
                    getNewId(),
                    newDisciplina.getNome(), 
                    newDisciplina.getNota()
                )
            ),
            HttpStatus.CREATED
        );
    }

    @GetMapping("/v1/api/disciplinas")
    public ResponseEntity< List<Disciplina> > getDisciplinas(){
        return new ResponseEntity< List<Disciplina> >(
            disciplinasService.getDisciplinas(),
            HttpStatus.OK
        );
    }

    @GetMapping("/v1/api/disciplinas/{id}")
    public ResponseEntity<Disciplina> getDisciplina(
        @PathVariable("id") int id
    ){
        try{
            return new ResponseEntity<Disciplina>(
                disciplinasService.getDisciplina(id),
                HttpStatus.OK
            );
        } catch (IndexOutOfBoundsException ioobe){
            return new ResponseEntity<Disciplina>(
                // new Disciplina(0, null, 0.0),
                HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/v1/api/disciplinas/{id}/nome")
    public ResponseEntity<Disciplina>setNewNome(
        @PathVariable int id,
        @RequestBody String nome
    ){
        try{
            return new ResponseEntity<Disciplina>(
                disciplinasService.setNewNome(id, nome),
                HttpStatus.OK
            );
        } catch(IndexOutOfBoundsException ioobe){
            return new ResponseEntity<Disciplina>(
                HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/v1/api/disciplinas/{id}/nota")
    public ResponseEntity<Disciplina>setNewNota(
        @PathVariable int id,
        @RequestBody double nota
    ){
        try{
            return new ResponseEntity<Disciplina>(
                disciplinasService.setNewNota(id, nota),
                HttpStatus.OK
            );
        } catch (IndexOutOfBoundsException ioobe){
            return new ResponseEntity<Disciplina>(
                HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping(value = "/v1/api/disciplinas/{id}")
    public ResponseEntity<Integer> deletePost(
        @PathVariable int id
    ){
        var confirmacao = disciplinasService.removeDisciplina(id);

        if (!confirmacao) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/v1/api/disciplinas/ranking")
    public ResponseEntity< List<Disciplina> >listarRanking(){
        return new ResponseEntity< List<Disciplina> >(
            disciplinasService.listarRanking(),
            HttpStatus.OK
        );
    }

    /**
     * Criar um id que ainda não exista entre 1000 e 9999
     * 
     * @return Random Integer
     */
    public int getNewId(){
        Random random = new Random();
        int newId = 0;
        List<Integer> ids = new ArrayList<>();
        for(Disciplina disciplina: disciplinasService.getDisciplinas()){
            ids.add(disciplina.getId());
        }

        do{
            newId = random.nextInt(8999) + 1000;  
        }while(ids.contains(newId));
        return newId;
    }
}