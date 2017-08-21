/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anotacao_interface;

/**
 * Dados exrtaídos da classe/objeto na geração do formulario.
 *
 * @author Alexandre
 * @since 16/07/2015
 */
public class DadosExtraidos {
    
    private String nomeLabel;
    private String nomeConteudo;
    private TipoCampo tipo;
    private int ordem;
    private Class classObjeto;
    private Object value;

    public DadosExtraidos() {
    }

    public DadosExtraidos(String nomeLabel, String nomeConteudo, TipoCampo tipo) {
        this.nomeLabel = nomeLabel;
        this.nomeConteudo = nomeConteudo;
        this.tipo = tipo;
    }

    /**
     * Nome do label no formulario.
     * @return 
     */
    public String getNomeLabel() {
        return nomeLabel;
    }

    public void setNomeLabel(String nomeLabel) {
        this.nomeLabel = nomeLabel;
    }

    /**
     * Caso o seja requirido pelo formulario, como no caso de um checkbox/radiobutton.
     * @return 
     */
    public String getNomeConteudo() {
        return nomeConteudo;
    }

    public void setNomeConteudo(String nomeConteudo) {
        this.nomeConteudo = nomeConteudo;
    }

    /**
     * Tipo do campo no formulario. Textfield, checkbox, etc.
     * @return 
     */
    public TipoCampo getTipo() {
        return tipo;
    }

    public void setTipo(TipoCampo tipo) {
        this.tipo = tipo;
    }

    /**
     * Ordem na qual o campo ser� exibido no formulario.
     * @return 
     */
    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    
    /**
     * Classe do objeto no qual o formulario ser� gerado.
     * @return 
     */
    public Class getClassObjeto() {
        return classObjeto;
    }

    public void setClassObjeto(Class classObjeto) {
        this.classObjeto = classObjeto;
    }
    
    /**
     * Valor retornado pelo método.
     * @return 
     */
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Classe do tipo de retorno/parametro do metodo.
     * @return 
     */
    public Class getClasseValue() {
        return value.getClass();
    }
    
    
    @Override
    public String toString() {
        return "DadosExtraidos{" + "nomeLabel=" + nomeLabel + ", nomeConteudo=" + nomeConteudo + ", tipo=" + tipo + '}';
    }
    
    
    
}
