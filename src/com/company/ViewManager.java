package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private Scene mainScene;
    private Stage mainStage;
    private SchedulingSubScene schedulingSubScene;
    protected BorderPane border = new BorderPane();

    public List<Process> processList = new ArrayList<>();

    public ViewManager(){

        mainStage = new Stage();


        //Represents the grid with Rectangles
        gridDisplay = new ViewManager.GridDisplay(processList.size());


        //Function to set an action when text field loses focus
        HBox hbox = addHBox();
        border.setTop(hbox);


        mainScene = new Scene(border,800,800);


        mainStage.setScene(mainScene);

    }



    private void addNewProcess() {

        // Handles routing makeCopy method calls to the right subclasses of Process
        ProcessFactory processMaker = new ProcessFactory();
        Process proces = new Process();
        // The first position (0) is for the "original", the clones follows
        Process clonedProcess = (Process) processMaker.getClone(proces);

        processList.add(clonedProcess);

    }


    //Class containing grid (see below)
    private GridDisplay gridDisplay;

    //Class responsible for displaying the grid containing the Rectangles (Panels)
    public class GridDisplay {

        private static final double ELEMENT_SIZE = 100;
        private static final double GAP = ELEMENT_SIZE / 10;

        private TilePane tilePane = new TilePane();
        private Group display = new Group(tilePane);
//        private int[] nCols[];

        public GridDisplay(int processNum) {
            tilePane.setStyle("-fx-background-color: rgba(255, 215, 0, 0);");
            tilePane.setHgap(GAP);
            tilePane.setVgap(GAP);
            setColumns(processNum);
        }

        public void setColumns(int processNum) {
            tilePane.setPrefColumns(processNum);
            createElements(processNum);
        }

        public Group getDisplay() {
            return display;
        }

        private void createElements(int processNum) {
            tilePane.getChildren().clear();
            for (int i = 0; i < processNum; i++) {
                tilePane.getChildren().add(createElement(i));
            }
        }

        //crea celle per settare processi
        private TilePane createElement(int i) {

            TilePane tileRect = new TilePane();
            tileRect.setHgap(10);
            tileRect.setVgap(10);
            tileRect.setMaxWidth(170);
            tileRect.setMinHeight(100);
            tileRect.setStyle("-fx-background-color: F96531; -fx-border-color: FFFFFF; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-radius: 5.0;");
            tileRect.setPadding(new Insets(10, 10, 10, 10));


            final Spinner<Integer> ATField = new Spinner<>();
            SpinnerValueFactory<Integer> ff = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, processList.get(i).getArrivalTime());
            ATField.setValueFactory(ff);
            ATField.setMaxWidth(60);

            ATField.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
                if(!"".equals(newValue))
                    processList.get(i).arrivalTime = Integer.parseInt(newValue);
            });


            Label ATLabel = new Label("Arrival");
            ATLabel.setTextFill(Color.WHITE);


            Label burstLabel = new Label("Burst");
            burstLabel.setTextFill(Color.WHITE);

            Label nproc = new Label("Process "+String.valueOf(i+1));
            nproc.setTextFill(Color.WHITE);


            tileRect.getChildren().addAll(nproc, ATLabel, ATField, burstLabel, addBurstField(i));

            return tileRect;
        }

    }



    private Spinner addBurstField(int i){

        var burstField = new Spinner<Integer>(); //vedere se per cambiare il range posso cambbiare solo una sola parte
        var burstFieldValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, processList.get(i).getBurst());
        burstField.setValueFactory(burstFieldValue);

//        burstField.setAlignment(Pos.CENTER_RIGHT);
        burstField.setMaxWidth(60);
        burstField.setLayoutX(100);


        burstField.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            if(!"".equals(newValue))
                processList.get(i).burst = Integer.parseInt(newValue);

        });

        return burstField;
    }

    private Button addScheduleButton(){

        Button scheduleButton = new Button("Schedule");
        scheduleButton.setPrefSize(100, 20);
        scheduleButton.setAlignment(Pos.CENTER);
        Fcfs al = new Fcfs();

        scheduleButton.setOnAction((event) -> {
            System.out.println(java.util.List.of(processList)); //prin
            al.schedule(processList);
            createSchedulingSubScene();
        });

        return scheduleButton;
    }



    private ComboBox addChooseSchedAlg(){
        ObservableList<String> options = FXCollections.observableArrayList(
                "FCFS", "SJF"

        );
        final ComboBox comboBox = new ComboBox(options);
        comboBox.getSelectionModel().selectFirst();

        return comboBox;
    }


    private CheckBox addPriorityCheckBox(){
        CheckBox priorityCheck = new CheckBox("Priority");
        priorityCheck.setTextFill(Color.WHITE);
        return priorityCheck;

    }

    private void createSchedulingSubScene(){

        schedulingSubScene = new SchedulingSubScene(processList);
        schedulingSubScene.setLayoutY(50);
        border.setBottom(schedulingSubScene);
        border.getChildren().add(schedulingSubScene);

    }


    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        //per allineare immissione n.processi e bottone Schedule
        VBox buttonVBox = new VBox();
        buttonVBox.setMaxWidth(170);
        buttonVBox.setMinWidth(150);

        HBox miniHBox = new HBox();

        var numProcessField = new Spinner<Integer>();

        Label ATLabel = new Label("Arrival");
        ATLabel.setTextFill(Color.WHITE);

        var ff = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10, 2);
        numProcessField.setValueFactory(ff);
        numProcessField.setMaxWidth(60);





        Process process = new Process();

        processList.add(process);
        addNewProcess();

        // DA OTTIMIZZARE
        gridDisplay.setColumns(processList.size());


        for (Process xxx : processList) {
            System.out.println("Arrival Time: " + xxx.getArrivalTime());

        }

        //Change the process panel's number when I change the spinner's value
        numProcessField.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {

            if(Integer.parseInt(newValue) > Integer.parseInt(oldValue))
                //create processes when I change
                addNewProcess();
            else
                processList.remove(processList.size()-1);

            System.out.println(java.util.List.of(processList));
            gridDisplay.setColumns(processList.size());
            System.out.println(" "+ java.util.List.of(processList));


            int index = 0;
            for (Process xxx : processList) {

                System.out.println("Arrival Time: " + xxx.getArrivalTime() + " - Burst Time: " + xxx.getBurst());

                index++;
            }


        });




        addScheduleButton().setLayoutY(numProcessField.getLayoutY()+100);

        miniHBox.getChildren().addAll(new Label("N. Proc: "), numProcessField);

        //add to the VBox buttons and combobox
        buttonVBox.getChildren().addAll(miniHBox, addChooseSchedAlg(), addPriorityCheckBox(), addScheduleButton());

        hbox.getChildren().addAll(buttonVBox, gridDisplay.getDisplay());


        return hbox;
    }


    public Stage getMainStage(){

        return mainStage;
    }



}
