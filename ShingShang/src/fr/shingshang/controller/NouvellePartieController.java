package fr.shingshang.controller;

import java.io.IOException;

import fr.shingshang.MainApp;
import fr.shingshang.model.ShingShang;
import fr.shingshang.model.execption.ValeurAttributException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NouvellePartieController {
	@FXML
	private Label titreLabel;
	@FXML
	private Label joueur1Label;
	@FXML
	private TextField joueur1TextField;
	@FXML
	private TextField joueur2TextField;
	@FXML
	private Label joueur2Label;
	@FXML
	private Label sauvegardeLabel;
	@FXML
	private TextField sauvegardeTextField;
	@FXML
	private Button lancerButton;
	@FXML
	private Label legendeLabel;
	
	private MainApp mainApplication;
	
	public void cliqueLancerButton(){
		ShingShang shingShang = new ShingShang();

		try {
			shingShang.nouvellePartie(joueur1TextField.getText(), joueur2TextField.getText());
			shingShang.setCheminSauvegarde(sauvegardeTextField.getText()+".save");
			shingShang.sauvegarderPartie();
			this.mainApplication.initEcranJeu(shingShang);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ValeurAttributException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public MainApp getMainApplication() {
		return mainApplication;
	}
	public void setMainApplication(MainApp mainApplication) {
		this.mainApplication = mainApplication;
	}
}
