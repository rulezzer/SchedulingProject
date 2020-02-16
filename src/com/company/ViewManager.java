package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private Scene mainScene;
    private Stage mainStage;
    private SchedulingSubScene schedulingSubScene;
    protected BorderPane border = new BorderPane();


    protected Iterator procIterator;
    protected ProcessCollections processCollections= new ProcessCollections();


    public List<Process> processList = processCollections.getList();
    private int contextSwitch = 1;
    private Schedule schedule = new Schedule();



    ObservableList<String> options = FXCollections.observableArrayList(
            "FCFS", "SJF"
    );

    final ComboBox<String> comboBox = new ComboBox<>(options);


    public ViewManager() throws FileNotFoundException {

        mainStage = new Stage();

        //Represents the grid with Rectangles
        gridDisplay = new ViewManager.GridDisplay(processList.size());

        //Function to set an action when text field loses focus
        HBox hbox = addHBox();
        HBox bottombox = addHBottomBox();
        border.setTop(hbox);
        border.setBottom(bottombox);

        mainScene = new Scene(border, 800, 500);

        mainStage.setScene(mainScene);

        final ImageView selectedImage = new ImageView();
        Image image1 = new Image(new FileInputStream("src/com/company/resources/accelerated-order.png"));

        selectedImage.setImage(image1);

        selectedImage.setScaleX(0.35);
        selectedImage.setScaleY(0.35);
//        selectedImage.setLayoutX(mainScene.getWidth()/2+selectedImage.getFitWidth());
//        selectedImage.setLayoutY(mainScene.getHeight()/2+selectedImage.getFitHeight());

//        selectedImage.layoutXProperty().bind(mainScene.widthProperty().subtract(selectedImage.fitWidthProperty()).divide(8));
//        selectedImage.layoutYProperty().bind(mainScene.heightProperty().subtract(selectedImage.fitHeightProperty()).divide(5).subtract(hbox.getHeight()));

        HBox himagebox = new HBox();
        himagebox.getChildren().add(selectedImage);
//        border.setCenter(himagebox);


//        mainScene.getChildren().add(selectedImage);
        himagebox.setMaxHeight(mainScene.getHeight());
        BorderPane.setAlignment(himagebox, Pos.TOP_CENTER);


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
            tileRect.setMaxWidth(150);
            tileRect.setMinHeight(100);
            tileRect.setStyle("-fx-background-color: F96531; -fx-border-color: FFFFFF; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-radius: 5.0;");
            tileRect.setPadding(new Insets(10, 10, 10, 10));

            Label nproc = new Label("Process " + String.valueOf(i + 1));
//            nproc.fontProperty(FontWeight.BOLD);
            nproc.setStyle("-fx-font-weight: bold");
            nproc.setTextFill(Color.WHITE);
            nproc.setAlignment(Pos.BASELINE_CENTER);

            Label atLabel = new Label("Arrival");
            atLabel.setTextFill(Color.WHITE);


            HBox atRow = new HBox();
            atRow.setSpacing(25);
            atRow.setAlignment(Pos.BASELINE_RIGHT);
            atRow.getChildren().addAll(atLabel, addATField(i));


            Label burstLabel = new Label("Burst");
            burstLabel.setTextFill(Color.WHITE);

            HBox burstRow = new HBox();
            burstRow.setSpacing(25);
            burstRow.setAlignment(Pos.BASELINE_RIGHT);

            burstRow.getChildren().addAll(burstLabel, addBurstField(i));

//            System.out.println(processList.get(i).getIdProc);
            tileRect.getChildren().addAll(nproc, atRow, burstRow);

            return tileRect;
        }

    }


    private Spinner addATField(int i) {

        final Spinner<Integer> atField = new Spinner<>();
        SpinnerValueFactory<Integer> ff = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, processList.get(i).getArrivalTime());
        atField.setValueFactory(ff);
        atField.setMaxWidth(60);
        atField.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            if (!"".equals(newValue))
                processList.get(processList.get(i).getIdProc()).setArrivalTime(Integer.parseInt(newValue), processList, i);
        });

        return atField;
    }

    private Spinner addBurstField(int i) {

        var burstField = new Spinner<Integer>(); //vedere se per cambiare il range posso cambbiare solo una sola parte
        var burstFieldValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, processList.get(i).getBurst());
        burstField.setValueFactory(burstFieldValue);

        burstField.setMaxWidth(60);
        burstField.setLayoutX(100);


        burstField.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {

            System.out.println("prima burst=" + processList.get(i).getBurst());
            System.out.println("arrivatimne=" + processList.get(i).getArrivalTime());
            System.out.println("Completion=" + processList.get(i).completion);


            if (!"".equals(newValue))
                processList.get(i).setBurst(Integer.parseInt(newValue), processList, i);

            System.out.println("indice " + i);
            System.out.println("dopo burst=" + processList.get(i).getBurst());
            System.out.println("arrivatimne=" + processList.get(i).getArrivalTime());

            System.out.println("Completion=" + processList.get(i).completion);
        });

        return burstField;
    }

    private Button addScheduleButton() {

        Button scheduleButton = new Button("Schedule");
        scheduleButton.setPrefSize(150, 20);
        scheduleButton.setAlignment(Pos.CENTER);
        comboBox.getSelectionModel().selectFirst();

        scheduleButton.setOnAction((event) -> {
            System.out.println(java.util.List.of(processList)); //print all processList


            System.out.println("Prova: " + comboBox.getValue());
            String value = comboBox.getValue();
            System.out.println("Prova: " + value);
            if (value == "FCFS") {

                schedule.setSchedulingStrategy("FCFS");
                System.out.println("Prova: " + comboBox.getValue());

            } else if (value == "SJF") {

                schedule.setSchedulingStrategy("SJF");
                System.out.println("Prova: " + comboBox.getValue());

            }


            schedule.execute(processCollections, contextSwitch);

            createSchedulingSubScene();

            averageWaitingTime.setText("Average Waiting Time: " + schedule.averageWaitingTime(processList));
            turnaroundLabel.setText("|  Average Turnaround: " + schedule.averageTurnaroundTime(processList));
            normalizedTurnaroundLabel.setText("|  Normalized Average Turnaround: " + schedule.averageTurnaroundNormalized(processList));

        });

        return scheduleButton;
    }

    private Spinner addCSField() {
        var CSField = new Spinner<Integer>();
        var cc = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2, 1); //first element is equal for all
        CSField.setValueFactory(cc);
        CSField.setMaxWidth(60);

        CSField.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            contextSwitch = Integer.valueOf(newValue);
        });

        return CSField;
    }



    private CheckBox addPriorityCheckBox() {
        CheckBox priorityCheck = new CheckBox("Priority");
        priorityCheck.setTextFill(Color.WHITE);

        priorityCheck.setDisable(true);


        return priorityCheck;
    }

    private void createSchedulingSubScene() {

        schedulingSubScene = new SchedulingSubScene(processCollections, contextSwitch);
        schedulingSubScene.setLayoutY(200);
        schedulingSubScene.setLayoutX(50);
        border.getChildren().add(schedulingSubScene);

    }


    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        hbox.setEffect(new DropShadow(2d, 0d, +2d, Color.LIGHTGREY));

        //per allineare immissione n.processi e bottone Schedule
        VBox buttonVBox = new VBox();
        buttonVBox.setMinWidth(135);

        HBox miniHBox = new HBox();
        HBox miniHBox2 = new HBox();
