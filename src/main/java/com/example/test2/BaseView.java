package com.example.test2;

import javafx.scene.Parent;

public abstract class BaseView {

    private static App application;

    public abstract Parent getView();

    public static void setApplication(App application) {
        BaseView.application = application;
    }

    public static App getApplication() throws Exception {
        if (application != null) {
            return application;
        }
        throw new Exception("No Application in BaseView");
    }

    public abstract String getTitle();
}

