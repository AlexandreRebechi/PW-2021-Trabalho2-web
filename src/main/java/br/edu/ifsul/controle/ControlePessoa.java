/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaDAO;
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
@Named(value = "controlePessoa")
@ViewScoped
public class ControlePessoa implements Serializable {

    @EJB
    private PessoaDAO<Pessoa> dao;
    private Pessoa objeto;

    public ControlePessoa() {

    }

    public String listar() {
        return "/privado/pessoa/listar?faces-redirect=true";
    }

    public void novo() {

        objeto = new Pessoa();

    }
    
     public void alterar(Object id) {
        try{
         objeto = dao.localizar(id);   
        }catch(Exception e){
            Util.mesagemInformacao("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
        }
    
    }
       public void excluir(Object id) {
        try{
         objeto = dao.localizar(id);
         dao.remove(objeto);
         Util.mesagemInformacao("Objeto removido com sucesso");
        }catch(Exception e){
            Util.mesagemInformacao("Erro ao remover objeto: "+Util.getMensagemErro(e));
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
            Util.mesagemInformacao("Erro ao salvar objeto: "+Util.getMensagemErro(e));
        }

    }
       
     
    

    /**
     * @return the dao
     */
    public PessoaDAO<Pessoa> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(PessoaDAO<Pessoa> dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Pessoa getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Pessoa objeto) {
        this.objeto = objeto;
    }

}
