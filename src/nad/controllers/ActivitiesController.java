package nad.controllers;

import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import nad.Analysis.Activity;
import nad.Analysis.FileOperation;
import nad.Analysis.ShowAlert;

public class ActivitiesController {

    @FXML
    private TextField activityTextField;

    @FXML
    private Button fillTable;

    @FXML
    private TextField crashTextField;

    @FXML
    private JFXToggleButton crashToggle;

    @FXML
    private JFXToggleButton OMPTime;

    @FXML
    private TableView<Activity> activityTable;

    @FXML
    private TableColumn<Activity, Number> idColumn;

    @FXML
    private TableColumn<Activity, String> activityColumn;

    @FXML
    private TableColumn<Activity, String> predecessorColumn;

    @FXML
    private TableColumn<Activity, Float> optimisticColumn;

    @FXML
    private TableColumn<Activity, Float> mostColumn;

    @FXML
    private TableColumn<Activity, Float> pessimisticColumn;

    @FXML
    private TableColumn<Activity, Float> durationColumn;

    @FXML
    private Button addData;

    @FXML
    private Button drawNAD;

    @FXML
    private Tab crashTab;

    private FileOperation file;

    @FXML
    void initialize() {
        file = new FileOperation();
        //hide some Nodes until needed
        crashTextField.setEditable(false);
        optimisticColumn.setVisible(false);
        mostColumn.setVisible(false);
        pessimisticColumn.setVisible(false);

        crashToggle.setOnAction(actionEvent -> {
            if (crashToggle.isSelected()) {
                crashTab.setDisable(false);
                crashTextField.setEditable(true);
            } else {
                crashTab.setDisable(true);
                crashTextField.setEditable(false);
            }
        });

        OMPTime.setOnAction(actionEvent -> {
            if (OMPTime.isSelected()) {
                optimisticColumn.setVisible(true);
                mostColumn.setVisible(true);
                pessimisticColumn.setVisible(true);
            } else {
                optimisticColumn.setVisible(false);
                mostColumn.setVisible(false);
                pessimisticColumn.setVisible(false);
                durationColumn.setVisible(false);
            }
            setTableFromFile();
        });

        // id, activity, predecessor, time, optimistic, mostLikely, pessimistic, duration;
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        activityColumn.setCellValueFactory(new PropertyValueFactory<>("activity"));
        predecessorColumn.setCellValueFactory(new PropertyValueFactory<>("predecessor"));
        optimisticColumn.setCellValueFactory(new PropertyValueFactory<>("optimistic"));
        mostColumn.setCellValueFactory(new PropertyValueFactory<>("mostLikely"));
        pessimisticColumn.setCellValueFactory(new PropertyValueFactory<>("pessimistic"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        editValues();
        activityTable.setEditable(true);

        //remove this and do when user opens a project in Account
        file.read();
        setTableFromFile();

        addData.setOnAction(actionEvent -> {
            //Write table data to the given file
            ObservableList<Activity> selectedItems = activityTable.getItems();
            if (selectedItems.isEmpty()) {
                new ShowAlert(Alert.AlertType.WARNING, "Cannot add, table is empty");
            } else {
                file.deleteFile(); //this will make the table not to +add
                selectedItems.forEach(selectedItem -> {
                    if (OMPTime.isSelected()) {
                        file.write(selectedItem.getId(), selectedItem.getActivity(),
                                selectedItem.getPredecessor(), selectedItem.getOptimistic(),
                                selectedItem.getMostLikely(), selectedItem.getPessimistic(),
                                selectedItem.getDuration());
                    } else {
                        file.write(selectedItem.getId(), selectedItem.getActivity(),
                                selectedItem.getPredecessor(), selectedItem.getDuration());
                    }
                });
                new ShowAlert(Alert.AlertType.CONFIRMATION, "Data added successfully");
            }
        });

//        drawNAD.setOnAction(actionEvent -> {
//            //try to load nad.fxml
//            try {
//                Parent fxml = FXMLLoader.load(getClass().getResource("/nad/fxml/nad.fxml"));
//                MainController.getMainPane().getChildren().removeAll();
//                MainController.getMainPane().getChildren().setAll(fxml);
//                new SetStyle().setButtonStyle(MainController.getHButton(),
//                        MainController.getAButton(), MainController.getNButton());
//                MainController.getNButton().requestFocus();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

        fillTable.setOnAction(actionEvent -> {
            if (activityTextField.getText().isEmpty()) {
                new ShowAlert(Alert.AlertType.WARNING, "Please enter a value!");
            } else {
                int txtVal = Integer.parseInt(activityTextField.getText());

                //load the new data supplied
                loadData(txtVal);
            }
        });
    }



    private void editValues() {
        predecessorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        predecessorColumn.setOnEditCommit(activityStringCellEditEvent ->
                activityStringCellEditEvent.getTableView().getItems().get(
                        activityStringCellEditEvent.getTablePosition().getRow())
                        .setPredecessor(activityStringCellEditEvent.getNewValue().toUpperCase()));

        durationColumn.setCellFactory(TextFieldTableCell.forTableColumn(
                new FloatStringConverter()
        ));
        durationColumn.setOnEditCommit(activityStringCellEditEvent -> {
            try {
                activityStringCellEditEvent.getTableView().getItems().get(
                        activityStringCellEditEvent.getTablePosition().getRow())
                        .setDuration(activityStringCellEditEvent.getNewValue());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        optimisticColumn.setCellFactory(TextFieldTableCell.forTableColumn(
                new FloatStringConverter()
        ));
        optimisticColumn.setOnEditCommit(activityStringCellEditEvent -> {
            try {
                activityStringCellEditEvent.getTableView().getItems().get(
                        activityStringCellEditEvent.getTablePosition().getRow())
                        .setOptimistic(activityStringCellEditEvent.getNewValue());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        mostColumn.setCellFactory(TextFieldTableCell.forTableColumn(
                new FloatStringConverter()
        ));
        mostColumn.setOnEditCommit(activityStringCellEditEvent -> {
            try {
                activityStringCellEditEvent.getTableView().getItems().get(
                        activityStringCellEditEvent.getTablePosition().getRow())
                        .setMostLikely(activityStringCellEditEvent.getNewValue());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        pessimisticColumn.setCellFactory(TextFieldTableCell.forTableColumn(
                new FloatStringConverter()
        ));
        pessimisticColumn.setOnEditCommit(activityStringCellEditEvent -> {
            try {
                activityStringCellEditEvent.getTableView().getItems().get(
                        activityStringCellEditEvent.getTablePosition().getRow())
                        .setPessimistic(activityStringCellEditEvent.getNewValue());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });
    }

    public void setTableFromFile() {
        //Fill the table with the saved file values read
        ObservableList<Activity> list = FXCollections.observableArrayList();

        for (int i = 0; i<file.getId().size(); i++) {
            if (OMPTime.isSelected()) {
                list.add(new Activity(file.getId().get(i), file.getActivity().get(i),
                        file.getPredecessor().get(i), file.getOptimistic().get(i)
                        , file.getMostLikely().get(i), file.getPessimistic().get(i),
                        file.getDuration().get(i)));
            } else {
                list.add(new Activity(file.getId().get(i), file.getActivity().get(i),
                        file.getPredecessor().get(i), file.getDuration().get(i)));
            }
        }

        activityTable.setItems(list);
    }

    private void loadData(int txtVal) {
        ObservableList<Activity> tableData = FXCollections.observableArrayList();

        char aChar = 65;
        char pChar = 65;

        for (int i = 1; i <= txtVal; i++) {
            if (i==1) {
                if (OMPTime.isSelected()) {
                    tableData.add(new Activity(i, String.valueOf(aChar++), "-", 0,
                            0, 0, 0));
                } else tableData.add(new Activity(i, String.valueOf(aChar++), "-", 0));
            } else if (i<27) {
                if (OMPTime.isSelected()) {
                    tableData.add(new Activity(i, String.valueOf(aChar++), String.valueOf(pChar++),
                            0, 0, 0, 0));
                } else tableData.add(new Activity(i, String.valueOf(aChar++), String.valueOf(pChar++), 0));
            } else {
                new ShowAlert(Alert.AlertType.ERROR, "Cannot handle activities higher" +
                        "than 26");
                break;
            }
        }

        activityTable.setItems(tableData);
    }
}
