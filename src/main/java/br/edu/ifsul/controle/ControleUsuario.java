package br.edu.ifsul.controle;
import br.edu.ifsul.dao.PermissaoDAO;
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.model.Permissao;
import br.edu.ifsul.model.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorgebavaresco@ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Named(value = "controleUsuario")
@ViewScoped
public class ControleUsuario implements Serializable {

    @EJB
    private UsuarioDAO<Usuario> dao;
    private Usuario objeto;
    private Boolean novo;
    @EJB
    private PermissaoDAO<Permissao> daoPermissao;
    private Permissao permissao;
    private int abaAtiva;

    public ControleUsuario() {

    }

    public void removerPermissao(Permissao obj) {
        objeto.getPermissoes().remove(obj);
        Util.mesagemInformacao("Permissão removida com sucesso!");
    }

    public void adicionarPermissao() {
        if (!objeto.getPermissoes().contains(permissao)) {
            if (permissao != null) {
                objeto.getPermissoes().add(permissao);
                Util.mesagemInformacao("Permissão adicionada com sucesso!");
            } else {
                Util.mesagemErro("Selecione a permissão");
            }
        } else {
            Util.mesagemErro("Usuário já possui esta permissão");
        }
    }

    public void verificarUnicidadeNomeUsuario() {
        if (novo) {
            try {
                if (!dao.verificaUnicidadeNomeUsuario(objeto.getNomeUsuario())) {
                    Util.mesagemErro("Nome de usuário '" + objeto.getNomeUsuario() + "' "
                            + " já existe no banco de dados!");
                    objeto.setNomeUsuario(null);
                    // capturar o componente que chamou o método
                    UIComponent comp
                            = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance());
                    if (comp != null) {
                        // deixar em vermelho após o update
                        UIInput input = (UIInput) comp;
                        input.setValid(false);
                    }
                }
            } catch (Exception e) {
                Util.mesagemErro("Erro do sistema:" + Util.getMensagemErro(e));
            }
        }
    }

    public String listar() {
        return "/privado/usuario/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Usuario();
        novo = true;
        abaAtiva = 0;
    }

    public void alterar(Object id) {
        try {
            objeto = dao.localizar(id);
            novo = false;
            abaAtiva = 0;
        } catch (Exception e) {
            Util.mesagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.localizar(id);
            dao.remove(objeto);
            Util.mesagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mesagemInformacao("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (novo) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mesagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mesagemInformacao("Erro ao salvar objeto: " + Util.getMensagemErro(e));
        }
    }

    public UsuarioDAO<Usuario> getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO<Usuario> dao) {
        this.dao = dao;
    }

    public Usuario getObjeto() {
        return objeto;
    }

    public void setObjeto(Usuario objeto) {
        this.objeto = objeto;
    }

    public Boolean getNovo() {
        return novo;
    }

    public void setNovo(Boolean novo) {
        this.novo = novo;
    }

    public PermissaoDAO<Permissao> getDaoPermissao() {
        return daoPermissao;
    }

    public void setDaoPermissao(PermissaoDAO<Permissao> daoPermissao) {
        this.daoPermissao = daoPermissao;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public int getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(int abaAtiva) {
        this.abaAtiva = abaAtiva;
    }

}
