package encryptorgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.util.Base64;
import java.util.List;
import java.util.Random;

public class Controller {

    @FXML
    private TextArea input;

    @FXML
    private TextArea output;

    @FXML
    private TextField aeskey;

    @FXML
    private TextField aesiv;




    double x, y;
    @FXML
    void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() -x);
        stage.setY(event.getScreenY() -y);
    }

    @FXML
    void mpressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();

    }

    @FXML
    void min(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);

    }
    @FXML
    void press(ActionEvent event) {

        String text = input.getText();
        String step2 = AES.encrypt(aeskey.getText(), aesiv.getText(), text);
        String step3 = Base64.getEncoder().encodeToString(step2.getBytes());

        output.setText(step3);
    }

    @FXML
    void copy(ActionEvent event) {

        try {
            StringSelection stringSelection = new StringSelection(output.getText());
            java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        } catch (Exception ignored) {
        }
    }


    @FXML
    void ivgen(ActionEvent event) {
        String characters = "!\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        String randomstringiv = "";
        Random rand = new Random();
        int length = 16;
        char[] text = new char[length];

        for(int i = 0; i < length; i++){
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }
        for(int i = 0; i < text.length; i++){
            randomstringiv += text[i];
        }

        aesiv.setText(randomstringiv);

    }

    @FXML
    void keygen(ActionEvent event) {
        String characters = "!\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        String randomstringkg = "";
        Random rand = new Random();
        int length = 16;
        char[] text = new char[length];

        for(int i = 0; i < length; i++){
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }
        for(int i = 0; i < text.length; i++){
            randomstringkg += text[i];
        }

        aeskey.setText(randomstringkg);
    }


    @FXML
    void copykeyandiv(ActionEvent event) {
        try {
            String ivandkey = "Key: " + aeskey.getText() + " IV: " + aesiv.getText();

            StringSelection stringSelection = new StringSelection(ivandkey);
            java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        } catch (Exception ignored) {
        }
    }

}