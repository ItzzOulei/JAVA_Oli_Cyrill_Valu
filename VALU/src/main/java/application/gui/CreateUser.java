package application.gui;

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
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Objects;

public class CreateUser extends Application {
    private static Label labelStatus;

    // This method creates the GUI scene for the Create User Screen
    @Override
    public void start(Stage primaryStage) {
        BorderPane rootCreateUser = new BorderPane();
        rootCreateUser.getStyleClass().add("root");

        // Create VBox for center area
        VBox vboxCenter1 = new VBox();
        rootCreateUser.setCenter(vboxCenter1);
        vboxCenter1.setAlignment(Pos.CENTER);
        vboxCenter1.getStyleClass().add("login-VBox");
        vboxCenter1.setSpacing(10);
        vboxCenter1.setPadding(new Insets(10, 10, 10, 10));

        // Title box
        HBox hboxCenter1 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter1);
        hboxCenter1.setSpacing(10);
        hboxCenter1.setAlignment(Pos.CENTER);

        // Hbox for status label (got added later)
        HBox hboxCenter1half = new HBox();
        vboxCenter1.getChildren().add(hboxCenter1half);
        hboxCenter1half.setSpacing(10);
        hboxCenter1half.setAlignment(Pos.CENTER);
        hboxCenter1half.setPadding(new Insets(0, 0, 5, 0));

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
        TextField tfPassword = new TextField();
        vboxCenter1.getChildren().add(tfPassword);
        tfPassword.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        tfPassword.getStyleClass().add("login-textfields");

        HBox hboxCenter8 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter8);
        hboxCenter8.setSpacing(10);
        hboxCenter8.setAlignment(Pos.CENTER);
        hboxCenter8.setPadding(new Insets(15, 0, 0, 0));

        HBox hboxCenter9 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter9);
        hboxCenter9.setSpacing(10);
        hboxCenter9.setAlignment(Pos.CENTER);

        //

        // Big Title for Create user scene
        Label labelCreatUser = new Label("CREATE USER");
        hboxCenter1.getChildren().add(labelCreatUser);
        labelCreatUser.getStyleClass().add("login-title");
        labelCreatUser.setAlignment(Pos.CENTER);

        // Status label if creation was successful (box1.5)
        Label labelStatus = new Label("");
        hboxCenter1half.getChildren().add(labelStatus);
        labelStatus.getStyleClass().add("login-label-error");
        labelStatus.setAlignment(Pos.CENTER);

        // Username Textbox Info (box2)
        Label labelUsername = new Label("Enter Username: ");
        hboxCenter2.getChildren().add(labelUsername);
        labelUsername.getStyleClass().add("login-label");

        // Email Textbox Info (box4)
        Label labelEmail = new Label("Enter E-Mail: ");
        hboxCenter4.getChildren().add(labelEmail);
        labelEmail.getStyleClass().add("login-label");


        // Password Textbox Info (box6)
        Label labelPassword = new Label("Enter Password: ");
        hboxCenter6.getChildren().add(labelPassword);
        labelPassword.getStyleClass().add("login-label");

        // The unlock button to get into the PwdManager (box7)
        Button btnLogin = new Button("Create Account");
        hboxCenter8.getChildren().add(btnLogin);
        HBox.setHgrow(btnLogin, Priority.ALWAYS);
        btnLogin.getStyleClass().add("logIn-button");
        btnLogin.setAlignment(Pos.CENTER);

        // The create button to create an account -> leads to new scene (box8)
        Button btnCreateAccount = new Button("Back to Login");
        hboxCenter9.getChildren().add(btnCreateAccount);
        HBox.setHgrow(btnCreateAccount, Priority.ALWAYS);
        btnCreateAccount.getStyleClass().add("login-create-button");
        btnCreateAccount.setAlignment(Pos.CENTER);

        /* Event for button to switch to HomeScreen
        Creates Account and verifies if its already registered
         */
        btnLogin.setOnAction(event -> {
            String username = tfusername.getText();
            username = username.toLowerCase().replaceAll("\\s","");
            String email = tfemail.getText();
            email = email.toLowerCase().replaceAll("\\s","");
            String password = tfPassword.getText();
            password = PasswordCrypter.encrypt(password);
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                labelStatus.setText("Invalid E-Mail or Password");
            }
            else {
                if (UserJDBCDao.checkEmailRegistered(email)) {
                    UserJDBCDao.saveData(username, email, password);
                    Login Login = new Login();
                    Login.start(primaryStage);
                }
                else {
                    labelStatus.setText("E-Mail is already registered");
                }
            }
        });

        // Event for button to create account
        btnCreateAccount.setOnAction(event -> {
            Login Login = new Login();
            Login.start(primaryStage);

        });

        // Set up the scene
        Scene scene = new Scene(rootCreateUser, 400, 500);
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
