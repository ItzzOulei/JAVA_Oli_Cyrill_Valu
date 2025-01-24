package application.gui;

import application.dbconnection.UserJDBCDao;
import application.secure.PasswordCrypter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.util.Objects;


public class Login extends Application {
    private static String currentUserUUID;

    // This method creates the GUI scene for the login Screen
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("root");

        // Create VBox for center area
        VBox vboxCenter1 = new VBox();
        root.setCenter(vboxCenter1);
        vboxCenter1.setAlignment(Pos.CENTER);
        vboxCenter1.getStyleClass().add("login-VBox");
        vboxCenter1.setSpacing(10);
        vboxCenter1.setPadding(new Insets(10, 10, 10, 10));

        HBox hboxCenter1 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter1);
        hboxCenter1.setSpacing(10);
        hboxCenter1.setAlignment(Pos.CENTER);

        HBox hboxCenter2 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter2);
        hboxCenter2.setSpacing(10);
        hboxCenter2.setAlignment(Pos.CENTER);
        hboxCenter2.setPadding(new Insets(10, 10, 20, 10));

        HBox hboxCenter3 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter3);
        hboxCenter3.setSpacing(10);

        // Email Textbox (box4)
        TextField textFieldEmail = new TextField();
        vboxCenter1.getChildren().add(textFieldEmail);
        textFieldEmail.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        textFieldEmail.getStyleClass().add("login-textfields");

        HBox hboxCenter5 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter5);
        hboxCenter5.setSpacing(10);
        hboxCenter5.setPadding(new Insets(10, 0, 0, 0));

        // Password Textbox (box6)
        PasswordField textFieldPassword = new PasswordField();
        textFieldPassword.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        vboxCenter1.getChildren().add(textFieldPassword);
        textFieldPassword.getStyleClass().add("login-textfields");

        HBox hboxCenter7 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter7);
        hboxCenter7.setSpacing(10);
        hboxCenter7.setAlignment(Pos.CENTER);
        hboxCenter7.setPadding(new Insets(15, 0, 0, 0));

        HBox hboxCenter8 = new HBox();
        vboxCenter1.getChildren().add(hboxCenter8);
        hboxCenter8.setSpacing(10);
        hboxCenter8.setAlignment(Pos.CENTER);

        // Create the center section and add objects to HBox
        // Big Logo for Login Screen (box1)
        Image imageLogoL = new Image(Objects.requireNonNull(getClass().getResource("/images/logo.png")).toExternalForm());
        ImageView imageLogoV = new ImageView(imageLogoL);
        imageLogoV.setFitHeight(100);
        imageLogoV.setFitWidth(100);
        hboxCenter1.setAlignment(Pos.CENTER);
        hboxCenter1.getChildren().add(imageLogoV);

        // Give a little status information if its locked (box2)
        Label labelStatus = new Label("Valu locked");
        hboxCenter2.getChildren().add(labelStatus);
        labelStatus.getStyleClass().add("login-label");

        // Email Textbox Info (box3)
        Label labelEmail = new Label("Enter E-Mail: ");
        hboxCenter3.getChildren().add(labelEmail);
        labelEmail.getStyleClass().add("login-label");


        // Password Textbox Info (box5)
        Label labelPassword = new Label("Enter Password: ");
        hboxCenter5.getChildren().add(labelPassword);
        labelPassword.getStyleClass().add("login-label");

        // The unlock button to get into the PwdManager (box7)
        Button btnLogin = new Button("Unlock");
        hboxCenter7.getChildren().add(btnLogin);
        HBox.setHgrow(btnLogin, Priority.ALWAYS);
        btnLogin.getStyleClass().add("logIn-button");
        btnLogin.setAlignment(Pos.CENTER);

        // The create button to create an account -> leads to new scene (box8)
        Button btnCreateAccount = new Button("Create Account");
        hboxCenter8.getChildren().add(btnCreateAccount);
        HBox.setHgrow(btnCreateAccount, Priority.ALWAYS);
        btnCreateAccount.getStyleClass().add("login-create-button");
        btnCreateAccount.setAlignment(Pos.CENTER);

        // Event for button to switch to HomeScreen
        btnLogin.setOnAction(event -> {
            // Get email and password from the text fields
            String tfemail = textFieldEmail.getText();
            tfemail = tfemail.toLowerCase().replaceAll("\\s", "");  // Normalize email
            String tfpassword = textFieldPassword.getText();

            // Validate input
            if (tfemail.isEmpty() || tfpassword.isEmpty()) {
                labelStatus.getStyleClass().add("login-label-error");
                labelStatus.setText("Invalid Email or Password");
            } else {

                // Check if email and encrypted password match the database values
                String encryptedPassword = PasswordCrypter.encrypt(tfpassword);
                boolean isValidLogin = UserJDBCDao.checkEmailAndPassword(tfemail, encryptedPassword);

                if (isValidLogin) {
                    currentUserUUID = UserJDBCDao.getUserUID(tfemail, encryptedPassword);

                    // Switch to HomeScreen
                    HomeScreen homeScreen = new HomeScreen();
                    homeScreen.start(primaryStage);
                } else {
                    labelStatus.setText("Invalid Email or Password");
                    labelStatus.getStyleClass().add("login-label-error");
                }
            }
        });


        // Event for button to create account
        btnCreateAccount.setOnAction(event -> {
            CreateUser createUser = new CreateUser();
            createUser.start(primaryStage);
        });

        // Set up the scene
        Scene scene = new Scene(root, 400, 500, Color.WHITE);
        scene.setFill(Color.WHITE);
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
        launch();

    }

    // Getter and setter
    public static String getCurrentUserUUID() {
        return currentUserUUID;
    }

    public static void setCurrentUserUUID(String currentUserUUID) {
        Login.currentUserUUID = currentUserUUID;
    }
}
