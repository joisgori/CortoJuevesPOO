/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Filtro {

    private int id;
    private String codigo;
    private float precio;
    private String nombre;
    private int cantidad;
    private String tipo;
    private boolean disponibilidad;

    //Se crean los constructores, vacíos o con parámetros que permitirán la inicialización del programa con ciertos valores predeterminados...
    public Filtro() {
    }
//rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getBoolean(7)
    public Filtro(int id, String codigo, float precio, String nombre, int cantidad, String tipo, boolean disponibilidad) {
        this.id = id;
        this.codigo = codigo;
        this.precio = precio;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.disponibilidad = disponibilidad;
    }

    public Filtro(String codigo, String tipo, int cantidad, boolean disponibilidad) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.disponibilidad = disponibilidad;
    }

    public Filtro(String tipo, int cantidad, boolean disponibilidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.disponibilidad = disponibilidad;
    }

    //Se definen todos los métodos getter...
    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    //Se definen todos los métodos setter...
    public void setId(int id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //Igual, este creo que ni lo ocuparéw....
    public boolean isDisponibilidad() {
        return disponibilidad;
    }

}
