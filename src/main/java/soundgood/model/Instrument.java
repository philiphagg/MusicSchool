package soundgood.model;

public class Instrument {
    public String type;
    public String brand;
    public int qty;

    public Instrument(String instrumentType, String brand, int qtyInStock) {
        this.type = instrumentType;
        this.brand = brand;
        this.qty = qtyInStock;

    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public int getQty() {
        return qty;
    }
}
