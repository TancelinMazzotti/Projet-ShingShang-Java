package fr.shingshang.controller;

import fr.shingshang.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class RootLayoutController {
	@FXML
	private MenuItem menuPrincipalMenuItem;
	@FXML
	private MenuItem nouvellePartieMenuItem;
	@FXML
	private MenuItem chargerPartielMenuItem;
	
	private MainApp mainApplication;
	
	public void cliqueMenuPrincipalMenuItem(){
		this.mainApplication.initMenuPrincipal();
	}
	public void cliqueNouvellePartieMenuItem(){
		this.mainApplication.initNouvellePartie();
	}
	public void cliqueChargerPartieMenuItem(){
		this.mainApplication.initChargerPartie();
	}

	public MainApp getMainApplication() {
		return mainApplication;
	}
	public void setMainApplication(MainApp mainApplication) {
		this.mainApplication = mainApplication;
	}
}
