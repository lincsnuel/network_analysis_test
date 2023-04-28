package nad.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import nad.Analysis.FileOperation;
import nad.Analysis.SetStyle;

import java.io.IOException;

public class MainController {

    @FXML
    private Button homeButton;

    @FXML
    private Button activitiesButton;

    @FXML
    private Button nadButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Pane mainPane;

    @FXML
    void initialize() {
        //remove later
        new FileOperation().deleteFile();

        homeButton.setStyle("-fx-background-color: #1D1C37; -fx-border-width: 0px 0px 0px 0px;");
        homeButton.requestFocus();
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/nad/fxml/home.fxml"));
            mainPane.getChildren().removeAll();
            mainPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }

        homeButton.setOnAction(actionEvent -> {
            homeButton.requestFocus();
            isFocusedView();
        });

        activitiesButton.setOnAction(actionEvent -> {
            activitiesButton.requestFocus();
            isFocusedView();
        });

        nadButton.setOnAction(actionEvent -> {
            nadButton.requestFocus();
            isFocusedView();
        });

        settingsButton.setOnAction(actionEvent -> {
            settingsButton.requestFocus();
            isFocusedView();
        });

    }


    //setup button views
    private void isFocusedView() {
        if (homeButton.isFocused()) {
            new SetStyle().setButtonStyle(activitiesButton, nadButton, homeButton);
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("/nad/fxml/home.fxml"));
                mainPane.getChildren().removeAll();
                mainPane.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (activitiesButton.isFocused()) {
            new SetStyle().setButtonStyle(homeButton, nadButton, activitiesButton);
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("/nad/fxml/activities.fxml"));
                mainPane.getChildren().removeAll();
                mainPane.getChildren().setAll(fxml);
                //new ActivitiesController().setTableFromFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (nadButton.isFocused()) {
            new SetStyle().setButtonStyle(homeButton, activitiesButton, nadButton);
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("/nad/fxml/nad.fxml"));
                mainPane.getChildren().removeAll();
                mainPane.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (settingsButton.isFocused()) {
            new SetStyle().settingsStyle(homeButton, activitiesButton, nadButton);
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("/nad/fxml/settings.fxml"));
                mainPane.getChildren().removeAll();
                mainPane.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
