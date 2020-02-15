package com.company;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.util.Queue;
//AGENT: COMMAND
public class RecordTableView {

    private Scene mainScene;
    private Stage recordStage;
    protected BorderPane border = new BorderPane();
    //variabili per command
    private Queue<Object> queue;
    private int id;

    //metodo per command
    public void placeDelete(Delete delete){

        queue.add(delete);
        delete.executeAction(id);

    }
    //

    public RecordTableView() {
        recordStage = new Stage();



        HBox hbox = addHBox();
        border.setTop(hbox);
        border.setCenter(addTableView());

        mainScene = new Scene(border, 500, 600);

        final Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);


        recordStage.setScene(mainScene);
        recordStage.centerOnScreen();

        hbox.getChildren().addAll(spacer, addDeleteButton());


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


    public Button addDeleteButton(){


        Button deleteB = new Button("Delete");
        deleteB.setAlignment(Pos.TOP_RIGHT);
        return deleteB;
    }






    public Stage getRecordStageStage() {
        return recordStage;
    }

}
