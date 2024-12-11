import utils.miage.AnalyseurDeClasse;

public class Main {


    public static void main(String[] args) throws ClassNotFoundException {

        String testClass = "java.util.ArrayList";

        // String testClassSecond = personnes.getClass().getName();

        // AnalyseurDeClasse.analyseClasse("utils.tests.Personne", "");
        AnalyseurDeClasse.analyseClasse("utils.tests.TestClass", "");

        // System.out.println(AnalyseurDeClasse.getClasse(testClass));

        // AnalyseurDeClasse.affichePackageDeLaClasse( AnalyseurDeClasse.getClasse(testClass), "2" );

        // AnalyseurDeClasse.afficheConstructeurs(AnalyseurDeClasse.getClasse(testClassSecond), "  ");

        //AnalyseurDeClasse.afficheAttributs(AnalyseurDeClasse.getClasse(testClassSecond), "   ");

    }
}
