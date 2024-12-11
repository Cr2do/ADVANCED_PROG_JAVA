package utils.tests;

import utils.miage.InfoG;

public class Personne {
    String nom;

    @InfoG(
        nom = "Persone",
        prenom = "",
        anneeUniversitaire = 2024,
        module = "1",
        seanceTD = 2
    )
    int age;


    @InfoG(
        nom = "Persone",
        prenom = "",
        anneeUniversitaire = 2024,
        module = "1",
        seanceTD = 2
    )
    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }
}

