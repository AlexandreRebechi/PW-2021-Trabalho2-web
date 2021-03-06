/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.model.Condominio;
import br.edu.ifsul.model.Recurso;

import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Antonio
 */
@Stateful
public class CondominioDAO<TIPO> extends DAOGenerico<Condominio> implements Serializable{

    public CondominioDAO() {
        super();
        classePersistente = Condominio.class;
        //definir as ordens possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        //definir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
    @Override
    public Condominio localizar(Object id) throws Exception {
         Condominio objeto =em.find(Condominio.class, id);
         objeto.getUnidadeCondominais().size();
         return objeto;
    }
    
    
}
