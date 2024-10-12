package dev.bertin.CodersClub_Spring_Swing.GUI;

import dev.bertin.CodersClub_Spring_Swing.modelo.Socio;
import dev.bertin.CodersClub_Spring_Swing.servicio.ISocioServicio;
import dev.bertin.CodersClub_Spring_Swing.servicio.SocioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@Component
public class MainForm extends JFrame{

    ISocioServicio socioServicio;
    private JPanel panelPrincipal;
    private JTable sociosTabla;
    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoMembresia;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    private DefaultTableModel tablaModeloSocios;
    private Integer idSocio;


    @Autowired
    public MainForm(SocioServicio socioServicio){
        this.socioServicio = socioServicio;
        iniciar();

        guardarButton.addActionListener(e -> { guardarSocio(); });
        sociosTabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                recabarSocio();
            }
        });
        eliminarButton.addActionListener(e -> { eliminarSocio();});
        limpiarButton.addActionListener(e -> { limpiarFormulario();});
    }

    private void iniciar(){
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,700);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tablaModeloSocios = new DefaultTableModel(0,4){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        String[] cabeceros = {"Id","Nombre","Apellido", "Membresia"};
        this.tablaModeloSocios.setColumnIdentifiers(cabeceros);
        this.sociosTabla = new JTable(this.tablaModeloSocios);
        this.sociosTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listarSocios();

    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje);
    }

    private void listarSocios(){
        this.tablaModeloSocios.setRowCount(0);
        List<Socio> socios = this.socioServicio.listarSocios();
        socios.forEach(socio -> {
            Object[] renglonSocio = {socio.getId(),socio.getNombre(),socio.getApellido(),socio.getMembresia()};
            this.tablaModeloSocios.addRow(renglonSocio);
        });
    }

    private void guardarSocio(){
        if(campoNombre.getText().equals("")){
            mostrarMensaje("Proporciona un nombre");
            campoNombre.requestFocusInWindow();
            return;
        }
        if(campoApellido.getText().equals("")){
            mostrarMensaje("Proporciona un apellido");
            campoApellido.requestFocusInWindow();
            return;
        }

        if(campoMembresia.getText().equals("")){
            mostrarMensaje("Proporciona un numero de membresia");
            campoMembresia.requestFocusInWindow();
            return;
        }

        String nombre = campoNombre.getText();
        String apellido = campoApellido.getText();
        Integer membresia = Integer.parseInt(campoMembresia.getText());
        Socio socio = new Socio(this.idSocio,nombre,apellido,membresia);
        this.socioServicio.guardarSocio(socio);
        if(this.idSocio ==null){
            mostrarMensaje("Se agregó el nuevo cliente.");
        }else{
            mostrarMensaje("Se modificó el cliente");
        }
        limpiarFormulario();
        listarSocios();
    }

    private void eliminarSocio(){
        Socio socio = new Socio();
        socio.setId(this.idSocio);
        if(this.idSocio == null){
            mostrarMensaje("Selecciona de la tabla un socio a eliminar.");
        }else{
            this.socioServicio.eliminarSocio(socio);
            mostrarMensaje("Socio eliminado");
        }
        limpiarFormulario();
        listarSocios();
    }

    private void recabarSocio(){
        int renglon = sociosTabla.getSelectedRow();
        if(renglon != -1){
            Integer id = Integer.parseInt(sociosTabla.getModel().getValueAt(renglon,0).toString());
            this.idSocio = id;
            String nombre = sociosTabla.getModel().getValueAt(renglon,1).toString();
            this.campoNombre.setText(nombre);
            String apellido = sociosTabla.getModel().getValueAt(renglon,2).toString();
            this.campoApellido.setText(apellido);
            String membresia = sociosTabla.getModel().getValueAt(renglon,3).toString();
            this.campoMembresia.setText(membresia);

        }
    }

    private void limpiarFormulario(){
        campoNombre.setText("");
        campoApellido.setText("");
        campoMembresia.setText("");
        this.idSocio = null;
        this.sociosTabla.getSelectionModel().clearSelection();
    }
}
