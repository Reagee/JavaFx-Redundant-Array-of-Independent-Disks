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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.raid.*;

import javax.xml.crypto.Data;
import java.util.Random;

public class Main extends Application {

    private Label title = new Label("Tablice dysków nadmiarowych");
    private Label title2 = new Label("RAID 3");
    private Label inputData = new Label("Dane wejściowe");
    private Label outputData = new Label("Dane wyjściowe po operacji MERGE");
    private Label singleData = new Label("Dane podzielone na macierze RAID");
    private Label parityBit = new Label("Bity parzystości");
    private Label errorMsg = new Label("");

    private Button changeRandomBit = new Button("Zamień 1 losowy bit");
    private Button reverseArr = new Button("Odwróć bity losowego dysku");
    private Button changeMultipleBits = new Button("Zmień kilka bitów");
    private Button generateReportToFile = new Button("Generuj raport do pliku");
    private Button getFromFiles = new Button("Wypełnij gotowymi danymi");
    private Button confirmChanges = new Button("Zastosuj zmiany");
    private Button addNewDisc = new Button("Dodaj dysk");
    private Button clear = new Button("Wyczysc dane");

    private TextArea input = new TextArea("");
    private TextArea outputMerged = new TextArea("");
    private TextArea matrixOne = new TextArea("");
    private TextArea matrixTwo = new TextArea("");
    private TextArea parityBits = new TextArea("");
    private TextArea additionalDisc = new TextArea("");

    boolean randomBit = false, fewBits = false, reverse = false, anotherDisc = false;

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
        FileOpener4Discs fileOpener4Discs = new FileOpener4Discs();

