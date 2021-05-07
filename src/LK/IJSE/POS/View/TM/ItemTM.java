package LK.IJSE.POS.View.TM;


import javafx.scene.control.Button;

public class ItemTM {

    private String ID;
    private String Name;
    private String Description;
    private int Qty;
    private double UnitPrice;
    private Button btn;


    public ItemTM(String ID, String name, String description, int qty, double unitPrice, Button btn) {
        this.ID = ID;
        Name = name;
        Description = description;
        Qty = qty;
        UnitPrice = unitPrice;
        this.btn = btn;
    }

    public ItemTM() {
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "ItemTM{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Qty=" + Qty +
                ", UnitPrice=" + UnitPrice +
                ", btn=" + btn +
                '}';
    }
}
