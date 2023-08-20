package com.example.demo2048;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 800);

        Button R00 = new Button();
        R00.setLayoutX(200);
        R00.setLayoutY(200);

        Button R01 = new Button();
        R01.setLayoutX(280);
        R01.setLayoutY(200);

        Button R02 = new Button();
        R02.setLayoutX(360);
        R02.setLayoutY(200);

        Button R03 = new Button();
        R03.setLayoutX(440);
        R03.setLayoutY(200);

        Button R10 = new Button();
        R10.setLayoutX(200);
        R10.setLayoutY(280);

        Button R11 = new Button();
        R11.setLayoutX(280);
        R11.setLayoutY(280);

        Button R12 = new Button();
        R12.setLayoutX(360);
        R12.setLayoutY(280);

        Button R13 = new Button();
        R13.setLayoutX(440);
        R13.setLayoutY(280);

        Button R20 = new Button();
        R20.setLayoutX(200);
        R20.setLayoutY(360);

        Button R21 = new Button();
        R21.setLayoutX(280);
        R21.setLayoutY(360);

        Button R22 = new Button();
        R22.setLayoutX(360);
        R22.setLayoutY(360);

        Button R23 = new Button();
        R23.setLayoutX(440);
        R23.setLayoutY(360);

        Button R30 = new Button();
        R30.setLayoutX(200);
        R30.setLayoutY(440);

        Button R31 = new Button();
        R31.setLayoutX(280);
        R31.setLayoutY(440);

        Button R32 = new Button();
        R32.setLayoutX(360);
        R32.setLayoutY(440);

        Button R33 = new Button();
        R33.setLayoutX(440);
        R33.setLayoutY(440);

        Rectangle rectangle = new Rectangle(100, 100, Color.BLUE);
        rectangle.setX(250);
        rectangle.setY(90);

        Text text = new Text("Congratulations!!!");
        text.setFont(Font.font("Arial", 20));
        text.setFill(Color.GREEN);
        text.setX(250);
        text.setY(90);

        List<Button> buttonList = Arrays.asList(R00, R01, R02, R03, R10, R11, R12, R13, R20, R21, R22, R23, R30, R31, R32, R33);
        for (Button button : buttonList){
            button.setPrefSize(75, 75);
            button.setText("");
        }

        for (int i = 0; i < 4; i++) {
            Button button = buttonList.get(ThreadLocalRandom.current().nextInt(0, buttonList.size()));
            if(button.getText().equals("")){
                button.setText("2");
                button.setStyle("-fx-font-size:20");
            }else{
                i--;
            }
        }




        Button buttons[][] = {{R00, R01, R02, R03},
                              {R10, R11, R12, R13},
                              {R20, R21, R22, R23},
                              {R30, R31, R32, R33}};

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = changeButtoStyle(buttons[i][j]);
            }
        }

        KeyCode cheatKeys[] = new KeyCode[10];

        scene.setOnKeyPressed((KeyEvent event) -> { // Create a key event that execute when any key pressed from your keyboard
            KeyCode keyCode = event.getCode();  // The actions that we are going to take when a key pressed. In our case we are changing the label text to "Key press detected !!!"
            if(keyCode == KeyCode.A){
                shiftToTheLeft(summingArray(shiftToTheLeft(buttons, true)), false);
                replaceClickedButtons(cheatKeys, KeyCode.A);
            }

            if(keyCode == KeyCode.W){
                shiftToTop(summingArrayToTop(shiftToTop(buttons, true)), false);
                replaceClickedButtons(cheatKeys, KeyCode.W);
            }

            if(keyCode == KeyCode.S){
                shiftToBottom(summingArrayToBottom(shiftToBottom(buttons, true)), false);
                replaceClickedButtons(cheatKeys, KeyCode.S);
            }

            if(keyCode == KeyCode.D){
                shiftToTheRight(summingArrayToRight(shiftToTheRight(buttons, true)), false);
                replaceClickedButtons(cheatKeys, KeyCode.D);
            }

            if(cheatCodeVerification(cheatKeys)){
                activateEasyWin(buttonList);
            }

            if(gameOverVerification(buttonList)){
                root.getChildren().addAll( text);
            }
            try {
                if(keyCode != cheatKeys[cheatKeys.length - 2]) {
                    fillRandomButton(buttons);
                }
            }catch (IllegalArgumentException ex){
                /*text.setText("Game OVER loser!");
                text.setFill(Color.RED);
                root.getChildren().addAll( text);*/
            }
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    buttons[i][j] = changeButtoStyle(buttons[i][j]);
                }
            }
            if(isEmptySquaresPresent(buttons) && isGameOver(buttons)){
                text.setText("Game OVER loser!");
                text.setFill(Color.RED);
                root.getChildren().addAll( text);
            }
        });
        root.getChildren().addAll(R00, R01, R02, R03, R10, R11, R12, R13, R20, R21, R22, R23, R30, R31, R32, R33);
        stage.setScene(scene);
        stage.show();
    }

    private void fillRandomButton(Button[][] changed) {
        List<Button> list = new ArrayList<Button>();
        for (int i = 0; i < changed.length; i++) {
            for (int j = 0; j < changed[i].length; j++) {
                if(changed[i][j].getText().equals("")){
                    list.add(changed[i][j]);
                }
            }
        }
        Random random = new Random();
        Button butt = list.get(random.nextInt((list.size() - 1 - 0) + 1));
        butt.setText("2");
        butt.setStyle("-fx-font-size:20");
    }

    public static void main(String[] args) {
        launch();
    }

    static private boolean cheatCodeVerification(KeyCode[] keys){
        KeyCode[] cheat = {KeyCode.A, KeyCode.W, KeyCode.W, KeyCode.W, KeyCode.W, KeyCode.D, KeyCode.S, KeyCode.S, KeyCode.S, KeyCode.S};
        for (int i = 0; i < cheat.length; i++) {
            if(!cheat[i].equals(keys[i])){
                return false;
            }
        }
        return true;
    }

    static private void activateEasyWin(List<Button> buttonList){
                for (Button button : buttonList){
                    button.setText("");
                }
                buttonList.get(0).setText("1024");
                buttonList.get(1).setText("1024");
    }

    static private boolean gameOverVerification(List<Button> buttonList){
        for (Button button : buttonList){
            if(button.getText().equals("2048")){
                return true;
            }
        }
        return false;
    }

    static public Button[][] shiftToTheLeft(Button arr[][], boolean verify){
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr[i].length - 1; j >= 0; j--) {
                if(j > 0 && !arr[i][j].getText().equals("") && arr[i][j - 1].getText().equals("")){
                    arr[i][j - 1].setText(arr[i][j].getText());
                    arr[i][j - 1].setStyle("-fx-font-size:20");
                    arr[i][j].setText("");
                }
            }
        }

        if(verification(arr) && verify){
            shiftToTheLeft(arr, verify);
        }
        return arr;
    }

    private static boolean verification(Button[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(j < arr[i].length - 1 && arr[i][j].getText().equals("") && !arr[i][j + 1].getText().equals("")){
                    return true;
                }
            }
        }
        return false;
    }
    private static Button[][] summingArray(Button arr[][]){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    if (j < arr[i].length - 1 && arr[i][j].getText().equals(arr[i][j + 1].getText())) {
                        arr[i][j].setText(String.valueOf(Integer.parseInt(arr[i][j].getText()) + Integer.parseInt(arr[i][j + 1].getText())));
                        arr[i][j].setStyle("-fx-font-size:20");;
                        arr[i][j + 1].setText("");
                    }
                }catch (NumberFormatException ex){
                    continue;
                }
            }
        }
        return arr;
    }

    // Shift array to the top line
    static public Button[][] shiftToTop(Button arr[][], boolean verify){
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = arr[i].length - 1; j >= 0 ; j--) {
                if(j > 0 && !arr[j][i].getText().equals("") && arr[j - 1][i].getText().equals("")){
                    arr[j - 1][i].setText(arr[j][i].getText());
                    arr[j - 1][i].setStyle("-fx-font-size:20");;
                    arr[j][i].setText("");
                }
            }
        }

        if(verificationToTop(arr) && verify){
            shiftToTop(arr, verify);
        }
        return arr;
    }

    private static boolean verificationToTop(Button[][] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = arr[i].length - 1; j >= 0 ; j--) {
                if(j > 0 && i > 0 && arr[j - 1][i].getText().equals("") && !arr[j][i].getText().equals("")){
                    return true;
                }
            }
        }
        return false;
    }

    private static Button[][] summingArrayToTop(Button arr[][]){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    if (j < arr[i].length - 1 && arr[j][i].getText().equals(arr[j + 1][i].getText())) {
                        arr[j][i].setText(String.valueOf(Integer.parseInt(arr[j][i].getText()) + Integer.parseInt(arr[j + 1][i].getText())));
                        arr[j][i].setStyle("-fx-font-size:20");
                        arr[j + 1][i].setText("");
                    }
                }catch (NumberFormatException ex){
                    continue;
                }
            }
        }
        return arr;
    }

    /*
    * Shift numbers to the bottom line
     */

    static public Button[][] shiftToBottom(Button arr[][], boolean verify){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(j < arr[i].length - 1 && !arr[j][i].getText().equals("") && arr[j + 1][i].getText().equals("")){
                    arr[j + 1][i].setText(arr[j][i].getText());
                    arr[j + 1][i].setStyle("-fx-font-size:20");
                    arr[j][i].setText("");
                }
            }
        }

        if(verificationToBottom(arr) && verify){
            shiftToBottom(arr, verify);
        }
        return arr;
    }

    private static boolean verificationToBottom(Button[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(j < arr[i].length - 1 && arr[j + 1][i].getText().equals("") && !arr[j][i].getText().equals("")){
                    return true;
                }
            }
        }
        return false;
    }

    private static Button[][] summingArrayToBottom(Button arr[][]){
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = arr[i].length - 1; j >= 0; j--) {
                try {
                    if (j > 0  && arr[j][i].getText().equals(arr[j - 1][i].getText())) {
                        arr[j][i].setText(String.valueOf(Integer.parseInt(arr[j][i].getText()) + Integer.parseInt(arr[j - 1][i].getText())));
                        arr[j][i].setStyle("-fx-font-size:20");
                        arr[j - 1][i].setText("");
                    }
                }catch (NumberFormatException ex){
                    continue;
                }
            }
        }
        return arr;
    }

    /*
     * Shift numbers to the right border
     */

    static public Button[][] shiftToTheRight(Button arr[][], boolean verify){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(j < arr[i].length - 1 && !arr[i][j].getText().equals("") && arr[i][j + 1].getText().equals("")){
                    arr[i][j + 1].setText(arr[i][j].getText());
                    arr[i][j + 1].setStyle("-fx-font-size:20");
                    arr[i][j].setText("");
                }
            }
        }

        if(verificationToTheRight(arr) && verify){
            shiftToTheRight(arr, verify);
        }
        return arr;
    }

    private static boolean verificationToTheRight(Button[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(j < arr[i].length - 1 && !arr[i][j].getText().equals("") && arr[i][j + 1].getText().equals("")){
                    return true;
                }
            }
        }
        return false;
    }

    private static Button[][] summingArrayToRight(Button arr[][]){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                try {
                    if (j < arr[i].length - 1  && arr[i][j].getText().equals(arr[i][j + 1].getText())) {
                        arr[i][j + 1].setText(String.valueOf(Integer.parseInt(arr[i][j].getText()) + Integer.parseInt(arr[i][j + 1].getText())));
                        arr[i][j].setText("");
                    }
                }catch (NumberFormatException ex){
                    continue;
                }
            }
        }
        return arr;
    }

    private static Button changeButtoStyle(Button button){
        switch (button.getText()){
            case "":
                button.setStyle("-fx-background-color: #C5C5C5;-fx-font-size:20");
                return button;
            case "2":
                button.setStyle("-fx-background-color: #FFFF33;-fx-font-size:20");
                return button;
            case "4":
                button.setStyle("-fx-background-color: #FFE933;-fx-font-size:20");
                return button;
            case "8":
                button.setStyle("-fx-background-color: #FFD733;-fx-font-size:20");
                return button;
            case "16":
                button.setStyle("-fx-background-color: #FFC433;-fx-font-size:20");
                return button;
            case "32":
                button.setStyle("-fx-background-color: #FFB833;-fx-font-size:20");
                return button;
            case "64":
                button.setStyle("-fx-background-color: #FFA533;-fx-font-size:20");
                return button;
            case "128":
                button.setStyle("-fx-background-color: #FF8D33;-fx-font-size:20");
                return button;
            case "256":
                button.setStyle("-fx-background-color: #FF7733;-fx-font-size:20");
                return button;
        }
        return button;
    }

    private static KeyCode[] replaceClickedButtons(KeyCode[] keyCodes, KeyCode pressed){
        for (int i = 0; i < keyCodes.length - 1; i++) {
            keyCodes[i] = keyCodes[i + 1];
        }
        keyCodes[keyCodes.length - 1] = pressed;
        return keyCodes;
    }

    private static boolean isGameOver(Button[][] buttons){
        if(buttons[0][0].getText().equals(buttons[0][1].getText()) || buttons[0][0].getText().equals(buttons[1][0].getText()) ||
                buttons[0][buttons[0].length - 1].getText().equals(buttons[0][buttons[0].length - 2].getText()) ||
                buttons[0][buttons[0].length - 1].getText().equals(buttons[1][buttons[0].length - 1].getText()) ||
                buttons[buttons.length - 1][0].getText().equals(buttons[buttons.length - 2][0].getText()) ||
                buttons[buttons.length - 1][0].getText().equals(buttons[buttons.length - 1][1].getText()) ||
                buttons[buttons.length - 1][buttons[0].length - 1].getText().equals(buttons[buttons.length - 1][buttons[0].length - 2].getText()) ||
                buttons[buttons.length - 1][buttons[0].length - 1].getText().equals(buttons[buttons.length - 2][buttons[0].length - 1].getText())
        ){
            return false;
        }

        for (int i = 1; i < buttons.length - 1; i++) {
            for (int j = 1; j < buttons[i].length - 1; j++) {
                if(buttons[i][j].getText().equals(buttons[i - 1][j].getText()) || buttons[i][j].getText().equals(buttons[i][j - 1].getText())
                || buttons[i][j].getText().equals(buttons[i][j + 1].getText()) || buttons[i][j].getText().equals(buttons[i + 1][j].getText())){
                    return false;
                }

            }
        }
        return true;
    }

    private static boolean isEmptySquaresPresent(Button[][] buttons){
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if(buttons[i][j].getText().equals("")){
                    return false;
                }
            }
        }
        return true;
    }
}