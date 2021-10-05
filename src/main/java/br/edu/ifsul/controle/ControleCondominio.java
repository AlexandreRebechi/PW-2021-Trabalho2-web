/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.model.Condominio;
import br.edu.ifsul.model.Pessoa;
import br.edu.ifsul.model.UnidadeCondominial;
import br.edu.ifsul.model.UnidadeCondominial;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
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
    
    @EJB
    private PessoaDAO<Pessoa> daoPessoa;
    private Condominio objeto;
    private UnidadeCondominial unidadeCondominial;
    private Boolean novaUnidadeCondominial;

    public ControleCondominio() {

    }
 public void imprimeCondominio(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatoriosCondominio", parametros, dao.getListaTodos());
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
    
       public void novaUnidadeCondominial(){
        unidadeCondominial = new UnidadeCondominial();
        novaUnidadeCondominial = true;
    }
     public void alterarUnidadeCondominial(int index){
        unidadeCondominial = objeto.getUnidadeCondominais().get(index);
        novaUnidadeCondominial = false;
    }
     
      public void removerUnidadeCondominial(int index){
       objeto.removerUnidadeCondominial(index);
         Util.mesagemInformacao("Unidade condominial removida com sucesso!");
       
    }
     
     public void salvarUnidadeCondominial(){
        if(novaUnidadeCondominial){
            objeto.adicionarUnidadeCondominial(unidadeCondominial);
        }
        Util.mesagemInformacao("Unidade condominial adicionada ou alterada com sucesso!");
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

    /**
     * @return the unidadeCondominial
     */
    public UnidadeCondominial getUnidadeCondominial() {
        return unidadeCondominial;
    }

    /**
     * @param unidadeCondominial the unidadeCondominial to set
     */
    public void setUnidadeCondominial(UnidadeCondominial unidadeCondominial) {
        this.unidadeCondominial = unidadeCondominial;
    }

    /**
     * @return the novaUnidadeCondominial
     */
    public Boolean getNovaUnidadeCondominial() {
        return novaUnidadeCondominial;
    }

    /**
     * @param novaUnidadeCondominial the novaUnidadeCondominial to set
     */
    public void setNovaUnidadeCondominial(Boolean novaUnidadeCondominial) {
        this.novaUnidadeCondominial = novaUnidadeCondominial;
    }

    /**
     * @return the daoPessoa
     */
    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    /**
     * @param daoPessoa the daoPessoa to set
     */
    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

   

}
