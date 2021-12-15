package com.example.test2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class App extends Application {
    Stage stage;

    private Scene scene;

    private BorderPane rootLayout;

    private SearchView searchView;

    public SearchView getWeatherSearchView() {
        return searchView;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.setOnCloseRequest(e -> System.exit(0));

        this.searchView = new SearchView();

        this.initLayout();
    }

    private void initLayout() {
        rootLayout = new BorderPane();

        scene = new Scene(rootLayout, 230, 230);
        stage.setScene(scene);
        stage.show();

        this.setView(getWeatherSearchView());
    }

    public void setView(BaseView view) {
        this.stage.setTitle(view.getTitle());
        rootLayout.setCenter(view.getView());
    }
}

