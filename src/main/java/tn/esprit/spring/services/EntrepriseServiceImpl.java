package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
	EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	private static final Logger l = LogManager.getLogger(EntrepriseServiceImpl.class);

	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		l.info("**********L'entrprise est ajouté avec succés " + entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		l.info("**********Le département est ajouté avec succés " + dep);
		return dep.getId();
	}

	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		// Le bout Master de cette relation N:1 est departement
		// donc il faut rajouter l'entreprise a departement
		// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
		// Rappel : la classe qui contient mappedBy represente le bout Slave
		// Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		Departement depManagedEntity = deptRepoistory.findById(depId).get();

		depManagedEntity.setEntreprise(entrepriseManagedEntity);
		deptRepoistory.save(depManagedEntity);

	}

	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		l.info("*******La liste des départements est : ");
		for (Departement dep : entrepriseManagedEntity.getDepartements()) {
			depNames.add(dep.getName());
		}
		if (depNames.isEmpty()) {
			l.error("vide********");
		} else {
			l.info(depNames + "*********");
		}
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		if (entrepriseId < 0) {
			l.error("*******Error,Invalide identifier*******");
		} else {
			l.error("L'entreprise avec l'identifinat " + entrepriseId + "est supprimé********");
		}
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		if (depId < 0) {
			l.debug("*******Error,Invalide identifier********");
		} else {
			l.error("Le département avec l'identifinat " + depId + "est supprimé********");
		}
		deptRepoistory.delete(deptRepoistory.findById(depId).get());
	}

	public Entreprise getEntrepriseById(int entrepriseId) {
		if(entrepriseId<0) {
			l.error("*******Error,Invalide identifier");
		}
		else {
			l.info("*****Le nom du l'entrprise est " + entrepriseRepoistory.findById(entrepriseId).get().getName()+ "********");

		}
		return entrepriseRepoistory.findById(entrepriseId).get();
	}

}
