package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RecordTableView {

    private Scene mainScene;
    private Stage recordStage;
    protected BorderPane border = new BorderPane();



    public RecordTableView() {
        recordStage = new Stage();



        HBox hbox = addHBox();
        border.setTop(hbox);
        border.setCenter(addTableView());

        mainScene = new Scene(border, 500, 600);

        recordStage.setScene(mainScene);
        recordStage.centerOnScreen();

    }

    private HBox addHBox() {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);


        return hbox;
    }

    private TableView addTableView(){

        TableView tableView = new TableView();

//        TableColumn<String, Person> column1 = new TableColumn<>("First Name");
//        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//
//
//        TableColumn<String, Person> column2 = new TableColumn<>("Last Name");
//        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//
//
//        tableView.getColumns().add(column1);
//        tableView.getColumns().add(column2);


        return tableView;
    }


    public Stage getRecordStageStage() {
        return recordStage;
    }

}
