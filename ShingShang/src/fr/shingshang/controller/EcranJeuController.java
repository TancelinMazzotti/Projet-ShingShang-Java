package fr.shingshang.controller;

import fr.shingshang.MainApp;
import fr.shingshang.model.ShingShang;
import fr.shingshang.model.enumeration.PuissancePion;
import fr.shingshang.model.execption.CaseBloqueException;
import fr.shingshang.model.execption.HorsPlateauException;
import fr.shingshang.model.plateau.CasePlateau;
import fr.shingshang.model.plateau.Plateau;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


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
	private Label legendeLabel;
	
	private ImageView imagesCasePlateau[][];
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
					ImageView imageView = new ImageView();
					pane.getChildren().add(imageView);
					imageView.fitHeightProperty().bind(pane.heightProperty());
					imageView.fitWidthProperty().bind(pane.widthProperty());
					this.imagesCasePlateau[x][y] = imageView;
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
	
	public void actualiserAffichage(){
		for(int y = 0; y < Plateau.TAILLE_PLATEAU; y++){
			for(int x = 0; x < Plateau.TAILLE_PLATEAU; x++){
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
