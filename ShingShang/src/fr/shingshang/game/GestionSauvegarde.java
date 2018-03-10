package fr.shingshang.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestionSauvegarde {
	public void sauvegarder(String pathFile,ShingShang shingShang) throws FileNotFoundException, IOException {
		File fichierSauvegarde = new File (pathFile);

		ObjectOutputStream writeStream;
		writeStream = new ObjectOutputStream (new FileOutputStream (fichierSauvegarde));
		writeStream.writeObject (shingShang);
		writeStream.close();
		
	}
	
	public ShingShang charger(String pathFile) throws ClassNotFoundException, FileNotFoundException, IOException {
		File fichierCharger = new File (pathFile);
		ShingShang shingShang = null;
		
		ObjectInputStream readStream;
		readStream = new ObjectInputStream (new FileInputStream (fichierCharger));
		shingShang = (ShingShang) readStream.readObject();
		readStream.close();
		
		
		return shingShang;
	}
}
