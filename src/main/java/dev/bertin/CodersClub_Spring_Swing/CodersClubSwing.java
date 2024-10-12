package dev.bertin.CodersClub_Spring_Swing;

import com.formdev.flatlaf.FlatDarculaLaf;
import dev.bertin.CodersClub_Spring_Swing.GUI.MainForm;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class CodersClubSwing {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        ConfigurableApplicationContext contextoSpring = new SpringApplicationBuilder(CodersClubSwing.class).headless(false).web(WebApplicationType.NONE).run(args);
        SwingUtilities.invokeLater(()->{
            MainForm formaPrincipal = contextoSpring.getBean(MainForm.class);
            formaPrincipal.setVisible(true);
        });
    }
}
