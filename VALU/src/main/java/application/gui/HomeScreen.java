package application.gui;

import application.dbconnection.PasswordJDBCDao;
import application.dbconnection.UserJDBCDao;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class HomeScreen extends Application {

    // creates the GUI scene for the Home Screen and welcomes the user
    @Override
    public void start(Stage primaryStage) {
        BorderPane rootHome = new BorderPane();
        rootHome.getStyleClass().add("root");

        // Create Hbox for top
        HBox hboxTop1 = new HBox();
        rootHome.setTop(hboxTop1);
        hboxTop1.setAlignment(Pos.CENTER);
        hboxTop1.getStyleClass().add("login-VBox");
        hboxTop1.setPadding(new Insets(10, 10, 10, 10));

        //Create Hbox for Center Quotes
        HBox hboxCenter1 = new HBox();
        rootHome.setCenter(hboxCenter1);
        hboxCenter1.setAlignment(Pos.CENTER);
        hboxCenter1.setPadding(new Insets(10, 10, 10, 10));

        //Create VBOX so HBOX Center is really in the Center
        VBox vboxCenter1 = new VBox();
        rootHome.setCenter(vboxCenter1);
        vboxCenter1.setAlignment(Pos.CENTER);
        vboxCenter1.setPadding(new Insets(10, 10, 10, 10));

        // Create HBox for bottom area
        HBox hboxBottom1 = new HBox();
        rootHome.setBottom(hboxBottom1);
        hboxBottom1.setAlignment(Pos.CENTER);
        hboxBottom1.getStyleClass().add("icons-bottom");
        hboxBottom1.setSpacing(40);
        hboxBottom1.setPadding(new Insets(10, 10, 10, 10));

        // Welcome Title
        String username = UserJDBCDao.getUsername(Login.getCurrentUserUUID());
        if (username != null) {
            username = username.toUpperCase();
            Label lbWelcome = new Label("WELCOME BACK " + username + "!");
            hboxTop1.getChildren().add(lbWelcome);
            lbWelcome.getStyleClass().add("welcome-title");
        } else {
            Label lbWelcome = new Label("NO USER FOUND");
            hboxTop1.getChildren().add(lbWelcome);
            lbWelcome.getStyleClass().add("welcome-title");
        }


        //Quotes
        String[] quotesList = {
                "\"Curry up and eat\"\n" +
                "-Arvin Nathan-",
                "\"Take a deep look in the mirror\"\n" +
                "-Huu Vinh Lee-",
                "\"Shut ze fuck up\"\n" +
                "-Nils Schädeli-","\n" +
                "\"Skadoosh.\"\n" +
                "-Flavio Argentati-" ,
                "“ABSOULTE CINEMA“\n" +
                        "-Oli-"};
        String quote = quotesList[(int) (Math.random() * quotesList.length)];
        Label lbQuote = new Label(quote);
        vboxCenter1.getChildren().add(hboxCenter1);
        hboxCenter1.getChildren().add(lbQuote);
        lbQuote.getStyleClass().add("quote-title");
        lbQuote.setWrapText(true);
        lbQuote.setAlignment(Pos.CENTER);
        lbQuote.setAlignment(javafx.geometry.Pos.CENTER);
        lbQuote.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        hboxCenter1.setAlignment(Pos.CENTER);
        hboxCenter1.setAlignment(javafx.geometry.Pos.CENTER);
        hboxCenter1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        hboxCenter1.setPadding(new Insets(10, 10, 10, 10));


        // Add Icons
        Image imageHomeOnL = new Image(Objects.requireNonNull(getClass().getResource("/images/homeOn.png")).toExternalForm());
        ImageView imageHomeOffV = new ImageView(imageHomeOnL);
        imageHomeOffV.setFitHeight(25);
        imageHomeOffV.setFitWidth(25);
        hboxBottom1.getChildren().add(imageHomeOffV);

        Image imageKeyOffL = new Image(Objects.requireNonNull(getClass().getResource("/images/keyOff.png")).toExternalForm());
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

        Scene scene = new Scene(rootHome, 400, 500);
        if (Settings.isDarkMode()) {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/application/applicationHome-dark.css")).toExternalForm());
        } else {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/application/applicationHome.css")).toExternalForm());
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
        primaryStage.setScene(scene);
        primaryStage.setTitle("VALU");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
