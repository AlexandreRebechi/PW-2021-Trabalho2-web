/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.model.Locatario;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Antonio
 */
@Stateful
public class LocatarioDAO<TIPO> extends DAOGenerico<Locatario> implements Serializable {

    public LocatarioDAO() {
        super();
        classePersistente = Locatario.class;
        //definir as ordens possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        //definir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }

}