        changeMultipleBits.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                input.setEditable(true);
                fewBits = true;
                randomBit = false;
                reverse = false;
            }
        });

        generateReportToFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                input.setEditable(false);
                if(!checkInputData()){
                    input.setText(fileOpener.getDataOutput());
                    outputMerged.setText("Podaj tylko bity");
                }
                else {
                    if(!anotherDisc) {
                        errors.check(outputData.getText(),parityBits.getText());
                        fileOpener.generateResult(input.getText(), matrixOne.getText(), matrixTwo.getText(), parityBits.getText(), errors.damagedBit);
                        errors.damagedBit = "";
                    }
                    else{
                        errors.check(outputData.getText(),parityBits.getText());
                        fileOpener4Discs.generateResultWithAdditionalDisc(input.getText(),matrixOne.getText(),matrixTwo.getText(),additionalDisc.getText(),parityBits.getText(),errors.damagedBit);
                        errors.damagedBit = "";
                    }
                }
            }
        });

        changeRandomBit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                input.setText(errors.changeRandomBit(input.getText()));
                randomBit = true;
                fewBits = false;
                reverse = false;
            }
        });

        reverseArr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reverse = true;
                fewBits = false;
                randomBit = false;
                if (!anotherDisc) {
                    Random gen = new Random();
                    int i = gen.nextInt(2) + 1;
                    System.out.println(i);
                    if (i == 1 && !matrixOne.getText().equals(null) && !matrixOne.getText().isEmpty())
                        matrixOne.setText(errors.rotateArr(matrixOne.getText()));
                    else if (i == 2 && !matrixTwo.getText().equals(null) && !matrixTwo.getText().isEmpty())
                        matrixTwo.setText(errors.rotateArr(matrixTwo.getText()));
                    else if (i == 1 && (matrixOne.getText().equals(null) || matrixOne.getText().isEmpty())) {
                        matrixOne.setText("Brak danych w dysku nr.1");
                    } else if (i == 2 && (matrixTwo.getText().equals(null) || matrixTwo.getText().isEmpty())) {
                        matrixTwo.setText("Brak danych w dysku nr.2");
                    }
                }
                else{
                    Random gen = new Random();
                    int i = gen.nextInt(3) + 1;
                    System.out.println(i);
                    if (i == 1 && !matrixOne.getText().equals(null) && !matrixOne.getText().isEmpty())
                        matrixOne.setText(errors.rotateArr(matrixOne.getText()));
                    else if (i == 2 && !matrixTwo.getText().equals(null) && !matrixTwo.getText().isEmpty())
                        matrixTwo.setText(errors.rotateArr(matrixTwo.getText()));
                    else if (i == 3 && !additionalDisc.getText().equals(null) && !additionalDisc.getText().isEmpty())
                        additionalDisc.setText(errors.rotateArr(additionalDisc.getText()));
                    else if (i == 1 && (matrixOne.getText().equals(null) || matrixOne.getText().isEmpty())) {
                        matrixOne.setText("Brak danych w dysku nr.1");
                    } else if (i == 2 && (matrixTwo.getText().equals(null) || matrixTwo.getText().isEmpty())) {
                        matrixTwo.setText("Brak danych w dysku nr.2");
                    } else if (i == 3 && (additionalDisc.getText().equals(null)) || additionalDisc.getText().isEmpty()){
                        additionalDisc.setText("Brak danych na dodatkowym dysku nr.3");
                    }
                }
            }
        });

        getFromFiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                input.setEditable(false);
                if(checkInputData()){
                    if(!anotherDisc) {
                        fileOpener.readData();

                        input.setText(fileOpener.getDataOutput());
                        matrixOne.setText(fileOpener.getDataOne());
                        matrixTwo.setText(fileOpener.getDataTwo());
                        parityBits.setText(fileOpener.getParityBits());
                        outputMerged.setText(fileOpener.getDataOutput());
                    }
                    else{
                        fileOpener4Discs.readDataWithAdditionalDisc();

                        input.setText(fileOpener4Discs.getDataOutput());
                        matrixOne.setText(fileOpener4Discs.getDataOne());
                        matrixTwo.setText(fileOpener4Discs.getDataTwo());
                        additionalDisc.setText(fileOpener4Discs.getDataThree());
                        parityBits.setText(fileOpener4Discs.getParityBits());
                        outputMerged.setText(fileOpener4Discs.getDataOutput());
                    }
                }else{
                    input.setText(fileOpener.getDataOutput());
                    outputMerged.setText("Podaj tylko bity");
                }
            }
        });

        confirmChanges.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (input.getText().length() % 16 != 0 && input.getText().length() % 24 != 0) {
                    outputMerged.setText("Niepoprawna dlugosc danych wejsciowych.");
                } else {
                    errors.damagedBit = "";
                    if (!anotherDisc) {
                        if (fewBits || randomBit) {
                            DataOperator.overrideAllData(input.getText());
                        } else if (reverse) {
                            DataOperator.overideOutputData(input.getText(), matrixOne.getText(), matrixTwo.getText());
                        }
                        if (DataOperator.isDataFlag() && (fewBits || randomBit)) {
                            matrixOne.setText(DataOperator.getDataOne());
                            matrixTwo.setText(DataOperator.getDataTwo());
                            parityBits.setText(DataOperator.getParityBits());
                            outputMerged.setText(DataOperator.getDataOutput());
                        } else if (DataOperator.isDataFlag() && reverse) {
                            parityBits.setText(DataOperator.getParityBits());
                            outputMerged.setText(DataOperator.getDataOutput());

                        } else {
                            input.setText(DataOperator.getInput());
                            matrixOne.setText("");
                            matrixTwo.setText("");
                            parityBits.setText("");
                            outputMerged.setText("");
                        }
                    } else {
                        if (fewBits || randomBit) {
                            DataOperator4Discs.overrideAllDataWithAdditionalDisc(input.getText());
                        } else if (reverse) {
                            DataOperator4Discs.overideOutputDataWithAdditionalDisc(input.getText(), matrixOne.getText(), matrixTwo.getText(), additionalDisc.getText());
                        }
                        if (DataOperator4Discs.isDataFlag() && (fewBits || randomBit)) {
                            matrixOne.setText(DataOperator4Discs.getDataOne());
                            matrixTwo.setText(DataOperator4Discs.getDataTwo());
                            additionalDisc.setText(DataOperator4Discs.getDataThree());
                            parityBits.setText(DataOperator4Discs.getParityBits());
                            outputMerged.setText(DataOperator4Discs.getDataOutput());
                        } else if (DataOperator4Discs.isDataFlag() && reverse) {
                            parityBits.setText(DataOperator4Discs.getParityBits());
                            outputMerged.setText(DataOperator4Discs.getDataOutput());

                        } else {
                            input.setText(DataOperator.getInput());
                            matrixOne.setText("");
                            matrixTwo.setText("");
                            additionalDisc.setText("");
                            parityBits.setText("");
                            outputMerged.setText("");
                        }
                    }
                    errors.check(outputData.getText(), parityBits.getText());
                    errorMsg.setText("Uszkodzone bity na pozycjach: " + errors.damagedBit);
                    errorMsg.setTextFill(Color.RED);
                    DataOperator4Discs.resetValues();
                }
            }
        });

        addNewDisc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!anotherDisc){
                    anotherDisc = true;
                    additionalDisc.setVisible(true);
                    addNewDisc.setText("Usuń dodatkowy dysk");
                }
                else{
                    anotherDisc = false;
                    additionalDisc.setVisible(false);
                    addNewDisc.setText("Dodaj dysk");
                }
            }
        });

        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    input.setText("");
                    matrixOne.setText("");
                    matrixTwo.setText("");
                    outputMerged.setText("");
                    parityBits.setText("");
             if(anotherDisc)
                    additionalDisc.setText("");
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
        additionalDisc.setFont(Font.font("Arial",15));

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
        additionalDisc.setEditable(false);
        additionalDisc.setPrefRowCount(1);
        additionalDisc.setVisible(false);

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
        confirmChanges.setPrefWidth(400);
        confirmChanges.setPrefHeight(30);
        addNewDisc.setPrefWidth(400);
        addNewDisc.setPrefHeight(30);
        clear.setPrefWidth(400);
        clear.setPrefHeight(30);

        changeRandomBit.setFont(Font.font("Arial",15));
        reverseArr.setFont(Font.font("Arial",15));
        changeMultipleBits.setFont(Font.font("Arial",15));
        generateReportToFile.setFont(Font.font("Arial",15));
        getFromFiles.setFont(Font.font("Arial",15));
        confirmChanges.setFont(Font.font("Arial",15));
        addNewDisc.setFont(Font.font("Arial",15));
        clear.setFont(Font.font("Arial",15));

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
        raid.add(additionalDisc,1,9);
        raid.add(changeRandomBit,1,10);
        raid.add(reverseArr,2,10);
        raid.add(changeMultipleBits,3,10);
        raid.add(generateReportToFile,1,11);
        raid.add(getFromFiles,2,11);
        raid.add(confirmChanges,3,11);
        raid.add(addNewDisc,4,10);
        raid.add(clear,4,11);
        raid.add(errorMsg,1,12);

        Scene scene = new Scene(raid,1200,480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
