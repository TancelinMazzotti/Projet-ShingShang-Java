package fr.shingshang.controller;

import fr.shingshang.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChargerPartieController {
	@FXML
	private Label titreLabel;
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
