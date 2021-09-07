/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;

import java.io.Serializable;
import javax.ejb.Stateful;
import br.edu.ifsul.model.Aluguel;
/**
 *
 * @author Antonio
 */
@Stateful
public class AluguelDAO<TIPO> extends DAOGenerico<Aluguel> implements Serializable {

    public AluguelDAO() {
        super();
        classePersistente = Aluguel.class;
        //definir as ordens possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("valor", "Valor", "="));
        listaOrdem.add(new Ordem("locatario.nome","Locatario nome","like"));
        listaOrdem.add(new Ordem("unidadeCondominial.numero","unidadeCondominial Numero","like"));
        
        

        //definir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    @Override
   public Aluguel localizar(Object id) throws Exception {
         Aluguel objeto = em.find(Aluguel.class, id);
         objeto.getListMensalidades().size();
        return objeto;
    }

}
