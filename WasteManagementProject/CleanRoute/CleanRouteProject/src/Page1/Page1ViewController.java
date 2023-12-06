package Page1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Page1ViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField initialtruckweight;

    @FXML
    private TextField finaltruckweight;

    @FXML
    private TextField garbagetypes;

    @FXML
    private TextField plastic;

    @FXML
    private TextField glass;

    @FXML
    private TextField wet;

    @FXML
    private TextField hazard;

    @FXML
    private Label msg1;

    @FXML
    private Label msg2;

    @FXML
    private Label msg3;

    @FXML
    private Label msg4;

    @FXML
    private Label msg5;

    @FXML
    private Label msg6;

    @FXML
    private Label msg7;

    @FXML
    private Label drivercost;

    private WasteController wasteController; // Add this line

    @FXML
    private Label finallab;

    @FXML
    void aboutus(ActionEvent event) {
     finallab.setText("ProjectDeveloped by Members:- Sahaj Somavanshi, Sai Kalyan Tirlangi, Manil Munjal, Heraj Gantyada");
    }

    @FXML
    void close(ActionEvent event) {
     System.exit(0);
    }

    @FXML
    void submit(ActionEvent event) throws Exception {
     String a=initialtruckweight.getText();
     String b=finaltruckweight.getText();
     String c=garbagetypes.getText();
     String d=plastic.getText();
     String e=glass.getText();
     String f=wet.getText();
     String g=hazard.getText();
        if (wasteController != null) {
            wasteController.startProcess(a , b , c , d , e , f , g);
            msg1.setText(wasteController.getMsg1());
            msg2.setText(wasteController.getMsg2());
            msg3.setText(wasteController.getMsg3());
            msg4.setText(wasteController.getMsg4());
            msg5.setText(wasteController.getMsg5());
            drivercost.setText(wasteController.getMsg6());
            msg6.setText(wasteController.getMsg7());
            msg7.setText(wasteController.getMsg8());
        }


    }

    @FXML
    void initialize() {
        assert initialtruckweight != null : "fx:id=\"initialtruckweight\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert finaltruckweight != null : "fx:id=\"finaltruckweight\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert garbagetypes != null : "fx:id=\"garbagetypes\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert plastic != null : "fx:id=\"plastic\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert glass != null : "fx:id=\"glass\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert wet != null : "fx:id=\"wet\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert hazard != null : "fx:id=\"hazard\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert msg1 != null : "fx:id=\"msg1\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert msg2 != null : "fx:id=\"msg2\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert msg3 != null : "fx:id=\"msg3\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert msg4 != null : "fx:id=\"msg4\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert msg5 != null : "fx:id=\"msg5\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert msg6 != null : "fx:id=\"msg6\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert msg7 != null : "fx:id=\"msg7\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert drivercost != null : "fx:id=\"drivercost\" was not injected: check your FXML file 'Page1View.fxml'.";
        assert finallab != null : "fx:id=\"finallab\" was not injected: check your FXML file 'Page1View.fxml'.";
        wasteController = new WasteController();

    }
}
