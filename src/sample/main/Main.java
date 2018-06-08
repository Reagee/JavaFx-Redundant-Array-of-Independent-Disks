package sample.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.raid.BitsManipulator;
import sample.raid.FileOpener;

import java.util.Random;

public class Main extends Application {

    private Label title = new Label("Tablice dysków nadmiarowych");
    private Label title2 = new Label("RAID 3");
    private Label inputData = new Label("Dane wejściowe");
    private Label outputData = new Label("Dane wyjściowe po operacji MERGE");
    private Label singleData = new Label("Dane podzielone na macierze RAID");
    private Label parityBit = new Label("Bity parzystości");

    private Button changeRandomBit = new Button("Zamień 1 losowy bit");
    private Button reverseArr = new Button("Odwróć bity losowego dysku");
    private Button changeMultipleBits = new Button("Zmień kilka bitów");
    private Button generateReportToFile = new Button("Generuj raport do pliku");
    private Button getFromFiles = new Button("Nadpisz dane gotowymi");

    private TextArea input = new TextArea("");
    private TextArea outputMerged = new TextArea("");
    private TextArea matrixOne = new TextArea("");
    private TextArea matrixTwo = new TextArea("");
    private TextArea parityBits = new TextArea("");

    private BitsManipulator errors = new BitsManipulator();

    private Boolean checkInputData(){
        for(int i = 0;i < input.getText().length(); i++){
            if(input.getText().charAt(i) != '0' && input.getText().charAt(i) != '1')
                return false;
        }
        return true;
    }

    public Main(){
        FileOpener fileOpener = new FileOpener();
        input.setText(fileOpener.getData());
        parityBits.setText(fileOpener.getBit());

        changeMultipleBits.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                input.setEditable(true);
            }
        });

        generateReportToFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                input.setEditable(false);
                if(!checkInputData()){
                    input.setText(fileOpener.getData());
                    outputMerged.setText("Podaj tylko bity");
                }
                else {
                    fileOpener.generateResult(input.getText(), matrixOne.getText(), matrixTwo.getText(), parityBits.getText(), errors.damagedBit);
                }
            }
        });

        changeRandomBit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                input.setText(errors.changeRandomBit(input.getText()));
            }
        });

        reverseArr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Random gen = new Random();
                int i = gen.nextInt(2)+1;
                System.out.println(i);
                if(i==1 && !matrixOne.getText().equals(null) && !matrixOne.getText().isEmpty())
                    matrixOne.setText(errors.rotateArr(matrixOne.getText()));
                else if(i==2 && !matrixTwo.getText().equals(null) && !matrixTwo.getText().isEmpty())
                    matrixTwo.setText(errors.rotateArr(matrixTwo.getText()));
                else if(i==1 && (matrixOne.getText().equals(null) || matrixOne.getText().isEmpty())){
                    matrixOne.setText("Brak danych w dysku nr.1");
                }
                else if(i==2 && (matrixTwo.getText().equals(null) || matrixTwo.getText().isEmpty())){
                    matrixTwo.setText("Brak danych w dysku nr.2");
                }
            }
        });

        getFromFiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                input.setEditable(false);
                if(checkInputData()){
                    fileOpener.readData();

                    input.setText(fileOpener.getData());
                    matrixOne.setText(fileOpener.getDataOne());
                    matrixTwo.setText(fileOpener.getDataTwo());
                    parityBits.setText(fileOpener.getBit());
                    outputMerged.setText(fileOpener.getData());
                }else{
                    input.setText(fileOpener.getData());
                    outputMerged.setText("Podaj tylko bity");
                }
            }
        });
    }

    @Override
    public void start(Stage primaryStage){
        title.setAlignment(Pos.CENTER_RIGHT);
        title2.setAlignment(Pos.CENTER_LEFT);
        title.setFont(Font.font("Arial",28));
        title.setPadding(new Insets(0,0,20,0));
        title2.setFont(Font.font("Arial",28));
        title2.setPadding(new Insets(0,0,20,0));

        inputData.setFont(Font.font("Arial",15));
        outputData.setFont(Font.font("Arial",15));
        singleData.setFont(Font.font("Arial",15));
        parityBit.setFont(Font.font("Arial",15));

        input.setEditable(false);
        input.setPrefRowCount(3);
        outputMerged.setEditable(false);
        outputMerged.setPrefRowCount(3);
        matrixOne.setEditable(false);
        matrixOne.setPrefRowCount(1);
        matrixTwo.setEditable(false);
        matrixTwo.setPrefRowCount(1);
        parityBits.setEditable(false);
        parityBits.setPrefRowCount(1);

        changeRandomBit.setPrefWidth(400);
        changeRandomBit.setPrefHeight(30);
        reverseArr.setPrefWidth(400);
        reverseArr.setPrefHeight(30);
        changeMultipleBits.setPrefWidth(400);
        changeMultipleBits.setPrefHeight(30);
        generateReportToFile.setPrefWidth(400);
        generateReportToFile.setPrefHeight(30);
        getFromFiles.setPrefWidth(400);
        getFromFiles.setPrefHeight(30);

        changeRandomBit.setFont(Font.font("Arial",15));
        reverseArr.setFont(Font.font("Arial",15));
        changeMultipleBits.setFont(Font.font("Arial",15));
        generateReportToFile.setFont(Font.font("Arial",15));
        getFromFiles.setFont(Font.font("Arial",15));

        primaryStage.setTitle("RAID");
        GridPane raid = new GridPane();
        raid.setAlignment(Pos.TOP_LEFT);
        raid.setHgap(10);
        raid.setVgap(10);
        raid.setPadding(new Insets(10,10,10,10));
        raid.add(title,1,1);
        raid.add(title2,2,1);
        raid.add(inputData,1,2);
        raid.add(outputData,2,2);
        raid.add(input,1,3);
        raid.add(outputMerged,2,3);
        raid.add(singleData,1,6);
        raid.add(matrixOne,1,7);
        raid.add(matrixTwo,1,8);
        raid.add(parityBit,2,6);
        raid.add(parityBits,2,7);

        raid.add(changeRandomBit,1,9);
        raid.add(reverseArr,2,9);
        raid.add(changeMultipleBits,1,10);
        raid.add(generateReportToFile,2,10);
        raid.add(getFromFiles,3,9);

        Scene scene = new Scene(raid,1200,420);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
