package LK.IJSE.POS.Controller;

import LK.IJSE.POS.Module.Item;
import LK.IJSE.POS.View.TM.ItemTM;
import LK.IJSE.POS.db.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class ItemFormController {
    public AnchorPane contextOfItemForm;
    public Button btnBackToHome;
    public Button btnNewItem;
    public Button btnSave;
    public TextField txtItem;
    public TextField txtQTY;
    public TextField txtUnitPrice;
    public TextField txtDescription;
    public TextField txtSearch;
    public TableView<ItemTM> tblItem;
    public TableColumn colItemCode;
    public TableColumn colItemDescription;
    public TableColumn colItemQty;
    public TableColumn colItemUnitPrice;
    public TableColumn colItemName;
    public TableColumn colItemOperation;
    public TextField txtName;


    public void initialize() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colItemOperation.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadItems("");

        //--------------------------------------------------------
        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData((newValue));
            }
        });

        //--------------------------------------------------------
    }

    private void setData(ItemTM value) {
        //System.out.println(value);
        txtItem.setText(value.getID());
        txtName.setText(value.getName());
        txtDescription.setText(value.getDescription());
        txtQTY.setText(String.valueOf(value.getQty()));
        txtUnitPrice.setText(String.valueOf(value.getUnitPrice()));
        btnSave.setText("Update Item");
    }


    private void loadItems(String search) {
        ObservableList<ItemTM> obList = FXCollections.observableArrayList();

        for (Item i : DataBase.itemList
        ) {
            Button btn = new Button("Delete");
            //if (i.getID().toLowerCase().contains(txtSearch.getText()) || i.getName().toLowerCase().contains(txtSearch.getText()) ||
            //i.getDescription().toLowerCase().contains(txtSearch.getText())) {
            obList.add(new ItemTM(i.getID(), i.getName(), i.getDescription(), i.getQty(), i.getUnitPrice(), btn));
            //}

            btn.setOnAction(event -> {
                if (DataBase.itemList.remove(i)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Deleted", ButtonType.OK).show();
                    loadItems("");
                }
            });
        }
        tblItem.setItems(obList);
    }


    public void newItemOnAction(ActionEvent actionEvent) {
        txtItem.clear();
        txtName.clear();
        txtDescription.clear();
        txtQTY.clear();
        txtUnitPrice.clear();
        btnSave.setText("Save Item");

    }

    public void saveOnAction(ActionEvent actionEvent) {

        if (btnSave.getText().equals("Save Item")) {

            Item item = new Item(txtItem.getText(), txtName.getText(), txtDescription.getText(), Integer.parseInt(txtQTY.getText()), Double.parseDouble(txtUnitPrice.getText()));

            if (DataBase.itemList.add(item)) {

                new Alert(Alert.AlertType.CONFIRMATION, "Saved.", ButtonType.OK).show();
            } else {

                new Alert(Alert.AlertType.WARNING, "Try again..", ButtonType.CLOSE).show();
            }

            loadItems("");

        } else {
            Button btn = new Button("Delete");
            Item item = new Item(txtItem.getText(), txtName.getText(), txtDescription.getText(), Integer.parseInt(txtQTY.getText()), Double.parseDouble(txtUnitPrice.getText()));


            for (int i = 0; i < DataBase.itemList.size() - 1;i++) {

                if (DataBase.itemList.get(i).getID().equals(txtItem.getText())) {
                    DataBase.itemList.remove(i);
                    DataBase.itemList.add(i, item);

                    if (DataBase.itemList.get(i).equals(item)) {

                        new Alert(Alert.AlertType.CONFIRMATION, "Saved.", ButtonType.OK).show();
                    } else {

                        new Alert(Alert.AlertType.WARNING, "Try again..", ButtonType.CLOSE).show();
                    }
                    loadItems("");
                }
                break;
            }
        }
    }


    public void loadDashBoard(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) contextOfItemForm.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBoardForm.fxml"))));

    }

    public void search(KeyEvent keyEvent) {
        ObservableList<ItemTM> obListSearch = FXCollections.observableArrayList();

        for (Item i : DataBase.itemList
        ) {
            Button btn = new Button("Delete");

            if (i.getID().toLowerCase().contains(txtSearch.getText()) || i.getName().toLowerCase().contains(txtSearch.getText()) ||
                    i.getDescription().toLowerCase().contains(txtSearch.getText())) {

                obListSearch.add(new ItemTM(i.getID(), i.getName(), i.getDescription(), i.getQty(), i.getUnitPrice(), btn));

            }
        }
        tblItem.setItems(obListSearch);
    }
}
