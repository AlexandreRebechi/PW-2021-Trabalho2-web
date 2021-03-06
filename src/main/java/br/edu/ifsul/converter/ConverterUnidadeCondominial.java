/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converter;

import br.edu.ifsul.model.UnidadeCondominial;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Antonio
 */
@Named(value = "converterUnidadeCondominial")
@RequestScoped
public class ConverterUnidadeCondominial implements Serializable, Converter{

    @PersistenceContext(unitName = "PW-2021-1-Trabalho2-WebPU")
    protected EntityManager em;
    
    // converter da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        
        if(string == null || string.equals("Selecione um registro")){
              return null;
            
        }
        return em.find(UnidadeCondominial.class, Integer.parseInt(string));
      
    }
    
    // converter do objeto para a tela  
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        
        if(t == null){
            return null;
        }
        UnidadeCondominial obj = (UnidadeCondominial) t;
        return obj.getId().toString();
        
    }

   
    
}
