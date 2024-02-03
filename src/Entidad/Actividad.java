/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Actividad {
    private String Proyecto;
    private String Jefe;
    private String Actividad;
    private String PorcentajeAvance;
    private Date FechaFinEstimada;
    private Date FechaRegistro;

    public Date getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(Date FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }

    private String Comentarios;
    private int IdEmpleado;
    private String NombreEmpleado;
    private String Hora;

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }
    public String getNombreEmpleado() {
        return NombreEmpleado;
    }

    public void setNombreEmpleado(String NombreEmpleado) {
        this.NombreEmpleado = NombreEmpleado;
    }
    private int IdActividad;

    public int getIdActividad() {
        return IdActividad;
    }

    public void setIdActividad(int IdActividad) {
        this.IdActividad = IdActividad;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public String getProyecto() {
        return Proyecto;
    }

    public void setProyecto(String Proyecto) {
        this.Proyecto = Proyecto;
    }

    public String getJefe() {
        return Jefe;
    }

    public void setJefe(String Jefe) {
        this.Jefe = Jefe;
    }

    public String getActividad() {
        return Actividad;
    }

    public void setActividad(String Actividad) {
        this.Actividad = Actividad;
    }

    public String getPorcentajeAvance() {
        return PorcentajeAvance;
    }

    public void setPorcentajeAvance(String PorcentajeAvance) {
        this.PorcentajeAvance = PorcentajeAvance;
    }

    public Date getFechaFinEstimada() {
        return FechaFinEstimada;
    }

    public void setFechaFinEstimada(Date FechaFinEstimada) {
        this.FechaFinEstimada = FechaFinEstimada;
    }

    public String getComentarios() {
        return Comentarios;
    }

    public void setComentarios(String Comentarios) {
        this.Comentarios = Comentarios;
    }
}
