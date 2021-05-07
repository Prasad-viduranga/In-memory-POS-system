package LK.IJSE.POS.Module;

public class Item {
    private String ID;
    private String Name;
    private String Description;
    private int Qty;
    private double UnitPrice;

    public Item(String ID, String name, String description, int qty, double unitPrice) {
        this.ID = ID;
        Name = name;
        Description = description;
        Qty = qty;
        UnitPrice = unitPrice;
    }

    public Item() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }
}
