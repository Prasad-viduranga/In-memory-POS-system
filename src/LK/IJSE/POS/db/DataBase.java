package LK.IJSE.POS.db;

import LK.IJSE.POS.Module.Customer;
import LK.IJSE.POS.Module.Item;

import java.util.ArrayList;

public class DataBase {
    public static ArrayList<Customer>customerList=new ArrayList();
    public static ArrayList<Item>itemList=new ArrayList();

    static {
        customerList.add(new Customer("C-001","Nimal","Colombo",25000));
        customerList.add(new Customer("C-002","Wimal","Panadura",27000));

        itemList.add(new Item("I-001","Yogurt","Urubewla",100,35));
        itemList.add(new Item("I-002","Chocolate","Kandos",200,50));

    }
}
