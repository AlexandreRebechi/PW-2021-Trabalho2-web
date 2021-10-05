/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.model.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Antonio
 */
@Named(value = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {

    private Usuario usuarioAutenticado;
    @EJB
    private UsuarioDAO<Usuario> dao;
    private String usuario;
    private String senha;

    public ControleLogin() {
    }

    public String paginaLogin() {
        return "/login?faces-redirect=true";
    }

    public String efetuarLogin() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.
                    getCurrentInstance().getExternalContext().getRequest();
            
          
            request.login(this.usuario, this.senha);
            if (request.getUserPrincipal() != null) {
              
                
                usuarioAutenticado
                        = dao.localizar(request.getUserPrincipal().getName());
                Util.mesagemInformacao("Login realizado com sucesso!");
                usuario = "";
                senha = "";
            }
            
            
            
            
            return "/index";
        } catch (Exception e) {
            Util.mesagemErro("Usuário ou senha inválidos!!! "
                    + Util.getMensagemErro(e));
            return "/login";
        }
    }

    public String efetuarLogout() {
        try {
            usuarioAutenticado = null;
            HttpServletRequest request = (HttpServletRequest) FacesContext.
                    getCurrentInstance().getExternalContext().getRequest();
            request.logout();
            return "/index?faces-redirect=true";
        } catch (ServletException e) {
            Util.mesagemErro("Erro: " + Util.getMensagemErro(e));
            return "/index";
        }
    }

    /**
     * @return the usuarioAutenticado
     */
    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    /**
     * @param usuarioAutenticado the usuarioAutenticado to set
     */
    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    /**
     * @return the dao
     */
    public UsuarioDAO<Usuario> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(UsuarioDAO<Usuario> dao) {
        this.dao = dao;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

}
