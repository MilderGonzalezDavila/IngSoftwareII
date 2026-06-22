package org.example;

public class InvoiceLine {

    private final String productDescription;
    private final double basePrice;
    private final double appliedVat;
    private final double totalPrice;
    private final int units;

    public InvoiceLine(Product product, int units) {
        if(product == null) {
            throw new IllegalArgumentException("product cannot be null");
        }

        this.productDescription = product.getDescripcion();
        this.basePrice = product.getPrecioBase();
        this.appliedVat = product.getImpuesto();
        this.units = units;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        return basePrice * (1 + appliedVat / 100) * this.units;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getAppliedVat() {
        return appliedVat;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
