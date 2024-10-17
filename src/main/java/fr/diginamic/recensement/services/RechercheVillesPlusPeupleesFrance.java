package fr.diginamic.recensement.services;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.CodeException;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;

/**
 * Cas d'utilisation: affichage des N villes les plus peuplées de France
 * 
 * @author DIGINAMIC
 *
 */
public class RechercheVillesPlusPeupleesFrance extends MenuService {

	@Override
	public void traiter(Recensement recensement, Scanner scanner) throws CodeException {

		System.out.println("Veuillez saisir un nombre de villes:");
		String nbVillesStr = scanner.nextLine();
		int nbVilles = Integer.parseInt(nbVillesStr);

		if (nbVilles <= 0) {
			throw new CodeException("Le nombre de villes saisie ne peut être égal ou inférieur à 0 !");
		}

		List<Ville> villes = recensement.getVilles();

		if(nbVilles > villes.size()) {
			throw new CodeException("Il n'y a pas autant de villes en france !");
		}

		System.out.println("Les " + nbVilles + " villes les plus peuplées de France sont :");
		Collections.sort(villes, new EnsemblePopComparateur(false));
		for (int i = 0; i < nbVilles; i++) {
			Ville ville = villes.get(i);
			System.out.println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
		}
	}

}
