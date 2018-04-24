package fr.shingshang.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import fr.shingshang.MainApp;
import fr.shingshang.model.ShingShang;
import fr.shingshang.model.execption.ValeurAttributException;
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
	
	public void cliqueLancerButton(){
		ShingShang shingShang;
		try {
			shingShang = ShingShang.chargerPartie(sauvegardeTextField.getText()+".save");
			shingShang.setCheminSauvegarde(sauvegardeTextField.getText()+".save");
			mainApplication.initEcranJeu(shingShang);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ValeurAttributException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public MainApp getMainApplication() {
		return mainApplication;
	}
	public void setMainApplication(MainApp mainApplication) {
		this.mainApplication = mainApplication;
	}
}
