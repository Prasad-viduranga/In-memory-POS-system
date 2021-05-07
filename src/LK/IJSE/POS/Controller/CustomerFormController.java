package LK.IJSE.POS.Controller;

import LK.IJSE.POS.Module.Customer;
import LK.IJSE.POS.View.TM.CustomerTM;
import LK.IJSE.POS.db.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerFormController {
    public AnchorPane contextOfCustomerForm;
    public TextField txtCid;
    public TextField txtCname;
    public TextField txtSalary;
    public TextField txtAddress;
    public TableColumn colID;
    public TableColumn colCustomerName;
    public TableColumn colCustomerAddress;
    public TableColumn colSalary;
    public TableColumn colOperate;
    public Button btnSave;
    public TableView<CustomerTM> tblCustomer;
    public TextField txtSearch;

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadAllCustomer();

        //--------------------------------------------------------
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
            }
        });
        //--------------------------------------------------------
    }

    private void setData(CustomerTM value) {
        txtCid.setText(value.getId());
        txtCname.setText(value.getName());
        txtAddress.setText(value.getAddress());
        txtSalary.setText(String.valueOf(value.getSalary()));
        btnSave.setText("Update Customer");
    }

    private void loadAllCustomer() {
        //System.out.println("loadAllCustomer");

        ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();

        for (Customer c : DataBase.customerList
        ) {
            Button btn = new Button("Delete");
            observableList.add(
                    new CustomerTM(c.getId(), c.getName(), c.getAddress(), c.getSalary(), btn)
            );
            //System.out.println(observableList);

            btn.setOnAction(e -> {
                if (DataBase.customerList.remove(c)) {
                    System.out.println(e);
                    loadAllCustomer();
                }
            });
        }

        tblCustomer.setItems(observableList);
    }

    public void btnBackToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) contextOfCustomerForm.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBoardForm.fxml"))));
    }

    public void btnSaveCustomerOnAction(ActionEvent actionEvent) {
        try {
            Customer customer1 = new Customer(txtCid.getText(), txtCname.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText())
            );

            if (btnSave.getText().equals("Save Customer")) {
                if (DataBase.customerList.add(customer1)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved.", ButtonType.OK).show();
                    loadAllCustomer();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..", ButtonType.CLOSE).show();
                }

            } else {

                for (int i = 0; i < DataBase.customerList.size(); i++) {
                    if (DataBase.customerList.get(i).getId().equals(txtCid.getText())) {
                        DataBase.customerList.remove(i);
                        DataBase.customerList.add(i, customer1);

                        if (customer1.equals(DataBase.customerList.get(i))) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Updated.", ButtonType.OK).show();
                            loadAllCustomer();
                            break;
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again..", ButtonType.CLOSE).show();
                        }
                    }
                }
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Incorrect Input.", ButtonType.CLOSE).show();
        }
    }

    public void btnNewCustomer(ActionEvent actionEvent) {
        btnSave.setText("Save Customer");
        txtCid.clear();
        txtCname.clear();
        txtAddress.clear();
        txtSalary.clear();
    }

    String searchText = "";

    public void search(KeyEvent keyEvent) {
        /*searchText+= keyEvent.getCharacter();
        System.out.println(searchText);*/
/*
        System.out.println(txtSearch.getText());
*/

        ObservableList searchTM = FXCollections.observableArrayList();
        for (Customer c : DataBase.customerList
        ) {
            Button btn = new Button("Delete");
            if (c.getId().toLowerCase().contains(txtSearch.getText().toLowerCase()) || c.getName().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                    c.getAddress().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                searchTM.add(new CustomerTM(c.getId(), c.getName(), c.getAddress(), c.getSalary(), btn));
            }
        }
        tblCustomer.setItems(searchTM);
    }
}