//        miniHBox.setAlignment(Pos.BASELINE_CENTER);

        var numProcessField = new Spinner<Integer>();
        var ff = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10, 2);
        numProcessField.setValueFactory(ff);
        numProcessField.setMaxWidth(60);
        //Change the process panel's number when I change the spinner's value
        numProcessField.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {

            if (Integer.parseInt(newValue) > Integer.parseInt(oldValue)) {

                //create processes when I change
                //addNewProcess();
                processCollections.addProc();

            } else {
                //processList.remove(processList.size() - 1);
                processCollections.removeProc();
            }
            //  System.out.println(java.util.List.of(processList));
            gridDisplay.setColumns(processCollections.getSize());
            //System.out.println(" " + java.util.List.of(processList));


            int index = 0;

            procIterator= processCollections.createIterator();
            Process process = new Process();
            while(procIterator.hasNext()){
                Process proc=(Process) procIterator.next();
                System.out.println("Arrival Time Iterator: " + proc.getArrivalTime() +" - Burst Time: " + proc.getBurst());
            }

        });

        // processList.add(process);
        //addNewProcess();

        // DA OTTIMIZZARE
        gridDisplay.setColumns(processCollections.getSize());

        addScheduleButton().setLayoutY(numProcessField.getLayoutY() + 100);

        miniHBox.setSpacing(10);
        miniHBox.setAlignment(Pos.BASELINE_RIGHT);

        miniHBox2.setSpacing(10);
        miniHBox2.setAlignment(Pos.BASELINE_RIGHT);


        HBox sAlRow = new HBox();
        sAlRow.setSpacing(10);
        sAlRow.setAlignment(Pos.BASELINE_RIGHT);
        sAlRow.setStyle("-fx-text-fill: white;");


        miniHBox.getChildren().addAll(new Label("N. Proc"), numProcessField);
        miniHBox2.getChildren().addAll(new Label("CS"), addCSField());
        sAlRow.getChildren().addAll(new Label("Algorithm"), comboBox);

        //add to the VBox buttons and combobox
        buttonVBox.setSpacing(10);
        buttonVBox.getChildren().addAll(miniHBox, miniHBox2, sAlRow, /*addPriorityCheckBox(),*/ addScheduleButton());

        hbox.getChildren().addAll(buttonVBox, gridDisplay.getDisplay());


        return hbox;
    }


    Label averageWaitingTime = new Label();
    Label turnaroundLabel = new Label();
    Label normalizedTurnaroundLabel = new Label();

    public HBox addHBottomBox() {
        HBox bottombox = new HBox();
        bottombox.setPadding(new Insets(15, 12, 15, 12));
        bottombox.setEffect(new DropShadow(5d, 20d, +5d, Color.BLACK));


        bottombox.setSpacing(10);
        bottombox.setStyle("-fx-background-color: #F9423A;");

        averageWaitingTime.setTextFill(Color.WHITE);
        normalizedTurnaroundLabel.setTextFill(Color.WHITE);
        averageWaitingTime.setText("Coded by Gennaro Caccavale, Davide Cangiano, Ivan Cifariello with  ❤");

        turnaroundLabel.setTextFill(Color.WHITE);


        final Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);

        Button openRecordView = new Button("History");
        openRecordView.setAlignment(Pos.BASELINE_RIGHT);
        openRecordView.setOnAction((event) -> {

            try {
                RecordTableView recordTableView = new RecordTableView();
                Stage recordStage = recordTableView.getRecordStageStage();
                recordStage.show();
                recordStage.setTitle("Scheduling Simulator History");
                recordStage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        });

        bottombox.getChildren().addAll(averageWaitingTime, turnaroundLabel, normalizedTurnaroundLabel, spacer, openRecordView);

        return bottombox;

    }


    public Stage getMainStage() {
        return mainStage;
    }


}
