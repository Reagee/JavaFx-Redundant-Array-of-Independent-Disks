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
import sample.raid.Error;
import sample.raid.FileOpener;

import javax.swing.text.StyledDocument;

public class Main extends Application {

    private Label title = new Label("Tablice dysków nadmiarowych");
    private Label title2 = new Label("RAID");
    private Label inputData = new Label("Dane wejściowe");
    private Label outputData = new Label("Dane wyjściowe po operacji MERGE");
    private Label singleData = new Label("Dane podzielone na macierze RAID");
    private Label parityBit = new Label("Bity parzystości");

    private Button changeRandomBit = new Button("Zamień 1 losowy bit");
    private Button reverseArr = new Button("Odwróć bity losowego dysku");
    private Button changeMultipleBits = new Button("Zmień kilka bitów");
    private Button confirm = new Button("Potwierdź");

    private TextArea input = new TextArea("");
    private TextArea outputMerged = new TextArea("");
    private TextArea matrixOne = new TextArea("");
    private TextArea matrixTwo = new TextArea("");
    private TextArea parityBits = new TextArea("");

    private Boolean checkInputData(){
        for(int i = 0;i < input.getText().length(); i++){
            if(input.getText().charAt(i) != '0' && input.getText().charAt(i) != '1')
                return false;
        }
        return true;
    }

    private void colorBits(String error){
        String temp = inputData.getText();

        int j = 0;
        int k = 0;
        for(int i=0;i<error.length();i++){
            if(input.getText().charAt(i) != error.charAt(i)){

            }
        }
    }

    private void colorParityBits(String error){

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

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                input.setEditable(false);
                Error errors = new Error();
                if(checkInputData()){
                    outputMerged.setText("");
                    matrixOne.setText("");
                    matrixTwo.setText("");
                    fileOpener.readData();
                    input.setText(fileOpener.getData());
                }else{
                    input.setText(fileOpener.getData());
                    outputMerged.setText("Podaj tylko bity");
                }

                parityBits.setText("");
                fileOpener.generateResult(input.getText(),matrixOne.getText(),matrixTwo.getText(),parityBits.getText(),errors.damagedBit);
            }
        });

        changeRandomBit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                outputMerged.setText("");
                matrixOne.setText("");
                matrixTwo.setText("");

                Error errors = new Error();
                String temp = input.getText();

                input.setText(errors.changeRandomBit(temp));
                fileOpener.readData();
                input.setText(fileOpener.getData());

                parityBits.setText("");

                fileOpener.generateResult(input.getText(),matrixOne.getText(),matrixTwo.getText(),parityBits.getText(),errors.damagedBit);
            }
        });

        reverseArr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                outputMerged.setText("");
                matrixOne.setText("");
                matrixTwo.setText("");

                Error errors = new Error();
                String temp = input.getText();

                input.setText(errors.rotateArr(temp));
                fileOpener.readData();
                input.setText(fileOpener.getData());

                parityBits.setText("");

                fileOpener.generateResult(input.getText(),matrixOne.getText(),matrixTwo.getText(),parityBits.getText(),errors.damagedBit);
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
        confirm.setPrefWidth(400);
        confirm.setPrefHeight(30);

        changeRandomBit.setFont(Font.font("Arial",15));
        reverseArr.setFont(Font.font("Arial",15));
        changeMultipleBits.setFont(Font.font("Arial",15));
        confirm.setFont(Font.font("Arial",15));

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
        raid.add(confirm,2,10);

        Scene scene = new Scene(raid,800,420);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
