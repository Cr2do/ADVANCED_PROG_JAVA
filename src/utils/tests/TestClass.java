package utils.tests;

import utils.miage.InfoD;
import utils.miage.InfoG;

@InfoG(nom = "Dupont", prenom = "Alice", anneeUniversitaire = 2024, module = "Java Avancé", seanceTD = 1)
public class TestClass {

    @InfoG(nom = "Dupont", prenom = "Alice", anneeUniversitaire = 2024, module = "Java Avancé", seanceTD = 1)
    private String field1;

    @InfoG(nom = "Dupont", prenom = "Alice", anneeUniversitaire = 2024, module = "Java Avancé", seanceTD = 2)
    private String field2;

    @InfoG(nom = "Dupont", prenom = "Alice", anneeUniversitaire = 2023, module = "Java Avancé", seanceTD = 1)
    private String field3; // Année différente, ne devrait pas être incluse

    @InfoG(nom = "finalizedMethod", prenom = "finalizedMethod", anneeUniversitaire = 2024, module = "Java Avance", seanceTD = 1)
    @InfoD(etat_competude = InfoD.ETAT_COMPLETUDE.VERSION_FINALISE, tested = true, generated = false)
    public void finalizedMethod() {
        // Méthode finalisée et testée
    }

    @InfoD(etat_competude = InfoD.ETAT_COMPLETUDE.DRAFT_PARTIEL, tested = false, generated = true)
    public void draftGeneratedMethod() {
        // Méthode en draft partiel et générée
    }

    @InfoD(etat_competude = InfoD.ETAT_COMPLETUDE.DRAFT_COMPLET, tested = false, generated = false)
    public void draftNonGeneratedMethod() {
        // Méthode en draft complet et non générée
    }

    @InfoG(nom = "Dupont", prenom = "Alice", anneeUniversitaire = 2024, module = "Java Avancé", seanceTD = 3)
    public void additionalMethod() {
        // Méthode annotée pour l'année 2024
    }
}
