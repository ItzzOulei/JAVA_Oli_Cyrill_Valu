package application.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class PasswordScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane rootPassword = new BorderPane();
        rootPassword.getStyleClass().add("root");
        ScrollPane scrollPassword = new ScrollPane();
        scrollPassword.setFitToHeight(true);
        scrollPassword.setFitToWidth(true);
        scrollPassword.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        rootPassword.setCenter(scrollPassword);

        // Create VBox for Center area
        VBox vboxCenter1 = new VBox();
        scrollPassword.setContent(vboxCenter1);
        vboxCenter1.setAlignment(Pos.CENTER);
        vboxCenter1.getStyleClass().add("login-VBox");
        vboxCenter1.setSpacing(0);
        vboxCenter1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        vboxCenter1.setPadding(new Insets(10, 10, 10, 10));

        // Create HBox for bottom area
        HBox hboxBottom1 = new HBox();
        rootPassword.setBottom(hboxBottom1);
        hboxBottom1.setAlignment(Pos.CENTER);
        hboxBottom1.getStyleClass().add("icons-bottom");
        hboxBottom1.setSpacing(40);
        hboxBottom1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        hboxBottom1.setPadding(new Insets(10, 10, 10, 10));

        // Add all password field, that the user has
        int userPasswordCount = 100;
        for (int i = 0; i < userPasswordCount; i++) {
            HBox hboxPasswordCenter = new HBox();
            Label labelPwdNb = new Label("Password " + (i + 1));
            hboxPasswordCenter.getChildren().add(labelPwdNb);
            vboxCenter1.getChildren().add(hboxPasswordCenter);
            hboxPasswordCenter.setAlignment(Pos.CENTER);
            hboxPasswordCenter.setPadding(new Insets(15, 10, 15, 10));
            hboxPasswordCenter.getStyleClass().add("pw-field");
        }

        // Add Icons
        Image imageHomeOnL = new Image(Objects.requireNonNull(getClass().getResource("/images/homeOff.png")).toExternalForm());
        ImageView imageHomeOffV = new ImageView(imageHomeOnL);
        imageHomeOffV.setFitHeight(25);
        imageHomeOffV.setFitWidth(25);
        hboxBottom1.getChildren().add(imageHomeOffV);

        Image imageKeyOffL = new Image(Objects.requireNonNull(getClass().getResource("/images/keyOn.png")).toExternalForm());
        ImageView imageKeyOffV = new ImageView(imageKeyOffL);
        imageKeyOffV.setFitHeight(25);
        imageKeyOffV.setFitWidth(25);
        hboxBottom1.getChildren().add(imageKeyOffV);

        Image imageAddOffL = new Image(Objects.requireNonNull(getClass().getResource("/images/addOff.png")).toExternalForm());
        ImageView imageAddOffV = new ImageView(imageAddOffL);
        imageAddOffV.setFitHeight(25);
        imageAddOffV.setFitWidth(25);
        hboxBottom1.getChildren().add(imageAddOffV);

        Image imageSettingsOffL = new Image(Objects.requireNonNull(getClass().getResource("/images/settingsOff.png")).toExternalForm());
        ImageView imageSettingsOffV = new ImageView(imageSettingsOffL);
        imageSettingsOffV.setFitHeight(25);
        imageSettingsOffV.setFitWidth(25);
        hboxBottom1.getChildren().add(imageSettingsOffV);

        // Add Pane for bigger Hitbox
        StackPane homeIconContainer = new StackPane();
        homeIconContainer.setPrefSize(50, 50);
        homeIconContainer.setAlignment(Pos.CENTER);
        homeIconContainer.getChildren().add(imageHomeOffV);
        hboxBottom1.getChildren().add(homeIconContainer);

        StackPane keyIconContainer = new StackPane();
        keyIconContainer.setPrefSize(50, 50);
        keyIconContainer.setAlignment(Pos.CENTER);
        keyIconContainer.getChildren().add(imageKeyOffV);
        hboxBottom1.getChildren().add(keyIconContainer);

        StackPane addIconContainer = new StackPane();
        addIconContainer.setPrefSize(50, 50);
        addIconContainer.setAlignment(Pos.CENTER);
        addIconContainer.getChildren().add(imageAddOffV);
        addIconContainer.getChildren().add(imageSettingsOffV);
        hboxBottom1.getChildren().add(addIconContainer);

        StackPane settingsIconContainer = new StackPane();
        settingsIconContainer.setPrefSize(50, 50);
        settingsIconContainer.setAlignment(Pos.CENTER);
        settingsIconContainer.getChildren().add(imageSettingsOffV);
        hboxBottom1.getChildren().add(settingsIconContainer);

        Scene sceneHome = new Scene(rootPassword, 400, 500);
        if (Settings.isDarkMode()) {
            sceneHome.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/application/application-dark.css")).toExternalForm());
        } else {
            sceneHome.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/application/application.css")).toExternalForm());
        }
        // Load the app icon and set it to the window's title bar
        Image appIcon = new Image(Objects.requireNonNull(getClass().getResource("/images/logo.png")).toExternalForm());
        primaryStage.getIcons().add(appIcon);

        homeIconContainer.setOnMouseClicked(event -> {
            HomeScreen homeScreen = new HomeScreen();
            homeScreen.start(primaryStage);
        });

        keyIconContainer.setOnMouseClicked(event -> {
            PasswordScreen passwordScreen = new PasswordScreen();
            passwordScreen.start(primaryStage);
        });

        addIconContainer.setOnMouseClicked(event -> {
            AddPassword addPassword = new AddPassword();
            addPassword.start(primaryStage);
        });

        settingsIconContainer.setOnMouseClicked(event -> {
            Settings settingsScreen = new Settings();
            settingsScreen.start(primaryStage);
        });

        // Set up the stage
        primaryStage.setScene(sceneHome);
        primaryStage.setTitle("VALU");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
