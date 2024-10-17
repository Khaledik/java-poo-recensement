package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.CodeException;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws CodeException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();




		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();


		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();

		if(!saisieMin.matches("-?\\d+") || !saisieMax.matches("-?\\d+")) {
			throw new CodeException("Le Min et le Max doivent être des entiers et non des lettres !");
		}



		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;

		if(min < 0 || max < 0) {
			throw new CodeException("Les nombres saisie ne peuvent être négatif !");
		}

		if(max < min) {
			throw new CodeException("Le minimum ne peut être supérieur au maximum !");
		}




		List<Ville> villes = rec.getVilles();

		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			} else {
				throw new CodeException("Ce département n'éxiste pas !");
			}
		}
	}

}
