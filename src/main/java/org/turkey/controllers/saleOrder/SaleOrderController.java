package org.turkey.controllers.saleOrder;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.turkey.models.*;
import org.turkey.services.MockUpData;
import org.turkey.services.NavBarService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class SaleOrderController {
    @FXML private JFXButton waitCreateBillBtn, waitPayBtn, doneBtn;
    @FXML private TableView<Order> table;
    @FXML private TableColumn<Order,String> code,customer;
    @FXML private TableColumn<Order, Float> price;
    private ArrayList<Order> orders;
    private Order order;
    private OrderLine orderLine;
    private ObservableList list;

    @FXML public void initialize() {
        table.setRowFactory( tv -> {
            TableRow<Order> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    Order rowData = row.getItem();
                    try {
                        // this.updateOrderToWaitPay();
                        // this.updateOrderToComplete();
                        this.showOrderComplete();
                    } catch (IOException e) {
                        // do nothing . . .
                    }
                    System.out.println(rowData);
                }
            });
            return row;
        });

        orders = new ArrayList<>();
        MockUpData.mockUpSO(orders);
        showWaitCreateBill();
    }

    @FXML private void createSaleOrder() throws IOException {
        Stage createSaleOrderPage = new Stage();
        createSaleOrderPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/createSaleOrder.fxml"));
        Scene scene = new Scene(loader.load());
        createSaleOrderPage.setScene(scene);
        CreateSaleOrderController csc = loader.getController();
        csc.setTable(table);
        csc.setCodeCol(code);
        csc.setPrice(price);
        csc.setCustomerCol(customer);
        ArrayList<Order> arrayList = new ArrayList<>();
        for(Order order: orders){
            if(((SaleOrder)order).getStatus().equals(SaleStatus.WaitCreateBill)){
                arrayList.add(order);
            }
        }
        csc.setWaitCrateBillSO(arrayList);
        createSaleOrderPage.show();
    }

    // เปลี่ยนสถานะ Order เป็น 'WaitPay'
    @FXML private void updateOrderToWaitPay() throws IOException {
        Stage updateSaleOrderToWaitPayPage = new Stage();
        updateSaleOrderToWaitPayPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/toWaitPay/updateSaleOrderToWaitPay.fxml"));
        Scene scene = new Scene(loader.load());
        updateSaleOrderToWaitPayPage.setScene(scene);
        updateSaleOrderToWaitPayPage.setTitle("เปลี่ยนสถานะใบสั่งขาย");
        updateSaleOrderToWaitPayPage.setResizable(false);
        updateSaleOrderToWaitPayPage.show();
    }

    // เปลี่ยนสถานะ Order เป็น 'Complete'
    @FXML private void updateOrderToComplete() throws IOException {
        Stage updateSaleOrderToCompletePage = new Stage();
        updateSaleOrderToCompletePage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/toComplete/updateSaleOrderToComplete.fxml"));
        Scene scene = new Scene(loader.load());
        updateSaleOrderToCompletePage.setScene(scene);
        updateSaleOrderToCompletePage.setTitle("เปลี่ยนสถานะใบสั่งขาย");
        updateSaleOrderToCompletePage.setResizable(false);
        updateSaleOrderToCompletePage.show();
    }

    // แสดง Order (สำหรับ Order ที่มีสถานะ 'Complete')
    @FXML private void showOrderComplete() throws IOException {
        Stage showSaleOrderCompletePage = new Stage();
        showSaleOrderCompletePage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/showSaleOrderComplete.fxml"));
        Scene scene = new Scene(loader.load());
        showSaleOrderCompletePage.setScene(scene);
        showSaleOrderCompletePage.setTitle("ใบสั่งขาย");
        showSaleOrderCompletePage.setResizable(false);
        showSaleOrderCompletePage.show();
    }

    @FXML private void showWaitCreateBill() {
        clearBtnStyle();
        this.waitCreateBillBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
        ArrayList<Order> arrayList = new ArrayList<>();
        for(Order order: orders){
            if(((SaleOrder)order).getStatus().equals(SaleStatus.WaitCreateBill)){
                arrayList.add(order);
            }
        }
        setSOTable(arrayList);
    }

    @FXML private void showWaitPay() {
        clearBtnStyle();
        this.waitPayBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
        ArrayList<Order> arrayList = new ArrayList<>();
        for(Order order: orders){
            if(((SaleOrder)order).getStatus().equals(SaleStatus.WaitPay)){
                arrayList.add(order);
            }
        }
        setSOTable(arrayList);
    }

    @FXML private void showDone() {
        clearBtnStyle();
        this.doneBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
        ArrayList<Order> arrayList = new ArrayList<>();
        for(Order order: orders){
            if(((SaleOrder)order).getStatus().equals(SaleStatus.Complete)){
                arrayList.add(order);
            }
        }
        setSOTable(arrayList);
    }

    @FXML private void clearBtnStyle() {
        this.waitCreateBillBtn.setStyle("-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-radius: 50");
        this.waitPayBtn.setStyle("-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-radius: 50");
        this.doneBtn.setStyle("-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-radius: 50");
    }

    public void setSOTable(ArrayList<Order> arrayList){
        table.getItems().clear();
        list = FXCollections.observableArrayList(arrayList);
        table.setItems(list);
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        customer.setCellValueFactory(new PropertyValueFactory<>("partner"));
    }
    // Page Switcher
    @FXML private void toHome() throws IOException { NavBarService.switchToHome(); }
    @FXML private void toCustomer() throws IOException { NavBarService.switchToCustomer(); }
    @FXML private void toSaleOrder() throws IOException { NavBarService.switchToSaleOrder(); }
    @FXML private void toStock() throws IOException { NavBarService.switchToStock(); }
    @FXML private void toReport() throws IOException { NavBarService.switchToReport(); }
}
