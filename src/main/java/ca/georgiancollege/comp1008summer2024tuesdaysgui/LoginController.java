package ca.georgiancollege.comp1008summer2024tuesdaysgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPass;

    @FXML
    private Label lblError;

    private LoginModel model = new LoginModel();

    @FXML
    private Button btnRegister, btnSignin;


    @FXML
    void initialize(){

        lblError.setText("");

    }

    @FXML
    void onRegister(ActionEvent event) {

        //add the username and password to a file on File System

        try{
            model.register(txtEmail.getText(), txtPass.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("You've successfully registered the credentials");
            alert.show();

            txtEmail.clear();
            txtPass.clear();


        }
        catch (Exception e){
            lblError.setText(e.getMessage());
        }

    }

    void onSigninOld(ActionEvent event) {

        lblError.setText("");

        if(txtEmail.getText().equals("admin") && txtPass.getText().equals("pass")){

            //System.out.println("Correct");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct");
            alert.setContentText("You've entered a correct username and password");
            alert.show();
            txtEmail.clear();
            txtPass.clear();

        }else{
            //System.out.println("Invalid username or password");
            lblError.setText("Invalid username or password");
        }

        System.out.println("signin");
    }

    @FXML
    void onSignin(ActionEvent event) {

        lblError.setText("");
        if(model.login(txtEmail.getText(), txtPass.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct");
            alert.setContentText("You've entered a correct username and password");
            alert.show();

        }else{
            //System.out.println("Invalid username or password");
            lblError.setText("Invalid username or password");
        }


    }

    @FXML
    void onForgotPassword(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("What Happened?");
        alert.setContentText("How could you forget your password, eh?!");
        alert.show();
    }

    private StringBuilder sb = new StringBuilder();

    @FXML
    void onEmailKeyPressed(KeyEvent  event) {

        System.out.println("Text=" +event.getText());
        System.out.println("Code=" + event.getCode().getCode());
        int keyCodeNum = event.getCode().getCode();

        if(keyCodeNum >=65 && keyCodeNum <= 90 || keyCodeNum >= 48 && keyCodeNum <= 57){
            sb.setLength(0);
            sb.append(txtEmail.getText());

        } else if (keyCodeNum == 37 || keyCodeNum == 39 || keyCodeNum == 8 || keyCodeNum == 46) {
            //do nothing because user clicked on arrows

        } else{
            txtEmail.setText(sb.toString());
            txtEmail.positionCaret(txtEmail.getText().length() );
        }

    }

    boolean areAllRequiredFieldsFilledOut(){

        return txtEmail.getText().length() > 0 && !txtPass.getText().isEmpty();
    }

    @FXML
    void onButtonMouseEntered(MouseEvent event) {
        System.out.println("onButtonMouseEntered");

                if(areAllRequiredFieldsFilledOut()){

            Button currentButton = (Button) event.getSource();
            currentButton.setDisable(true);

        }

    }

    @FXML
    void onButtonMouseExited(MouseEvent event) {

        System.out.println("on mouse exited");

        Button currentButton = (Button) event.getSource();
            currentButton.setDisable(false);
    }

}
