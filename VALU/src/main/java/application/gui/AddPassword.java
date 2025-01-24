package application.gui;

import application.dbconnection.PasswordDAO;
import application.dbconnection.PasswordJDBCDao;
import application.dbconnection.UserJDBCDao;
import application.secure.PasswordCrypter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class AddPassword extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane rootAddPassword = new BorderPane();
        rootAddPassword.getStyleClass().add("root");

        // Create VBox for center area
        VBox vboxCenter1 = new VBox();
        rootAddPassword.setCenter(vboxCenter1);
        vboxCenter1.setAlignment(Pos.CENTER);
        vboxCenter1.getStyleClass().add("login-VBox");
        vboxCenter1.setSpacing(10);
        vboxCenter1.setPadding(new Insets(10, 10, 10, 10));

        // Title box
        HBox hboxCenter1 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter1);
        hboxCenter1.setSpacing(10);
        hboxCenter1.setAlignment(Pos.CENTER);

        // Create VBox for Bottom area
        HBox hboxBottom1 = new HBox();
        rootAddPassword.setBottom(hboxBottom1);
        hboxBottom1.setAlignment(Pos.CENTER);
        hboxBottom1.getStyleClass().add("icons-bottom");
        hboxBottom1.setSpacing(40);
        hboxBottom1.setPadding(new Insets(10, 10, 10, 10));

        // Add Icons
        Image imageHomeOffL = new Image(Objects.requireNonNull(getClass().getResource("/images/homeOff.png")).toExternalForm());
        ImageView imageHomeOffV = new ImageView(imageHomeOffL);
        imageHomeOffV.setFitHeight(25);
        imageHomeOffV.setFitWidth(25);
        hboxBottom1.getChildren().add(imageHomeOffV);

        Image imageKeyOffL = new Image(Objects.requireNonNull(getClass().getResource("/images/keyOff.png")).toExternalForm());
        ImageView imageKeyOffV = new ImageView(imageKeyOffL);
        imageKeyOffV.setFitHeight(25);
        imageKeyOffV.setFitWidth(25);
        hboxBottom1.getChildren().add(imageKeyOffV);

        Image imageAddOnL = new Image(Objects.requireNonNull(getClass().getResource("/images/addOn.png")).toExternalForm());
        ImageView imageAddOnV = new ImageView(imageAddOnL);
        imageAddOnV.setFitHeight(25);
        imageAddOnV.setFitWidth(25);
        hboxBottom1.getChildren().add(imageAddOnV);

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

        StackPane settingsIconContainer = new StackPane();
        settingsIconContainer.setPrefSize(50, 50);
        settingsIconContainer.setAlignment(Pos.CENTER);
        settingsIconContainer.getChildren().add(imageAddOnV);
        hboxBottom1.getChildren().add(settingsIconContainer);

        HBox hboxCenterStatus = new HBox();
        vboxCenter1.getChildren().add(hboxCenterStatus);

        // Username Info Textbox
        HBox hboxCenter2 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter2);
        hboxCenter2.setSpacing(10);

        // Username Textbox (box3)
        TextField tfusername = new TextField();
        vboxCenter1.getChildren().add(tfusername);
        tfusername.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        tfusername.getStyleClass().add("login-textfields");

        HBox hboxCenter4 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter4);
        hboxCenter4.setSpacing(10);

        // Email Textbox (box5)
        TextField tfemail = new TextField();
        vboxCenter1.getChildren().add(tfemail);
        tfemail.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        tfemail.getStyleClass().add("login-textfields");

        HBox hboxCenter6 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter6);
        hboxCenter6.setSpacing(10);

        // Password Textbox (box7)
        TextField tfAnwendung = new TextField();
        vboxCenter1.getChildren().add(tfAnwendung);
        tfAnwendung.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        tfAnwendung.getStyleClass().add("login-textfields");

        HBox hboxCenter8 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter8);
        hboxCenter8.setSpacing(10);

        // Password Textbox (box9)
        TextField tfPassword = new TextField();
        vboxCenter1.getChildren().add(tfPassword);
        tfPassword.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        tfPassword.getStyleClass().add("login-textfields");

        HBox hboxCenter10 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter10);
        hboxCenter10.setSpacing(10);
        hboxCenter10.setAlignment(Pos.CENTER);
        hboxCenter10.setPadding(new Insets(15, 0, 0, 0));

        HBox hboxCenter11 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter11);
        hboxCenter11.setSpacing(10);
        hboxCenter11.setAlignment(Pos.CENTER);

        homeIconContainer.setOnMouseClicked(event -> {
            HomeScreen homeScreen = new HomeScreen();
            homeScreen.start(primaryStage);
        });

        keyIconContainer.setOnMouseClicked(event -> {
            PasswordScreen passwordScreen = new PasswordScreen();
            passwordScreen.start(primaryStage);
        });

        settingsIconContainer.setOnMouseClicked(event -> {
            AddPassword settingsScreen = new AddPassword();
            settingsScreen.start(primaryStage);
        });


        // Big Title for Add Password scene
        Label labelCreatUser = new Label("Add Password");
        hboxCenter1.getChildren().add(labelCreatUser);
        labelCreatUser.getStyleClass().add("login-title");
        labelCreatUser.setAlignment(Pos.CENTER);

        Label labelStatus = new Label("");
        hboxCenterStatus.getChildren().add(labelStatus);
        labelStatus.setAlignment(Pos.CENTER);
        labelStatus.getStyleClass().add("login-label");

        // Username Textbox (box2)
        Label labelUsername = new Label("Enter Username: ");
        hboxCenter2.getChildren().add(labelUsername);
        labelUsername.getStyleClass().add("login-label");

        // Email Textbox (box4)
        Label labelEmail = new Label("Enter E-Mail: ");
        hboxCenter4.getChildren().add(labelEmail);
        labelEmail.getStyleClass().add("login-label");

        // Anwendung Textbox (box6)
        Label labelAnwendung = new Label("Enter Application/Website: ");
        hboxCenter6.getChildren().add(labelAnwendung);
        labelAnwendung.getStyleClass().add("login-label");

        // Password Textbox (box8)
        Label labelPassword = new Label("Enter Password: ");
        hboxCenter8.getChildren().add(labelPassword);
        labelPassword.getStyleClass().add("login-label");

        Button btnAddPw = new Button("Add Password");
        hboxCenter11.getChildren().add(btnAddPw);
        HBox.setHgrow(btnAddPw, Priority.ALWAYS);
        btnAddPw.getStyleClass().add("logIn-button");
        btnAddPw.setAlignment(Pos.CENTER);

                /* Event for button to switch to HomeScreen
        Creates Account and verifies if its already registered
         */
        btnAddPw.setOnAction(event -> {
            String username = tfusername.getText();
            username = username.toLowerCase().replaceAll("\\s","");

            String email = tfemail.getText();
            email = email.toLowerCase().replaceAll("\\s","");

            String Anwendung = tfAnwendung.getText();
            Anwendung = Anwendung.replaceAll("\\s","");

            String password = tfPassword.getText();
            password = PasswordCrypter.encrypt(password);

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || Anwendung.isEmpty()) {
                labelStatus.setText("Invalid E-Mail or Password");
                labelStatus.getStyleClass().add("login-label-error");
            }
            else {
                PasswordJDBCDao.savePassword(Login.getCurrentUserUUID(), username, email, Anwendung, password);
                PasswordScreen passwordScreen = new PasswordScreen();
                passwordScreen.start(primaryStage);
            }
        });

        Scene scene = new Scene(rootAddPassword, 400, 500);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/application/application.css")).toExternalForm());

        // Load the app icon and set it to the window's title bar
        Image appIcon = new Image(Objects.requireNonNull(getClass().getResource("/images/logo.png")).toExternalForm());
        primaryStage.getIcons().add(appIcon);


        // Set up the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("VALU");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
