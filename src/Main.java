import utils.AnalyseurDeClasse;
import utils.Personne;

import java.util.ArrayList;

public class Main {


    Personne[] p = new Personne[10];

    ArrayList<Personne> personnes = new ArrayList<>();


    public static void main(String[] args) throws ClassNotFoundException {

        String testClass = "java.util.ArrayList";
        // String testClassSecond = personnes.getClass().getName();
        AnalyseurDeClasse.analyseClasse("java.util.ArrayList", "");
        // System.out.println(AnalyseurDeClasse.getClasse(testClass));

        // AnalyseurDeClasse.affichePackageDeLaClasse( AnalyseurDeClasse.getClasse(testClass), "2" );

        // AnalyseurDeClasse.afficheConstructeurs(AnalyseurDeClasse.getClasse(testClassSecond), "  ");

        //AnalyseurDeClasse.afficheAttributs(AnalyseurDeClasse.getClasse(testClassSecond), "   ");

    }
}
