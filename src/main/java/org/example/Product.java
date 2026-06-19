package org.example;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Product {

    private final String description;
    private final double basePrice;
    private final double vat;
    private final int units;
    private final LocalDateTime time;
    private final LocalDate expirationDate;

    public Product(String description, double basePrice, int vat, int units, LocalDateTime time, LocalDate expirationDate) {
        time = time;
        ValidarDsc(description);
        ValidateBp(basePrice);
        ValidateImp(vat);

        this.description = description;
        this.basePrice = basePrice;
        this.vat = vat;
        this.units = units;
        this.time = time;
        this.expirationDate = expirationDate;

    }

    public String getDescription() {
        return description;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getVat() {
        return vat;
    }

    @Override
    public String toString() {
        return "Product \"" + description + "\"";
    }

    private void ValidarDsc(String description){
        if(StringUtils.isBlank(description)){
            throw new IllegalArgumentException("Descripcion no puede ser vacia");
        }
    }

    private void ValidateBp (double bp){
        if(bp < 0d) {
            throw new IllegalArgumentException("Precio base no puede ser negativo");
        }
    }

    private void ValidateImp (int imp){
        if(imp < 0d) {
            throw new IllegalArgumentException("El impuesto no puede ser negativo");
        }
    }

}
