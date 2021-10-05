/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AluguelDAO;
import br.edu.ifsul.dao.LocatarioDAO;
import br.edu.ifsul.dao.UnidadeCondominalDAO;
import br.edu.ifsul.model.Aluguel;
import br.edu.ifsul.model.Locatario;
import br.edu.ifsul.model.Mensalidades;
import br.edu.ifsul.model.UnidadeCondominial;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Antonio
 */
@Named(value = "controleAluguel")
@ViewScoped
public class ControleAluguel implements Serializable {

    @EJB
    private AluguelDAO<Aluguel> dao;
    private Aluguel objeto;
    
    @EJB
    private UnidadeCondominalDAO<UnidadeCondominial> daoUnidadeCondominal;
    
    @EJB
    private LocatarioDAO<Locatario> daoLocatario;
    
    
    private Mensalidades mensalidades;
    private Boolean novaMensalidade;

    public ControleAluguel() {

    }
    
    public void imprimeAluguel(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatoriosAluguel", parametros, dao.getListaCompleta());
    }
    
    public void imprimeAluguel(Object id) {
        try {
            objeto = dao.localizar(id);
            List<Aluguel> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatoriosAluguel", parametros, lista);
        } catch (Exception e) {
            Util.mesagemErro("Erro ao imprimir relatório: " + Util.getMensagemErro(e));
        }
    }
  
    
    public void novaMesalidade(){
        mensalidades = new Mensalidades();
        novaMensalidade = true;
    }
     public void alterarMesalidade(int index){
        mensalidades = objeto.getListMensalidades().get(index);
        novaMensalidade = false;
    }
     
      public void removerMesalidade(int index){
       objeto.removerMensalidades(index);
         Util.mesagemInformacao("Mensalidade removida com sucesso!");
       
    }
     
     public void salvarMesalidade(){
        if(novaMensalidade){
            objeto.adicionarMensalidades(mensalidades);
        }
        Util.mesagemInformacao("Mensalidade adicionada ou alterada com sucesso!");
    }

    public String listar() {
        return "/privado/aluguel/listar?faces-redirect=true";
    }

    public void novo() {

        objeto = new Aluguel();

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
    public AluguelDAO<Aluguel> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(AluguelDAO<Aluguel> dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Aluguel getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Aluguel objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoUnidadeCondominal
     */
    public UnidadeCondominalDAO<UnidadeCondominial> getDaoUnidadeCondominal() {
        return daoUnidadeCondominal;
    }

    /**
     * @param daoUnidadeCondominal the daoUnidadeCondominal to set
     */
    public void setDaoUnidadeCondominal(UnidadeCondominalDAO<UnidadeCondominial> daoUnidadeCondominal) {
        this.daoUnidadeCondominal = daoUnidadeCondominal;
    }

    /**
     * @return the daoLocatario
     */
    public LocatarioDAO<Locatario> getDaoLocatario() {
        return daoLocatario;
    }

    /**
     * @param daoLocatario the daoLocatario to set
     */
    public void setDaoLocatario(LocatarioDAO<Locatario> daoLocatario) {
        this.daoLocatario = daoLocatario;
    }

    /**
     * @return the mensalidades
     */
    public Mensalidades getMensalidades() {
        return mensalidades;
    }

    /**
     * @param mensalidades the mensalidades to set
     */
    public void setMensalidades(Mensalidades mensalidades) {
        this.mensalidades = mensalidades;
    }

    /**
     * @return the novaMensalidade
     */
    public Boolean getNovaMensalidade() {
        return novaMensalidade;
    }

    /**
     * @param novaMensalidade the novaMensalidade to set
     */
    public void setNovaMensalidade(Boolean novaMensalidade) {
        this.novaMensalidade = novaMensalidade;
    }

}
