package fr.shingshang.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import fr.shingshang.MainApp;
import fr.shingshang.model.Deplacement;
import fr.shingshang.model.ShingShang;
import fr.shingshang.model.enumeration.PuissancePion;
import fr.shingshang.model.execption.CaseBloqueException;
import fr.shingshang.model.execption.DeplacementException;
import fr.shingshang.model.execption.HorsPlateauException;
import fr.shingshang.model.pion.Pion;
import fr.shingshang.model.plateau.CasePlateau;
import fr.shingshang.model.plateau.Plateau;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class EcranJeuController {
	@FXML
	private Label titreLabel;
	@FXML
	private Label nomJoueur1Label;
	@FXML
	private Label nomJoueur2Label;
	@FXML
	private Label nomJoueurActuelLabel;
	@FXML
	private GridPane plateauGraphique;
	@FXML
	private Button terminerButton;
	@FXML
	private Label legendeLabel;
	
	private ImageView imagesCasePlateau[][];
	private Pion pionSelectionner;
	private Pion pionSaut;
	List<Deplacement> listDeplacement;
	private ShingShang shingShang;
	
	private final Image IMAGE_SINGE_JOUEUR1 = new Image("file:src/fr/shingshang/view/images/singe.png");
	private final Image IMAGE_LION_JOUEUR1 = new Image("file:src/fr/shingshang/view/images/lion.png");
	private final Image IMAGE_DRAGON_JOUEUR1 = new Image("file:src/fr/shingshang/view/images/dragon.png");
	private final Image IMAGE_SINGE_JOUEUR2 = new Image("file:src/fr/shingshang/view/images/singe2.png");
	private final Image IMAGE_LION_JOUEUR2 = new Image("file:src/fr/shingshang/view/images/lion2.png");
	private final Image IMAGE_DRAGON_JOUEUR2 = new Image("file:src/fr/shingshang/view/images/dragon2.png");
	private final Image IMAGE_BLOQUE = new Image("file:src/fr/shingshang/view/images/bloque.png");
	private final Image IMAGE_PORTE = new Image("file:src/fr/shingshang/view/images/porte.png");
		
	private MainApp mainApplication;
	
	public EcranJeuController(){
		this.imagesCasePlateau = new ImageView[Plateau.TAILLE_PLATEAU][Plateau.TAILLE_PLATEAU];
		this.pionSelectionner = null;
		this.pionSaut = null;
	}
	
	public void initController(){
		nomJoueur1Label.setText(shingShang.getJoueur1().getNom());
		nomJoueur2Label.setText(shingShang.getJoueur2().getNom());
		nomJoueurActuelLabel.setText(shingShang.getJoueurActuel().getNom());
		
		for(int y = 0; y < Plateau.TAILLE_PLATEAU; y++)
		{
			for(int x = 0; x < Plateau.TAILLE_PLATEAU; x++)
			{
				Pane pane;
				try {
					pane = getCellulePlateauGraphique(x, y);
					ImageView imageView = (ImageView) pane.getChildren().get(0);
					imageView.fitHeightProperty().bind(pane.heightProperty());
					imageView.fitWidthProperty().bind(pane.widthProperty());
					this.imagesCasePlateau[x][y] = imageView;
					
					imageView.setOnMouseClicked((MouseEvent evt) -> {
						cliquerSurCase(imageView);
					});
					imageView.setOnMouseEntered((MouseEvent evt) -> {
						entrerSurCase(imageView);
					});
					imageView.setOnMouseExited((MouseEvent evt) -> {
						sortirDeCase(imageView);
					});
					
				} catch (HorsPlateauException e) {
					e.printStackTrace();
				}
			}
		}
		actualiserAffichage();
	}
	private Pane getCellulePlateauGraphique(int x, int y) throws HorsPlateauException{
		if(!Plateau.estSurPlateau(x, y))
			throw new HorsPlateauException(x,y);
		Pane pane = (Pane) plateauGraphique.getChildren().get(y + Plateau.TAILLE_PLATEAU * x);
		return pane;
	}
	private int[] getCoordImageToken(ImageView imageView) throws HorsPlateauException{
		int coordonne[] = new int[2];
		coordonne[0] = -1;
		coordonne[1] = -1;
		Pane pane = (Pane) imageView.getParent();
		for(int y=0; y < Plateau.TAILLE_PLATEAU; y++ ){
			for(int x = 0; x < Plateau.TAILLE_PLATEAU; x++ ){
				if(pane == getCellulePlateauGraphique(x, y)){
					coordonne[0] = x;
					coordonne[1] = y;
				}
			}
		}
		return coordonne;
	}
	
	private void cliquerSurCase(ImageView imageView){
		int coordonneImage[];
		
		Shadow shadow = new Shadow(0.0, Color.ORANGE);
		int x, y;
		try {
			coordonneImage = getCoordImageToken(imageView);
			x = coordonneImage[0];
			y = coordonneImage[1];
			Pion pionClique = shingShang.getPionJoueurActuel(x, y);
			
			if(pionClique != null){
				this.pionSelectionner = pionClique;
				listDeplacement = pionSelectionner.listDeplacementPossible(shingShang.getPlateau());
				resetEffect();
				for(int i = 0; i < listDeplacement.size(); i++)
				{
					Deplacement deplacement = listDeplacement.get(i);
					int xDestination = deplacement.getDestination().getX();
					int yDestination = deplacement.getDestination().getY();
					this.imagesCasePlateau[xDestination][yDestination].getParent().setEffect(shadow);
				}
			}
			
			else if(this.pionSelectionner != null){
				Deplacement deplacement = Deplacement.rechercheDestinantionListDeplacement(this.listDeplacement, x, y);
				deplacement.deplacerPion();
				if(deplacement.isEstUnSaut())
				{
					deplacement.supprimerPion();
				}
				this.pionSelectionner = null;
				resetEffect();
				actualiserAffichage();
			}

		} catch (HorsPlateauException e) {
			System.out.println(e.getMessage());
		} catch (DeplacementException e) {
			System.out.println(e.getMessage());
		} catch (CaseBloqueException e) {
			System.out.println(e.getMessage());
		}

	}
	private void entrerSurCase(ImageView imageView){
		int coordonneImage[];
		try {
			coordonneImage = getCoordImageToken(imageView);
			Pion pion = shingShang.getPionJoueurActuel(coordonneImage[0], coordonneImage[1]);
			if(pion!=null){
				Shadow shadow = new Shadow(0.0, Color.GREEN);
				imageView.setEffect(shadow);
			}
		} catch (HorsPlateauException e) {
			e.printStackTrace();
		} catch (CaseBloqueException e) {
			System.out.println(e.getMessage());
		}
	}
	private void sortirDeCase(ImageView imageView){
		imageView.setEffect(null);
	}
	public void cliqueTerminerButton(){
		this.pionSelectionner =null;
		resetEffect();
		actualiserAffichage();
		shingShang.changerJoueur();
		nomJoueurActuelLabel.setText(shingShang.getJoueurActuel().getNom());
		try {
			shingShang.sauvegarderPartie();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void resetEffect(){
		for(int y = 0; y < Plateau.TAILLE_PLATEAU; y++){
			for(int x = 0; x < Plateau.TAILLE_PLATEAU; x++){
				this.imagesCasePlateau[x][y].setEffect(null);
				this.imagesCasePlateau[x][y].getParent().setEffect(null);
			}
		}
	}
	public void actualiserAffichage(){
		for(int y = 0; y < Plateau.TAILLE_PLATEAU; y++){
			for(int x = 0; x < Plateau.TAILLE_PLATEAU; x++){
				imagesCasePlateau[x][y].setImage(null);
				try {
					CasePlateau casePlateau = this.shingShang.getCasePlateau(x, y);
					if(casePlateau.estUnPortail())
						imagesCasePlateau[x][y].setImage(IMAGE_PORTE);
					
					if(casePlateau.getPionCase() != null){
						if(casePlateau.getPionCase().getPuissance() == PuissancePion.SINGE)
						{
							if(casePlateau.getPionCase().getJoueur().getNumero() == 1)
								imagesCasePlateau[x][y].setImage(IMAGE_SINGE_JOUEUR1);
							else imagesCasePlateau[x][y].setImage(IMAGE_SINGE_JOUEUR2);
						}
						else if(casePlateau.getPionCase().getPuissance() == PuissancePion.LION)
						{
							if(casePlateau.getPionCase().getJoueur().getNumero() == 1)
								imagesCasePlateau[x][y].setImage(IMAGE_LION_JOUEUR1);
							else imagesCasePlateau[x][y].setImage(IMAGE_LION_JOUEUR2);
						}
						else if(casePlateau.getPionCase().getPuissance() == PuissancePion.DRAGON)
						{
							if(casePlateau.getPionCase().getJoueur().getNumero() == 1)
								imagesCasePlateau[x][y].setImage(IMAGE_DRAGON_JOUEUR1);
							else imagesCasePlateau[x][y].setImage(IMAGE_DRAGON_JOUEUR2);
						}
					}
				} catch (HorsPlateauException e) {
					e.printStackTrace();
				} catch (CaseBloqueException e) {
					imagesCasePlateau[x][y].setImage(IMAGE_BLOQUE);
				}
			}
		}
	}
	
	public MainApp getMainApplication() {
		return mainApplication;
	}
	public void setMainApplication(MainApp mainApplication) {
		this.mainApplication = mainApplication;
	}
	public ShingShang getShingShang() {
		return shingShang;
	}
	public void setShingShang(ShingShang shingShang) {
		this.shingShang = shingShang;
	}
}
