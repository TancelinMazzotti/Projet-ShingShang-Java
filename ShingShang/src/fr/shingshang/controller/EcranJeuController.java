package fr.shingshang.controller;

import fr.shingshang.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class EcranJeuController {
	@FXML
	private Label titreLabel;
	@FXML
	private GridPane plateauGraphique;
	@FXML
	private Label legendeLabel;
	
	private MainApp mainApplication;
	
	public void initController(){
		
	}
	
	public MainApp getMainApplication() {
		return mainApplication;
	}
	public void setMainApplication(MainApp mainApplication) {
		this.mainApplication = mainApplication;
	}
}
