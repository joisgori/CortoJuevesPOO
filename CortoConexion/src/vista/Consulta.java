/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame {

    public JLabel lblCodigo, lblPrecio, lblNombre, lblCantidad, lblTipo, lblDisponibilidad; //6 strings en pantalla,,,

    public JTextField codigo, precio, nombre, cantidad;
    public JComboBox tipo;

    ButtonGroup disponibilidad = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30; //tamaño botones...

    DefaultTableModel tm;

    public Consulta() {
        super("PRODUCTO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCodigo);
        container.add(lblPrecio);
        container.add(lblNombre);
        container.add(lblCantidad);
        container.add(lblTipo);
        container.add(lblDisponibilidad);
        container.add(codigo);
        container.add(precio);
        container.add(nombre);
        container.add(cantidad);
        container.add(tipo);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(850, 700);
        eventos();

    }

    private void agregarLabels() {
        lblCodigo = new JLabel("Codigo");
        lblPrecio = new JLabel("Precio");
        lblNombre = new JLabel("Nombre");
        lblCantidad = new JLabel("Cantidad");
        lblTipo = new JLabel("Tipo");
        lblDisponibilidad = new JLabel("Disponibilidad");
        lblCodigo.setBounds(10, 10, ANCHOC, ALTOC);
        lblPrecio.setBounds(10, 60, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 100, ANCHOC, ALTOC); //La poscion en equis e ye
        lblCantidad.setBounds(10, 140, ANCHOC, ALTOC);
        lblTipo.setBounds(10, 180, ANCHOC, ALTOC);
        lblDisponibilidad.setBounds(10, 220, ANCHOC, ALTOC);
    }

    private void formulario() {
        codigo = new JTextField();
        nombre = new JTextField();
        cantidad = new JTextField();
        tipo = new JComboBox();
        precio = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();

        tipo.addItem("Verduras");
        tipo.addItem("Pan");
        tipo.addItem("Fruta");
        tipo.addItem("Carne");
        tipo.addItem("Vegetales");
        tipo.addItem("Bebida");
        tipo.addItem("Dulces");

        disponibilidad = new ButtonGroup();
        disponibilidad.add(si);
        disponibilidad.add(no);
        //------------------------------------------- //FALTAN COSAS POR ARREGLAR::.... MÁRGENTES Y OTRO ORDENM
        codigo.setBounds(140, 10, ANCHOC, ALTOC); //odigo, precio, nombre, cantidad;
        precio.setBounds(140, 60, ANCHOC, ALTOC);
        nombre.setBounds(140, 100, ANCHOC, ALTOC);
        cantidad.setBounds(140, 140, ANCHOC, ALTOC);
        tipo.setBounds(140, 180, ANCHOC, ALTOC);
        si.setBounds(140, 220, 50, ALTOC);
        no.setBounds(210, 220, 50, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 260, ANCHOC, ALTOC);
        actualizar.setBounds(150, 260, ANCHOC, ALTOC);
        eliminar.setBounds(300, 260, ANCHOC, ALTOC);
        limpiar.setBounds(450, 260, ANCHOC, ALTOC);

        //Acá la tabla de bases...
        resultados = new JTable();
        table.setBounds(10, 400, 600, 200);
        table.add(new JScrollPane(resultados));

    }

    private void llenarTabla() {        //CHEQUEAR ACÁ LOS CAMBIOS...
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    default:        //Esto es pal chekbox
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("Nombre");
        tm.addColumn("Codigo");
        tm.addColumn("Tipo");
        tm.addColumn("Cantidad");
        tm.addColumn("Precio");
        tm.addColumn("Disponibilidad");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();

        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getNombre(), fi.getCodigo(), fi.getTipo(), fi.getCantidad(), fi.getPrecio(), fi.getDisponibilidad()});
        }

        resultados.setModel(tm);

    }

    private void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(nombre.getText(), codigo.getText(), tipo.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()), Float.parseFloat(precio.getText()), true);

                if (no.isSelected()) {
                    f.setDisponibilidad(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro registrado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema con la creación de este filtro.");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(nombre.getText(), codigo.getText(), tipo.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()), Float.parseFloat(precio.getText()), true);

                if (no.isSelected()) {
                    f.setDisponibilidad(false);
                }

                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro modificado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de creación de este filtro.");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(codigo.getText());
                if (fd.delete(codigo.getText())) {
                    JOptionPane.showMessageDialog(null, "Filtro eliminado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar este filtro.");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(codigo.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "El Filtro buscado no ha sido encontrado");
                } else {

                    codigo.setText(f.getCodigo());
                    tipo.setSelectedItem(f.getTipo());
                    nombre.setText(f.getNombre());
                    cantidad.setText(Integer.toString(f.getCantidad()));

                    if (f.getDisponibilidad()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    public void limpiarCampos() {
        nombre.setText("");
        precio.setText("");
        codigo.setText("");
        tipo.setSelectedItem("Verduras");
        cantidad.setText("");

    }

}
