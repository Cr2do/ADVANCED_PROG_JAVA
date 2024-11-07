package utils;

/**
 * @version 1.2 - 5 novembre 2024
 * @author Michel Buffa + am�lioration de Philippe Lahire
 * Inspire par la classe Reflectiontest.java de
 * Cay S. Horstmann & Gary Cornell, publiee dans le livre Core Java, Sun Press
 */

// import des packages n�cessaires

public class AnalyseurDeClasse {

  public static void analyseClasse(String nomClasse, String decalage) throws ClassNotFoundException {
	// R�cup�ration d'un objet de type Class correspondant au nom pass� en param�tre et analyse en plusieurs �tapes

    Class<?> cl =  // Code � �crire
    
    affichePackageDeLaClasse(cl, decalage); 
   
    afficheEnTeteClasse(cl, decalage);
    
    System.out.println(decalage + "// Inner Classes");
    afficheInnerClasses(cl, decalage);

    System.out.println(decalage + "// Champs");
    afficheAttributs(cl, decalage);

    System.out.println("\n" + decalage + "// Constructeurs");
    afficheConstructeurs(cl, decalage);

    System.out.println(decalage + "\n" + decalage + "// M�thodes");
    afficheMethodes(cl, decalage);

    // L'accolade fermante de fin de classe !
    System.out.println(decalage + "}");
  }

  public static Class<?> getClasse(String nomClasse) throws ClassNotFoundException {
          // Retourne la classe dont le nom est pass� en param�tre
	  // Code � �crire
  }

  public static void affichePackageDeLaClasse(Class<?> cl, String decalage) {
	 // Affiche le package de la classe : package.... 
         // Code � �crire
  }

  public static void afficheEnTeteClasse(Class<?> cl, String decalage) {
	// Affiche l'ent�te d'une classe
        // Cette m�thode affiche par ex "public class Toto extends Tata implements Titi, Tutu {"

	 // Code � �crire :

	//  Affichage du modifier et du nom de la classe  	  
	// R�cup�ration de la superclasse si elle existe (null si cl est le type Object)
	// On ecrit le "extends " que si la superclasse est non nulle et diff�rente de Object
   	// Affichage des interfaces que la classe implemente (affichage de "implements" s'il y a des interfaces � impl�menter)
	// Accolade ouvrante du d�but de la classe !
   }

  public static void afficheInnerClasses (Class<?> cl, String decalage) throws ClassNotFoundException {
         // Affiche les classes imbriqu�es statiques ou pas (� faire apr�s avoir fait fonctionner le reste)
	// Code � �crire
  }
  
  public static void afficheAttributs(Class<?> cl, String decalage) {
	// Affichage des attributs de la classe : utilise afficheTypeBasic ou afficheTypeFull
	// Code � �crire
  }

  private static void afficheTypeBasic(Class<?> cl, String decalage) {
	// Affichage du type de l'attribut : Ne prend pas en compte l'instanciation de types g�n�riques (variante simple)
	// Code � �crire
    }

  private static void afficheTypeFull(Class<?> t, Type gt, String decalage) {
	// Affichage du type de l'attribut : Prend en compte l'instanciation de types g�n�riques (variante plus complexe)
	// Code � �crire
   }
  
  public static void afficheConstructeurs(Class<?> cl, String decalage) {
	// Affichage des constructeurs de la classe : utilise afficheParamsBasic ou afficheParamsFull
	// Code � �crire
  }
  
  public static void afficheMethodes(Class<?> cl, String decalage) {
	// Affichage des m�thodes de la classe : utilise afficheParamsBasic ou afficheParamsFull
	// Code � �crire
  }

  private static void afficheParamsBasic(Class<?>[] params, String decalage) {
	// Affichage du type de chaque param�tre : Ne prend pas en compte l'instanciation de types g�n�riques (variante simple)
	// Code � �crire
   }

  private static void afficheParamsFull(Class<?>[] params, Type[] gparams, String decalage) {
	// Affichage du type de chaque param�tre : Prend en compte l'instanciation de types g�n�riques (variante plus complexe)  
	// Code � �crire
   }

  public static void main(String[] args) {
	// Code � �crire : :
	// R�cup�rer le nom de la classe dans 'args'
	// Lancement de l'analyse de la classe
  }
}