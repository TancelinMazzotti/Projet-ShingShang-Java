package fr.shingshang;

import java.io.IOException;

import fr.shingshang.controller.MenuPrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application{
	private Stage primaryStage;
	private BorderPane rootLayout;

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
			AnchorPane profile = (AnchorPane) loader.load();
			this.rootLayout.setCenter(profile);
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
			AnchorPane profile = (AnchorPane) loader.load();
			this.rootLayout.setCenter(profile);
			primaryStage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
