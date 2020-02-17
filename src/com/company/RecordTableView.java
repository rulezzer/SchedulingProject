package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;



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
    private List<Integer> contextSwitch = new ArrayList<>();



    static final String DB_URL = "jdbc:postgresql://kandula.db.elephantsql.com:5432/rxikhsae";

    //  Database credentials
    static final String USER = "rxikhsae";
    static final String PASS = "hrdIjtZaLpr4ynLFo1nwVxtoPfL-MJdp";

    private TableView<ProcInfo> table;
    private ObservableList<ProcInfo> data;

    TableView<ProcInfo> tab = new TableView();
    TableView.TableViewSelectionModel tsm = tab.getSelectionModel();

    //metodo per command
    public void placeDelete(Delete delete){

        queue.add(delete);
        delete.executeAction(id);

    }

    TableColumn IDColumn = new TableColumn("ID (timestamp)");

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


        tsm.setSelectionMode(SelectionMode.MULTIPLE);


        getData();





//        border.setCenter(tv);


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


            data = FXCollections.observableArrayList();

            // iterate through the java resultset
            while (rs.next()) {
                IDList.add(rs.getInt("ID"));
//                idProc = rs.getArray("idProc");
                arrivalList.add(rs.getArray("ArrivalTime"));
                burstList.add(rs.getArray("Burst"));
//                contextSwitch.add(rs.getInt("contextSwitch"));
                System.out.println("QUI");


                data.add(new ProcInfo(rs.getInt(1), rs.getString(2), rs.getString(3)/*, rs.getInt(5)*/));


                System.out.println("QUI");
            }



            IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

            TableColumn arrivalColumn = new TableColumn("Arrival Time");
            arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("ArrivalTime"));

            TableColumn burstColumn = new TableColumn("Burst");
            burstColumn.setCellValueFactory(new PropertyValueFactory<>("Burst"));

//            TableColumn contextSwitchColumn = new TableColumn("Context Switch");
//            contextSwitchColumn.setCellValueFactory(new PropertyValueFactory<>("contextSwitch"));


            tab.getColumns().addAll(IDColumn, arrivalColumn, burstColumn /*, contextSwitchColumn*/);

            tab.getItems().addAll(data);










            border.setCenter(tab);

            for (int i = 0; i < IDList.size(); i++){
                System.out.println("ID "+IDList.get(i)+"  arr "+arrivalList.get(i));
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
////        return tableView;
//    }



//
//
//        return tableView;
//    }


    public Button addDeleteButton(){

        Button deleteB = new Button("Delete");
        deleteB.setAlignment(Pos.TOP_RIGHT);

        deleteB.setOnAction(actionEvent -> {
            System.out.println("delete pressed");

            deleteRow();

        });

        return deleteB;
    }

    public void deleteRow(){
        if (tsm.isEmpty()) System.out.println("Please select a row to delete");

        var selectedItem = tab.getSelectionModel().getSelectedItem();
        int IDtoDelete = tab.getSelectionModel().getSelectedItem().getID();
        deleteDBRow(IDtoDelete);
        tab.getItems().remove(selectedItem);
        System.out.println("removed "+ IDtoDelete);


    }

    public void deleteDBRow(int IDtoDelete){

        try {

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // our SQL SELECT query.
            String query = "DELETE FROM process WHERE \"ID\" = '"+IDtoDelete+"' ";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            st.executeUpdate(query);

        }  catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
    }





    public Stage getRecordStageStage() {
        return recordStage;
    }

}
