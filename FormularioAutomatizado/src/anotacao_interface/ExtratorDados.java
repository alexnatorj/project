/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anotacao_interface;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ClassUtils;

/**
 * Classe responsavel por extrair dados de um objeto/classe e gerar um
 * formulario, e extrair os dados de um formulario e transformá-los em um
 * objeto.
 *
 * @author Alexandre
 */
public class ExtratorDados {

    private static Map<String, Method> mapaSetters;

    private ExtratorDados() {

    }

    /**
     * Extrai as anotacoes da classe e gerar um mapa desses dados. Essa
     * informação irá gerar os campos dos formulários.
     *
     * @param c classe
     * @param o objeto
     * @return
     */
    public static Map<String, DadosExtraidos> extratorDadosToFormulario(Object o) {
        Map<String, DadosExtraidos> mapaFormulario = new HashMap<>();
        mapaSetters = new HashMap<>();
        Class c = o.getClass();
        for (Method method : c.getMethods()) {

            // se metodo está anotado com @AnotacaoInterface
            if (method.isAnnotationPresent(AnotacaoInterface.class)) {

                Annotation annotation = method.getAnnotation(AnotacaoInterface.class);
                AnotacaoInterface anotacao = (AnotacaoInterface) annotation;
                DadosExtraidos de = new DadosExtraidos(anotacao.nomeLabel(), anotacao.nomeConteudo(), anotacao.tipo());
                de.setOrdem(anotacao.ordem());
                de.setClassObjeto(c);

                try {
                    if (o != null) {
                        Object obj = method.invoke(o);
                        if (obj != null) {
                            de.setValue(obj);
                        }
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(ExtratorDados.class.getName()).log(Level.SEVERE, null, ex);
                }
                mapaFormulario.put(anotacao.nomeLabel(), de);
                mapaSetters.put(anotacao.nomeLabel(), method);
            }
        }

        return mapaFormulario;
    }

    /**
     * 
     * @param c
     * @return 
     */
    public static Map<String, DadosExtraidos> extratorDadosToFormularioClass(Class c) {
        Map<String, DadosExtraidos> mapaFormulario = new HashMap<>();
        mapaSetters = new HashMap<>();
        
        for (Method method : c.getMethods()) {

            // se metodo está anotado com @AnotacaoInterface
            if (method.isAnnotationPresent(AnotacaoInterface.class)) {

                Annotation annotation = method.getAnnotation(AnotacaoInterface.class);
                AnotacaoInterface anotacao = (AnotacaoInterface) annotation;
                DadosExtraidos de = new DadosExtraidos(anotacao.nomeLabel(), anotacao.nomeConteudo(), anotacao.tipo());
                de.setOrdem(anotacao.ordem());
                de.setClassObjeto(c);

//                try {
//                    if (o != null) {
//                        Object obj = method.invoke(o);
//                        if (obj != null) {
//                            de.setValue(obj);
//                        }
//                    }
//                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//                    Logger.getLogger(ExtratorDados.class.getName()).log(Level.SEVERE, null, ex);
//                }
                mapaFormulario.put(anotacao.nomeLabel(), de);
                mapaSetters.put(anotacao.nomeLabel(), method);
            }
        }

        return mapaFormulario;
    }
    
    /**
     * Mapa dos metodos a serem transformados em metodos setters.
     *
     * @return
     */
    public static Map<String, Method> getMapaSetters() {
        if (mapaSetters == null) {
            throw new NullPointerException("Extração de dados para o formulário ainda não foi efetuada!");
        } else {
            return mapaSetters;
        }
    }

    /**
     * Este metodo extrai os dados do formulario e retorna um Objeto.
     *
     *
     * @param c Classe do objeto instanciado
     * @param o Objeto instanciado.
     * @param id id para recuperar o nome do metodo. nomeLabel
     * @param param parametro do metodo setter
     * @return
     */
    public static Object extratorDadosFromFormulario(Class c, Object o, String id, Object param) {

        Method method = getMapaSetters().get(id);

        // if method is annotated with @Test
        if (method.isAnnotationPresent(AnotacaoInterface.class)) {

            String setter = null;
            if (method.getName().startsWith("get")) {
                setter = method.getName().replaceFirst("get", "set");
            } else if (method.getName().startsWith("is")) {
                setter = method.getName().replaceFirst("is", "set");
            }
            try {
                Method m = c.getMethod(setter, method.getReturnType());

                Class parse = method.getReturnType();
                if (method.getReturnType().isPrimitive()) {
                    parse = ClassUtils.primitiveToWrapper(parse);
                }
                Object refl = parse.getConstructor(String.class).newInstance((String) param);

                m.invoke(o, refl);
//                    }
//                    if (method.getReturnType().getSimpleName().equalsIgnoreCase(Boolean.class.getSimpleName())) {
//                    m.invoke(o, false);
//                    }
            } catch (Exception ex) {
                Logger.getLogger(ExtratorDados.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }

        }

        return o;
    }

}
