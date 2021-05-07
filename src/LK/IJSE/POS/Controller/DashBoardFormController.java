package LK.IJSE.POS.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {
    public ImageView btnProfile;
    public ImageView btnItem;
    public ImageView btnCustomer;
    public AnchorPane contextOfDashBoardForm;
    public ImageView btnPlaceOrder;


    public void loadItem(MouseEvent mouseEvent) throws IOException {
        setUI("ItemForm");
    }

    public void loadCustomer(MouseEvent mouseEvent) throws IOException {
        setUI("CustomerForm");
    }

    public void loadPlaceOrder(MouseEvent mouseEvent) throws IOException {
        setUI("PlaceOrderForm");
    }

    public void setUI(String location) throws IOException {

        Stage stage = (Stage) contextOfDashBoardForm.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/" + location + ".fxml"))));

    }
}
