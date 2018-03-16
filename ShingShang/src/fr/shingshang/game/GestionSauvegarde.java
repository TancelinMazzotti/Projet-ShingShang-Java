package fr.shingshang.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestionSauvegarde {
	public static void sauvegarder(String pathFile,ShingShang shingShang) throws FileNotFoundException, IOException {
		File fichierSauvegarde = new File (pathFile);
		ObjectOutputStream writeStream = new ObjectOutputStream (new FileOutputStream (fichierSauvegarde));
		writeStream.writeObject(shingShang);
		writeStream.close();
	}
	
	public static ShingShang charger(String pathFile) throws ClassNotFoundException, FileNotFoundException, IOException {
		File fichierCharger = new File (pathFile);
		ObjectInputStream readStream = new ObjectInputStream(new FileInputStream (fichierCharger));
		ShingShang shingShang = (ShingShang)readStream.readObject();
		readStream.close();
		
		return shingShang;
	}
}
