package com.company;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.sql.*;
import java.util.*;

//AGENT: COMMAND
public class RecordTableView {

    private Scene mainScene;
    private Stage recordStage;
    protected BorderPane border = new BorderPane();
    //variabili per command
    private Queue<Object> queue;
    private int id;

    //ArrayListSet
    private List<Integer> IDList = new ArrayList<>();

    private List<Array> arrivalList = new ArrayList<Array>();
    private List<Array> burstList = new ArrayList<Array>();



    static final String DB_URL = "jdbc:postgresql://kandula.db.elephantsql.com:5432/rxikhsae";

    //  Database credentials
    static final String USER = "rxikhsae";
    static final String PASS = "hrdIjtZaLpr4ynLFo1nwVxtoPfL-MJdp";

    private TableView table;
    private ObservableList data;


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

        mainScene = new Scene(border, 500, 600);

        final Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);


        recordStage.setScene(mainScene);
        recordStage.centerOnScreen();



        hbox.getChildren().addAll(spacer, addDeleteButton());

        table = new TableView();

        getData();
//        buildData(IDList);
//        createTableView(IDList);
//        TableView<ObservableList<String>> tableView = createTableView(IDList);
//        addTableView();

        border.setCenter(table);

        data = buildData();

        table.setItems(data);

        TableColumn titleCol = new TableColumn("ID");

        table.getColumns().setAll(titleCol);
        table.setPrefWidth(450);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);




    }

    private void getData(){
        try
        {

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM process";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);




            // iterate through the java resultset
            while (rs.next())
            {
                IDList.add(rs.getInt("ID"));
//                idProc = rs.getArray("idProc");
                arrivalList.add(rs.getArray("ArrivalTime"));
                burstList.add(rs.getArray("Burst"));

                // print the results

//                System.out.println("ID: "+timestamp_ID+" - idProc: "+idProc);


            }

            for (int i= 0; i< IDList.size(); i++){
                System.out.println("ID "+IDList.get(i)+"arr "+arrivalList.get(i));
            }


            st.close();


        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    private HBox addHBox() {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);


        return hbox;
    }

//    private TableView addTableView(){
//
////        TableView<ObservableList<String>> tableView = createTableView(IDList);
//
//
////        TableColumn columnOne = new TableColumn("One");
////        TableColumn columnTwo = new TableColumn("Two");
//
////        tableView.getColumns().addAll(columnOne, columnTwo);
////
//
////        return tableView;
//    }

    private ObservableList buildData() {


        ObservableList data = FXCollections.observableList(IDList);

//        for (Object row : IDList) {
//            data.add(FXCollections.observableArrayList(row.toString()));
//            System.out.println((FXCollections.observableArrayList(row.toString())));
//
//        }
        return data;
    }

//    private TableView<ObservableList<String>> createTableView(List dataArray) {
//        TableView<ObservableList<String>> tableView = new TableView<>();
//        tableView.setItems(buildData(dataArray));
//
//        System.out.println(IDList.size());
//
//        for (Object row : IDList) {
//
//            System.out.println("jjj"+row + "     "+IDList.indexOf(row));
//
////        }
////        for (int i = 0; i < IDList.size(); i++) {
////            final int curCol = i;
////            final TableRow<ObservableList<String> row = new TableRow<>(
////                    IDList.get(i)
////            );
////            row.set(
////                    param -> new ReadOnlyObjectWrapper<>(param.getValue().get(curCol))
////            );
////            tableView.getColumns().add(row);
//            System.out.println("columns add");
//        }
//
//        return tableView;
//    }


    public Button addDeleteButton(){


        Button deleteB = new Button("Delete");
        deleteB.setAlignment(Pos.TOP_RIGHT);
        return deleteB;
    }






    public Stage getRecordStageStage() {
        return recordStage;
    }

}
