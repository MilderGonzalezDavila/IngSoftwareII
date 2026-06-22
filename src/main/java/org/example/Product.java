package org.example;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Product {

    private final String descripcion;
    private final double precioBase;
    private final int impuesto;
    private final int unidades;
    private final LocalDate fechaVencimiento;

    public Product(String descripcion, double precioBase, int impuesto, int unidades, LocalDate fechaVencimiento) {
        ValidarDescripcion(descripcion);
        ValidarPrecioBase(precioBase);
        ValidarImpuesto(impuesto);
        ValidarUnidades(unidades);
        ValidarFechaVencimiento(fechaVencimiento);

        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.impuesto = impuesto;
        this.unidades = unidades;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public int getUnidades() {
        return unidades;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Product \"" + descripcion + "\"";
    }

    private void ValidarDescripcion(String descripcion){
        if(descripcion == null || StringUtils.isBlank(descripcion)){
            throw new IllegalArgumentException("Descripcion no puede ser null o vacia");
        }
    }

    private void ValidarPrecioBase (double precioBase){
        if(precioBase < 0d) {
            throw new IllegalArgumentException("Precio base no puede ser negativo");
        }
    }

    private void ValidarImpuesto(int impuesto){
        if(impuesto < 0d) {
            throw new IllegalArgumentException("El impuesto no puede ser negativo");
        }
    }

    private void ValidarUnidades(int unidades) {
        if(unidades <= 0) {
            throw new IllegalArgumentException("Las unidades deben ser positivas");
        }
    }

    private void ValidarFechaVencimiento(LocalDate fechaVencimiento) {
        if(fechaVencimiento == null){
            throw new IllegalArgumentException("Fecha de vencimiento no puede ser null");
        }
        if(fechaVencimiento.isBefore(LocalDate.now()) || fechaVencimiento.equals(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de vencimiento debe ser futura");
        }
    }




}
