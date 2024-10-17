package fr.diginamic.recensement.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.CodeException;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;

/**
 * Cas d'utilisation: affichage des N villes les plus peuplées d'une région
 * donnée
 * 
 * @author DIGINAMIC
 *
 */
public class RechercheVillesPlusPeupleesRegion extends MenuService {

	@Override
	public void traiter(Recensement recensement, Scanner scanner) throws CodeException {

		System.out.println("Veuillez saisir un nom de région:");
		String nomRegion = scanner.nextLine();
		boolean regionTrouvee = false;

		System.out.println("Veuillez saisir un nombre de villes:");
		String nbVillesStr = scanner.nextLine();
		int nbVilles = Integer.parseInt(nbVillesStr);

		if(nbVilles <= 0) {
			throw new CodeException("Le nombre saisie ne peut être négatif ou égal à 0 !");
		}

		List<Ville> villesRegions = new ArrayList<Ville>();

		List<Ville> villes = recensement.getVilles();
		for (Ville ville : villes) {
			if (ville.getNomRegion().toLowerCase().startsWith(nomRegion.toLowerCase())) {
				villesRegions.add(ville);
				regionTrouvee = true;
			}
		}


		if(!regionTrouvee) {
			throw new CodeException("Cette région n'exite pas !");
		}

		if(nbVilles > villesRegions.size()) {
			throw new CodeException("Il n'y a pas autant de ville dans cete région !");
		}

		Collections.sort(villesRegions, new EnsemblePopComparateur(false));
		System.out.println("Les " + nbVilles + " villes les plus peuplées de la région " + nomRegion + " sont :");
		if (villesRegions.size() > 0) {
			for (int i = 0; i < nbVilles; i++) {
				Ville ville = villesRegions.get(i);
				System.out.println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
			}
		}

	}

}
