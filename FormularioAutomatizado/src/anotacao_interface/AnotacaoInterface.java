/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anotacao_interface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  Framework de mapeamento de classes para gerar um formulario.
 * 
 * 
 * @author Alexandre
 * @since 16/07/2015
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AnotacaoInterface {
    
  
    public String nomeLabel();
    public String nomeConteudo() default "";
    public TipoCampo tipo();
    
    /**
     * Ordem de exibicao do formulario. Valor padrao '0' determina uma posicao aleatória.
     * Começar a partir do '1'.
     * @return 
     */
    public int ordem() default 0;
}
