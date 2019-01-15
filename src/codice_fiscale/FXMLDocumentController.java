/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codice_fiscale;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 *
 * @author andrea
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField fieldNome;
    @FXML
    private TextField fieldCognome;
    @FXML
    private ComboBox<String> cmbSesso;
    @FXML
    private TextField fieldLuogo;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea labelCodiceFiscale;
    @FXML
    private Button btnCalcola;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbSesso.getItems().addAll("M", "F");
    }    

    @FXML
    private void credenziali(ActionEvent event){
        if(!fieldNome.getText().equals("") && !fieldCognome.getText().equals("") && !fieldLuogo.getText().equals("") && !datePicker.getValue().toString().equals("")){
            btnCalcola.setDisable(false);
        }else{
            btnCalcola.setDisable(true);
        }
    }

    @FXML
    private void Calcola(ActionEvent event) throws IOException{
        CodiceFiscale cf3 = new CodiceFiscale(fieldCognome.getText(), fieldNome.getText(), cmbSesso.getValue(), datePicker.getValue().toString(), fieldLuogo.getText());
        labelCodiceFiscale.setVisible(true);
        try {
            labelCodiceFiscale.setFont(new Font(40));
            labelCodiceFiscale.setText(cf3.getCodice());
        } catch (CodiceFiscaleNotValid ex) {
            labelCodiceFiscale.setFont(new Font(25));
            labelCodiceFiscale.setText("Dati non validi");
        }
    }
    
    
    
    
   

}
