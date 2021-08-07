/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.model.Condominio;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Antonio
 */
@Named(value = "controleCondominio")
@ViewScoped
public class ControleCondominio implements Serializable {

    @EJB
    private CondominioDAO<Condominio> dao;
    private Condominio objeto;
    

    public ControleCondominio() {

    }

    public String listar() {
        return "/privado/condominio/listar?faces-redirect=true";
    }

    public void novo() {

        objeto = new Condominio();

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
    public CondominioDAO<Condominio> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(CondominioDAO<Condominio> dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Condominio getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Condominio objeto) {
        this.objeto = objeto;
    }

   

}
