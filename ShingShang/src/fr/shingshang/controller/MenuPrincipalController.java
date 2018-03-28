package fr.shingshang.controller;

import fr.shingshang.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MenuPrincipalController {
	@FXML
	private Label titreLabel;
	@FXML
	private Button nouvellePartieButton;
	@FXML
	private Button chargerPartieButton;
	@FXML
	private Label legendeLabel;
	
	private MainApp mainApplication;

	public void cliqueNouvellePartieButton(){
		this.mainApplication.initNouvellePartie();
	}
	public void cliqueChargerPartieButton(){
		this.mainApplication.initChargerPartie();
	}
	
	public MainApp getMainApplication() {
		return mainApplication;
	}
	public void setMainApplication(MainApp mainApplication) {
		this.mainApplication = mainApplication;
	}
}
