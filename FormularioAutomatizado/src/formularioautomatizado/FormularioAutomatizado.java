/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularioautomatizado;

import gerador_formulario_ui.FormularioJavaFX;
import entidade.Davi;
import entidade.ListaPessoa;
import entidade.Pessoa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author cisne
 */
public class FormularioAutomatizado extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Button btn = new Button();
        btn.setText("Say 'Hello World'");

        root.getChildren().add(btn);

        Scene scene = new Scene(root, 600, 500);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Pessoa p = new Pessoa(1, "Alexandre", "12/03/1987");
                p.setVivo(new Boolean(true));

                Davi davi = new Davi(52, "Davi Angelo", true);

                FormularioJavaFX f = new FormularioJavaFX(300, 250, Davi.class);

                root.getChildren().add(f.getPai());
//                for (String key : mapa.keySet()) {
//                    System.out.println("Key: " + key);
//                    System.out.println(mapa.get(key));
//                }

            }
        });
        
         Pessoa p = new Pessoa(1, "Alexandre", "12/03/1987");
        Pessoa p1 = new Pessoa(2, "Alex", "12/03/1981");
        Pessoa p2 = new Pessoa(3, "andre", "12/03/1982");
        Pessoa p3 = new Pessoa(4, "Renato", "12/03/1983");
        Pessoa p4 = new Pessoa(5, "Fabio", "12/03/1984");
        Pessoa p5 = new Pessoa(6, "Jonata", "12/03/1985");

     p.getNomesFilhos().add("Renan");
     p.getNomesFilhos().add("Nicolas");
     p1.getNomesFilhos().add("Fe");
     p1.getNomesFilhos().add("Nanda");
     
     p2.getNomesFilhos().add("Emily");
     p2.getNomesFilhos().add("Bruno");
     p3.getNomesFilhos().add("Carlos");
     p3.getNomesFilhos().add("Junior");
     
     p4.getNomesFilhos().add("Rodrigo");
     p4.getNomesFilhos().add("Davi");
     
     p5.getNomesFilhos().add("Paulo");
     p5.getNomesFilhos().add("Miguel");
ListaPessoa lp = new ListaPessoa();
lp.add(p);
lp.add(p1);
lp.add(p2);
lp.add(p3);
lp.add(p4);
lp.add(p5);
        try {
            JAXBContext context = JAXBContext.newInstance(ListaPessoa.class);

            Marshaller marshallerObj = context.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshallerObj.marshal(lp, new FileOutputStream("pessoas.xml"));
            Unmarshaller unmarshaller = context.createUnmarshaller();
            
            
            ListaPessoa ps = (ListaPessoa) unmarshaller.unmarshal(new File("pessoas.xml"));
            System.out.println(ps.getPessoas());
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(FormularioAutomatizado.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
