/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converter;

import br.edu.ifsul.model.Pessoa;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Antonio
 */
public class ConverterPessoa implements Serializable, Converter{

    @PersistenceContext(unitName = "PW-2021-1-Trabalho2-WebPU")
    protected EntityManager em;
    
    // converter da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        
        if(string == null || string.equals("Selecione um registro")){
              return null;
            
        }
        return em.find(Pessoa.class, Integer.parseInt(string));
      
    }
    
    // converter do objeto para a tela  
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        
        if(t == null){
            return null;
        }
        Pessoa obj = (Pessoa) t;
        return obj.getId().toString();
        
    }

   
    
}