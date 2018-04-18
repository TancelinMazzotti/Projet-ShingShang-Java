package fr.shingshang;

import java.io.IOException;

import fr.shingshang.controller.ChargerPartieController;
import fr.shingshang.controller.EcranJeuController;
import fr.shingshang.controller.MenuPrincipalController;
import fr.shingshang.controller.NouvellePartieController;
import fr.shingshang.controller.RootLayoutController;
import fr.shingshang.model.ShingShang;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application{
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ShingShang shingShang;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Shing - Shang");
		initRootLayout();
		initMenuPrincipal();
	}
	
	public void initRootLayout()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			this.rootLayout = (BorderPane) loader.load();
			RootLayoutController controller = loader.getController();
			controller.setMainApplication(this);
			
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void initMenuPrincipal()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MenuPrincipal.fxml"));
			AnchorPane menuPrincipal = (AnchorPane) loader.load();
			this.rootLayout.setCenter(menuPrincipal);
			MenuPrincipalController controller = loader.getController();
			controller.setMainApplication(this);
			primaryStage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void initNouvellePartie()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/NouvellePartie.fxml"));
			AnchorPane nouvellePartie = (AnchorPane) loader.load();
			this.rootLayout.setCenter(nouvellePartie);
			NouvellePartieController controller = loader.getController();
			controller.setMainApplication(this);
			primaryStage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void initChargerPartie()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ChargerPartie.fxml"));
			AnchorPane chargerPartie = (AnchorPane) loader.load();
			this.rootLayout.setCenter(chargerPartie);
			ChargerPartieController controller = loader.getController();
			controller.setMainApplication(this);
			primaryStage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	public void initEcranJeu()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/EcranJeu.fxml"));
			AnchorPane ecranJeu = (AnchorPane) loader.load();
			this.rootLayout.setCenter(ecranJeu);
			EcranJeuController controller = loader.getController();
			controller.setMainApplication(this);
			controller.initController();
			primaryStage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public ShingShang getShingShang(){
		return this.shingShang;
	}
	public void setShingShang(ShingShang shingShang){
		this.shingShang = shingShang;
	}
	public static void main(String[] args) {
		launch(args);
	}

}
