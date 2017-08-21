/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerador_formulario_ui;

import anotacao_interface.DadosExtraidos;
import anotacao_interface.ExtratorDados;
import gerador_formulario_ui.FormularioJavaFX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Classe de criaÁ„o de formul·rio em Java FX.
 * Esta classe possui dois construtores: um para o Objeto, cujo o formul·tio È prenchido com os dados do mesmo.
 * Ou pela classe, no qual os nomes dos campos e o formul·rio È criado.
 *
 * @author Alexandre
 * @since 16/07/2015
 */
public class FormularioJavaFX {

    private final HBox pai;
    private final VBox colunaLabels;
    private final VBox colunaCampos;
    private final Map<String, DadosExtraidos> mapaForm;
    private final Map<Node, String> mapaCampos;
    private final String[] ordenaKeys;
    private final Button botaoOk;
    private Object novoObjeto;
    private Class classObjeto;

    public FormularioJavaFX(double w, double h, Object o) {
        botaoOk = new Button("OK");
        botaoOk.setOnAction(new AcaoBotao());
        mapaCampos = new HashMap<>();
        mapaForm = ExtratorDados.extratorDadosToFormulario(o);
        ordenaKeys = new String[mapaForm.keySet().size()];
        colunaCampos = new VBox(10);
        colunaLabels = new VBox(20);
        pai = new HBox(colunaLabels, colunaCampos);
        pai.setSpacing(10);
       
        ordena();
        preencheLabels();
        preencheCampos();
    }
    
     public FormularioJavaFX(double w, double h, Class o) {
        botaoOk = new Button("OK");
        botaoOk.setOnAction(new AcaoBotao());
        mapaCampos = new HashMap<>();
        mapaForm = ExtratorDados.extratorDadosToFormularioClass(o);
        ordenaKeys = new String[mapaForm.keySet().size()];
        colunaCampos = new VBox(10);
        colunaLabels = new VBox(20);
        pai = new HBox(colunaLabels, colunaCampos);
        pai.setSpacing(10);
       
        ordena();
        preencheLabels();
        preencheCampos();
    }
    

    /**
     * Ordena de acordo com o getOrdem, reajusta se h√° entrada com o mesmo
     * numero de ordem e trata exce√ß√£o caso o valor da ordem seja maior que o
     * tamanho do set.
     *
     * @throws ArrayIndexOutOfBoundsException
     */
    private void ordena() throws ArrayIndexOutOfBoundsException {
        List<Integer> listaIdDisponivel = new ArrayList<>();
        List<String> ordemZero = new ArrayList<>();
        for (String key : mapaForm.keySet()) {
            int index = mapaForm.get(key).getOrdem();
            if (index > mapaForm.keySet().size()) {
                throw new ArrayIndexOutOfBoundsException("Ordem " + index + " : tamanho do array: " + mapaForm.keySet().size());
            }
            if (index > 0) {
                index--;
            } else {
                ordemZero.add(key);
                continue;
            }
            if (ordenaKeys[index] == null) {
                ordenaKeys[index] = key;
            } else {
                mapaForm.get(key).setOrdem(0);
                ordemZero.add(key);
            }
        }
        if (!ordemZero.isEmpty()) {
            int max = 0;
            for (int i = 0; i < ordenaKeys.length; i++) {
                if (ordenaKeys[i] != null) {
                    max = Math.max(max, i);
                } else {
                    listaIdDisponivel.add(i);
                }
            }

            if (listaIdDisponivel.size() == ordemZero.size()) {
                int ordemZeroIndex = 0;
                for (Integer idDisp : listaIdDisponivel) {
                    ordenaKeys[idDisp] = ordemZero.get(ordemZeroIndex);
                    ordemZeroIndex++;
                }
            }

        }

    }

    /**
     * Inicia os labels de acordo com as anotacoes.
     */
    private void preencheLabels() {
        for (String key : ordenaKeys) {
            colunaLabels.getChildren().add(new Label(mapaForm.get(key).getNomeLabel()));
        }
    }

    /**
     * constroi os campos como especificado pelas anotacoes.
     */
    private void preencheCampos() {
        for (String key : ordenaKeys) {
            DadosExtraidos tc = mapaForm.get(key);

            colunaCampos.getChildren().add(tratarTipoCampo(tc, key));
        }
        colunaCampos.getChildren().add(botaoOk);
    }

    /**
     * Instancia os campos como nas anotacoes, e guarda uma referencia de quem √© propriet√°rio do campo.
     * @param dados
     * @param nome
     * @return 
     */
    private Node tratarTipoCampo(DadosExtraidos dados, String nome) {
//        System.out.println("CLASSE DO VALUE" +dados.getClasse());
        switch (dados.getTipo()) {
            case CheckBox:
                CheckBox chk = new CheckBox(dados.getNomeConteudo());
                if (dados.getValue() != null) {
                    Boolean checked = (Boolean) dados.getValue();
                    chk.setSelected(checked);
                }
                mapaCampos.put(chk, nome);
                return chk;
            case TextField:
                TextField tf = new TextField();
                if (dados.getValue() != null) {
                    
                    tf.setText(dados.getValue().toString());
                }
                
                mapaCampos.put(tf, nome);
                return tf;
        }
        return null;
    }

    
    /**
     * Painel principal do formulario.
     * @return 
     */
    public HBox getPai() {
        return pai;
    }

    /**
     * Coluna dos Labels.
     *
     * @return
     */
    public VBox getColunaLabels() {
        return colunaLabels;
    }

    /**
     * Coluna dos campos.
     *
     * @return
     */
    public VBox getColunaCampos() {
        return colunaCampos;
    }

    /**
     * Botao ok
     *
     * @return
     */
    public Button getBotaoOk() {
        return botaoOk;
    }

    public void setCss(String id) {
        pai.setId(id);
    }

    private Object getValorNode(Node n) {
        if (n instanceof TextField) {
            return ((TextField) n).getText();
        }
        if (n instanceof CheckBox) {
            return String.valueOf(((CheckBox) n).isSelected());
        }

        return null;
    }

    public void limparTodos() {
        for (Node n : colunaCampos.getChildren()) {
            if (n instanceof TextField) {
                ((TextField)n).clear();
            } else if (n instanceof CheckBox) {
                ((CheckBox)n).setSelected(false);
            }
        }
    }
    
    /**
     * Extrai os dados do formulario instanciando um novo objeto com suas informacoes.
     * @return Objeto
     */
    public Object extrairDadosFormulario() {
        for (Node n : mapaCampos.keySet()) {

            String id = mapaCampos.get(n);
            Object param = getValorNode(n);
            if (classObjeto == null) {
                classObjeto = mapaForm.get(id).getClassObjeto();
                try {
                    novoObjeto = classObjeto.newInstance();
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(FormularioJavaFX.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            ExtratorDados.extratorDadosFromFormulario(novoObjeto.getClass(), novoObjeto, id, param);

        }
        return novoObjeto;
    }

    private class AcaoBotao implements EventHandler<ActionEvent> {

        public AcaoBotao() {

        }

        @Override
        public void handle(ActionEvent event) {
    

            System.out.println(extrairDadosFormulario());
        }

    }

}
