/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.RecursoDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.model.Recurso;
import br.edu.ifsul.model.Pessoa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Antonio
 */
@Named(value = "controleRecurso")
@ViewScoped
public class ControleRecurso implements Serializable {

    @EJB
    private RecursoDAO<Recurso> dao;
    private Recurso objeto;
    

    public ControleRecurso() {

    }

    public String listar() {
        return "/privado/recurso/listar?faces-redirect=true";
    }

    public void novo() {

        objeto = new Recurso();

    }
    
     public void alterar(Object id) {
        try{
         objeto = dao.localizar(id);   
        }catch(Exception e){
            Util.mesagemInformacao("Erro ao recuperar objeto: "+Util.getMenssagemErro(e));
        }
    
    }
       public void excluir(Object id) {
        try{
         objeto = dao.localizar(id);
         dao.remove(objeto);
         Util.mesagemInformacao("Objeto removido com sucesso");
        }catch(Exception e){
            Util.mesagemInformacao("Erro ao remover objeto: "+Util.getMenssagemErro(e));
        }
    
    }
       public void salvar() {
        try{
        if(objeto.getId() == null){
            dao.persist(objeto);
        }else{
            dao.merge(objeto);
        }
         Util.mesagemInformacao("Objeto persistido com sucesso!");
        }catch(Exception e){
            Util.mesagemInformacao("Erro ao salvar objeto: "+Util.getMenssagemErro(e));
        }

    }
       
     
    

    /**
     * @return the dao
     */
    public RecursoDAO<Recurso> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(RecursoDAO<Recurso> dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Recurso getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Recurso objeto) {
        this.objeto = objeto;
    }

   

}
