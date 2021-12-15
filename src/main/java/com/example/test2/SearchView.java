package com.example.test2;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.util.Map;

public class SearchView extends BaseView{
    private String title = "Bus";

    private AnchorPane pane = null;

    private HBox hBox;

    private VBox vBox;

    private Button searchButton;
    private TextField busField;


    private TextArea busText;

    SearchService service = new SearchServiceImpl();

    private final EventHandler<ActionEvent> searchEvent = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            if (searchButton == event.getSource()) {
                String GaragNumb = busField.getText();

                StringBuilder jsonString = new StringBuilder(service.getBusJson("http://data.kzn.ru:8082/api/v0/dynamic_datasets/bus/" + GaragNumb + ".json"));
                Map<String, Object> json = null;
                try {
                    json = service.parseGson(jsonString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                busText.setText("");
                for (String header : json.keySet()) {
                    busText.setText(header + ": " + json.get(header) + "\n" + busText.getText());
                }
                busText.setText(busText.getText(0, busText.getText().length() - 1));
            }
        }
    };

    public Parent getView() {
        if (pane == null) {
            this.createView();
        }

        return pane;
    }

    @Override
    public String getTitle() {
        return title;
    }

    private void createView() {
        pane = new AnchorPane();

        hBox = new HBox(5);
        vBox = new VBox(5);

        searchButton = new Button("search");
        searchButton.setOnAction(searchEvent);
        busField = new TextField();
        busField.setPromptText("Input GaragNumb");

        busText = new TextArea();
        busText.setEditable(false);
        busText.setWrapText(true);
        busText.setMaxWidth(205);
        busText.setMinWidth(205);
        hBox.getChildren().addAll(busField, searchButton);

        vBox.getChildren().addAll(hBox, busText);

        pane.getChildren().add(vBox);

        AnchorPane.setTopAnchor(vBox, 5.0);
        AnchorPane.setLeftAnchor(vBox, 10.0);
        AnchorPane.setRightAnchor(vBox, 10.0);
    }
}

