package fr.diginamic.recensement.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Departement;
import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.CodeException;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;

/**
 * Affichage des N départements les plus peuplés
 * 
 * @author DIGINAMIC
 *
 */
public class RechercheDepartementsPlusPeuplees extends MenuService {

	@Override
	public void traiter(Recensement recensement, Scanner scanner) throws CodeException {

		System.out.println("Veuillez saisir un nombre de départements:");
		String nbDeptsStr = scanner.nextLine();
		int nbDepts = Integer.parseInt(nbDeptsStr);

		if(nbDepts <= 0) {
			throw new CodeException("Le nombre de départements saisie ne peut être égal ou inférieur à 0 !");
		}

		List<Ville> villes = recensement.getVilles();
		Map<String, Departement> mapDepts = new HashMap<>();

		for (Ville ville : villes) {

			Departement departement = mapDepts.get(ville.getCodeDepartement());
			if (departement == null) {
				departement = new Departement(ville.getCodeDepartement());
				mapDepts.put(ville.getCodeDepartement(), departement);
			}
			departement.addVille(ville);
		}

		List<Departement> departements = new ArrayList<Departement>();
		departements.addAll(mapDepts.values());

		if(nbDepts > departements.size()) {
			throw new CodeException("Il n'y a pas autant de départements en france !");
		}

		Collections.sort(departements, new EnsemblePopComparateur(false));

		for (int i = 0; i < nbDepts; i++) {
			Departement departement = departements.get(i);
			System.out.println(
					"Département " + departement.getCode() + " : " + departement.getPopulation() + " habitants.");
		}

	}

}
