package LK.IJSE.POS.Controller;
import LK.IJSE.POS.Module.Customer;
import LK.IJSE.POS.db.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlaceOrderFormController {
    public Button btnBack;
    public Label contextOfPlaceOrder;
    public Label txtOrderId;
    public Label txtDate;
    public Label txtTime;
    public ComboBox<String> cmbCustomer;
    public ComboBox<String> cmbItem;

    public void initialize() {
        setDataAndTime();
        loadallCustomer();
    }

    private void loadallCustomer() {
        ObservableList obList= FXCollections.observableArrayList();
        for (Customer c: DataBase.customerList
             ) {

        }
    }

    private void setDataAndTime() {
        Date date = new Date();
        txtTime.setText(new SimpleDateFormat("h-mm-ss").format(date));
        txtDate.setText(new SimpleDateFormat("yyy-MM-dd").format(date));

    }


    public void loadDashBoard(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) contextOfPlaceOrder.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBoardForm.fxml"))));
    }
}
