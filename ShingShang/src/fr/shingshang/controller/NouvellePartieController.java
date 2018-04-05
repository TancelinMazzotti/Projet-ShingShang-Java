package fr.shingshang.controller;

import fr.shingshang.MainApp;
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
	
	public MainApp getMainApplication() {
		return mainApplication;
	}
	public void setMainApplication(MainApp mainApplication) {
		this.mainApplication = mainApplication;
	}
}
