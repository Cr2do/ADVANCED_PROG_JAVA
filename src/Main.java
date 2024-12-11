import utils.miage.AnalyseurDeClasse;
import utils.miage.TD4.Promotion2024;

public class Main {


    public static void main(String[] args) throws ClassNotFoundException {

        String testClass = "java.util.ArrayList";

        // String testClassSecond = personnes.getClass().getName();

        // AnalyseurDeClasse.analyseClasse("utils.tests.Personne", "");
        AnalyseurDeClasse.analyseClasse("utils.tests.TestClass", "");


        Promotion2024 promotion = new Promotion2024();

        // Sauvegarde
        String fileName = "exercice1Etudiants.ser";
        promotion.saveEtudiants(fileName);

        // Vérification avant désérialisation
        System.out.println("Avant désérialisation :");
        promotion.afficherEtudiants();
        System.out.println("Root étudiant : " + promotion.getRootEtudiant());
        System.out.println("Root adresse : " + promotion.getRootAdresse());

        // Désérialisation
        promotion.retrieveEtudiants(fileName);



        // Vérification après désérialisation
        System.out.println("\nAprès désérialisation :");
        promotion.afficherEtudiants();
        System.out.println("Root étudiant : " + promotion.getRootEtudiant());
        System.out.println("Root adresse : " + promotion.getRootAdresse());

        // System.out.println(AnalyseurDeClasse.getClasse(testClass));

        // AnalyseurDeClasse.affichePackageDeLaClasse( AnalyseurDeClasse.getClasse(testClass), "2" );

        // AnalyseurDeClasse.afficheConstructeurs(AnalyseurDeClasse.getClasse(testClassSecond), "  ");

        //AnalyseurDeClasse.afficheAttributs(AnalyseurDeClasse.getClasse(testClassSecond), "   ");

    }
}
