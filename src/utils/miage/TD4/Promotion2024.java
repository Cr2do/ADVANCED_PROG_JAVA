package utils.miage.TD4;

import utils.miage.TD3.Adresse;
import utils.miage.TD3.Etudiant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Promotion2024 {

    private static final long serialVersionUID = 1L;

    private List<Etudiant> etudiants;
    private transient Etudiant rootEtudiant;
    private transient Adresse rootAdresse;

    public Adresse getRootAdresse() {
        return this.rootAdresse;
    }

    public Etudiant getRootEtudiant() {
        return this.rootEtudiant;
    }

    public Promotion2024() {
        // Initialisation des étudiants
        etudiants = new ArrayList<>();

        Adresse adresse1 = new Adresse("Paris", "Rue A", 10, true);
        Etudiant etudiant1 = new Etudiant("Dupont", "Jean", 20, new java.util.Date(), "0600000000");
        etudiant1.setAdresse(adresse1);
        etudiants.add(etudiant1);

        Adresse adresse2 = new Adresse("Lyon", "Rue B", 20, false);
        Etudiant etudiant2 = new Etudiant("Durand", "Marie", 22, new java.util.Date(), "0700000000");
        etudiant2.setAdresse(adresse2);
        etudiants.add(etudiant2);

        rootEtudiant = etudiant1;
        rootAdresse = adresse1;
    }

    public void saveEtudiants(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(etudiants);
            oos.writeObject(rootEtudiant);
            oos.writeObject(rootAdresse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void retrieveEtudiants(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            etudiants = (List<Etudiant>) ois.readObject();
            rootEtudiant = (Etudiant) ois.readObject();
            rootAdresse = (Adresse) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void afficherEtudiants() {
        for (Etudiant e : etudiants) {
            System.out.println(e);
        }
    }

}
