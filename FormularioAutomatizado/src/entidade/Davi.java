/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import anotacao_interface.AnotacaoInterface;
import anotacao_interface.TipoCampo;

/**
 *	Exemplo de entidade com anotações do Formulario Automatizado. 
 * @author Alexandre
 */
public class Davi {

    private int idDavi;
    private String nomeDavi;
    private boolean camisaXadrez;

    public Davi() {
    }

    public Davi(int idDavi, String nomeDavi, boolean camisaXadrez) {
        this.idDavi = idDavi;
        this.nomeDavi = nomeDavi;
        this.camisaXadrez = camisaXadrez;
    }

    @AnotacaoInterface(nomeLabel = "ID Davi", tipo = TipoCampo.TextField, ordem = 1)
    public int getIdDavi() {
        return idDavi;
    }

    public void setIdDavi(int idDavi) {
        this.idDavi = idDavi;
    }

    @AnotacaoInterface(nomeLabel = "Nome", tipo = TipoCampo.TextField, ordem = 2)
    public String getNomeDavi() {
        return nomeDavi;
    }

    public void setNomeDavi(String nomeDavi) {
        this.nomeDavi = nomeDavi;
    }

    @AnotacaoInterface(nomeLabel = "Camisa Xadrez", nomeConteudo = "Usando", tipo = TipoCampo.CheckBox, ordem = 3)
    public boolean isCamisaXadrez() {
        return camisaXadrez;
    }

    public void setCamisaXadrez(boolean camisaXadrez) {
        this.camisaXadrez = camisaXadrez;
    }

    @Override
    public String toString() {
        return "Davi{" + "idDavi=" + idDavi + ", nomeDavi=" + nomeDavi + ", camisaXadrez=" + camisaXadrez + '}';
    }

}
