/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.abstractas;
import com.mycompany.sistemabancariodemo.modelo.banco.Transaccion;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import java.time.LocalDateTime;

/**
 *
 * @author Dell
 */
public abstract class Cuenta {


    private String numeroCuenta;
    private double saldo;
    private boolean bloqueada;
    private LocalDateTime fechaCreacion;
    private LocalDateTime UltimaModificacion;
    private String usuarioModificacion;
    private Transaccion[] historial=new Transaccion[20];
    private int contadorHistorial=0;
    
    public Cuenta(String numeroCuenta,double saldo,boolean bloqueada,LocalDateTime fechaCreacion,LocalDateTime UltimaModificacion,String usuarioModificacion)throws DatoInvalidoException {
        
        setNumeroCuenta(numeroCuenta);
        setSaldo(saldo);
        this.bloqueada = bloqueada;
        this.fechaCreacion = fechaCreacion;
        this.UltimaModificacion = UltimaModificacion;
        this.usuarioModificacion = usuarioModificacion;
    }
    
    public abstract double calcularInteres();
    public abstract double  getLimiteRetiro();
    public abstract String getTipoCuenta();
    
    public void  verificarBloqueada()throws CuentaBloqueadaException{
        if(bloqueada){
            throw new CuentaBloqueadaException("La cuenta esta bloqueada");
        }
    }
    public void agregarAlhistorial(Transaccion t)throws CapacidadExcedidaException{
        if(contadorHistorial>=20){
            throw new CapacidadExcedidaException("Historial lleno",20);
        }
        historial[contadorHistorial]=t;
        contadorHistorial++;
    }
    public Transaccion[] getHistorial(){
        Transaccion[]copia=new Transaccion[20];
        System.arraycopy(historial,0,copia,0,20);
        return copia;
    }
    
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getUltimaModificacion() {
        return UltimaModificacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public int getContadorHistorial() {
        return contadorHistorial;
    }
    
    public void  setSaldo(double saldo)throws DatoInvalidoException{
        if(saldo<0){
            throw new DatoInvalidoException("Saldo no puede ser un numero negativo","saldo",saldo);
        }
        this.saldo=saldo;
    }
    public void setNumeroCuenta(String numeroCuenta)throws  DatoInvalidoException{
        if(numeroCuenta==null || numeroCuenta.isEmpty()){
            throw new DatoInvalidoException("Numero de cuenta no puede ser nulo o vacio","Numero de cuenta",numeroCuenta);
        }
        this.numeroCuenta=numeroCuenta;
    }
        
        
 
        
    }


