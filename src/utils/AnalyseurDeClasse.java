package utils;
import java.lang.reflect.*;

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

    Class<?> cl = Class.forName(nomClasse);  // Code � �crire
    
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
    System.out.println(" Class : " + nomClasse);
    return Class.forName(nomClasse);
  }

  public static void affichePackageDeLaClasse(Class<?> cl, String decalage) {
	 // Affiche le package de la classe : package.... 
     // Code � �crire
    System.out.println( "Package : " + cl.getPackage().getName());
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

    /**
     *
     */

    System.out.print(decalage);

    // Affichage des modificateurs de la classe (public, abstract, final, etc.)
    int modifiers = cl.getModifiers();
    if (modifiers != 0) {
      System.out.print(Modifier.toString(modifiers) + " ");
    }

    // Type de la classe : "class" pour une classe classique, "interface" pour une interface, etc.
    if (cl.isInterface()) {
      System.out.print("interface ");
    } else if (Modifier.isAbstract(modifiers)) {
      System.out.print("abstract class ");
    } else {
      System.out.print("class ");
    }

    // Nom de la classe
    System.out.print(cl.getSimpleName());

    // Récupération de la superclasse
    Class<?> superClass = cl.getSuperclass();
    if (superClass != null && superClass != Object.class) {
      // Affiche "extends NomSuperClasse" si la superclasse est différente de Object
      System.out.print(" extends " + superClass.getSimpleName());
    }

    // Récupération des interfaces implémentées par la classe
    Class<?>[] interfaces = cl.getInterfaces();
    if (interfaces.length > 0) {
      System.out.print(" implements ");
      for (int i = 0; i < interfaces.length; i++) {
        System.out.print(interfaces[i].getSimpleName());
        if (i < interfaces.length - 1) {
          System.out.print(", ");
        }
      }
    }

    // Accolade ouvrante pour la déclaration de la classe
    System.out.println(" {");

  }

  public static void afficheInnerClasses (Class<?> cl, String decalage) throws ClassNotFoundException {
    // Affiche les classes imbriqu�es statiques ou pas (� faire apr�s avoir fait fonctionner le reste)
	// Code � �crire
    Class<?>[] innerClasses = cl.getDeclaredClasses();

    // Vérifie s'il y a des classes internes à afficher
    if (innerClasses.length == 0) {
      System.out.println(decalage + "Aucune classe imbriquée trouvée.");
      return;
    }

    // Parcourt chaque classe interne
    for (Class<?> innerClass : innerClasses) {

      analyseClasse(innerClass.getName(), " ");

      // Vérifie si la classe interne est statique
      // boolean isStatic = java.lang.reflect.Modifier.isStatic(innerClass.getModifiers());

      // Affiche le nom de la classe interne avec indication si elle est statique ou non
//      System.out.println(decalage + "Classe interne : " + innerClass.getSimpleName() +
//              (isStatic ? " (statique)" : " (non statique)"));
    }
  }
  
  public static void afficheAttributs(Class<?> cl, String decalage) {
	// Affichage des attributs de la classe : utilise afficheTypeBasic ou afficheTypeFull
	// Code � �crire
    Field[] fields = cl.getDeclaredFields();

    for (Field field : fields) {
      // Affiche le décalage pour l'indentation
      System.out.print(decalage);

      // Affiche les modificateurs du champ (ex : private, public, static)
      int modifiers = field.getModifiers();
      if (modifiers != 0) {
        System.out.print(Modifier.toString(modifiers) + " ");
      }

      // Vérifie si le champ a un type générique
      Type genericType = field.getGenericType();
      if (genericType instanceof ParameterizedType) {
        // Appelle afficheTypeFull pour afficher le type avec les paramètres génériques
        afficheTypeFull(field.getType(), genericType, decalage);
      } else {
        // Appelle afficheTypeBasic pour afficher le type de base
        afficheTypeBasic(field.getType(), decalage);
      }

      // Affiche le nom du champ
      System.out.println(" " + field.getName() + ";");
    }

  }

  private static void afficheTypeBasic(Class<?> cl, String decalage) {
	// Affichage du type de l'attribut : Ne prend pas en compte l'instanciation de types g�n�riques (variante simple)
	// Code � �crire
    // System.out.println("NAME* :" + cl.getName());
    if (cl.isArray()) {
      System.out.println( decalage + cl.getComponentType().getTypeName() + " []");
    } else {
      System.out.println( decalage + cl.getTypeName());
    }
  }

  private static void afficheTypeFull(Class<?> t, Type gt, String decalage) {
	// Affichage du type de l'attribut : Prend en compte l'instanciation de types g�n�riques (variante plus complexe)
	// Code � �crire
    if (t.isArray()) {
      // Gère le cas des tableaux
      System.out.println(decalage + t.getComponentType().getTypeName() + "[]");
    } else if (gt instanceof ParameterizedType) {
      // Gère le cas des types génériques paramétrés
      ParameterizedType paramType = (ParameterizedType) gt;

      // Affiche le nom de la classe de base
      System.out.print(decalage + t.getSimpleName());

      // Gère les paramètres de type générique
      Type[] typeArgs = paramType.getActualTypeArguments();
      System.out.print("<");
      for (int i = 0; i < typeArgs.length; i++) {
        // Affiche chaque type générique
        if (typeArgs[i] instanceof Class<?>) {
          System.out.print(((Class<?>) typeArgs[i]).getSimpleName());
        } else {
          System.out.print(typeArgs[i].getTypeName());  // Pour les types plus complexes
        }
        if (i < typeArgs.length - 1) {
          System.out.print(", ");
        }
      }
      System.out.println(">");
    } else {
      // Si ce n'est pas un type générique ni un tableau, affiche le type de base
      System.out.println(decalage + t.getTypeName());
    }
  }
  
  public static void afficheConstructeurs(Class<?> cl, String decalage) {
	// Affichage des constructeurs de la classe : utilise afficheParamsBasic ou afficheParamsFull
	// Code � �crire
    Constructor<?>[] constructors = cl.getConstructors();
    for (Constructor<?> constructor : constructors) {
      System.out.println( decalage + "Constructeur : " + constructor.getName() + ", Paramètres : " + constructor.getParameterCount());
    }
  }
  
  public static void afficheMethodes(Class<?> cl, String decalage) {
	// Affichage des m�thodes de la classe : utilise afficheParamsBasic ou afficheParamsFull
	// Code � �crire
    Method[] methods = cl.getDeclaredMethods();

    // Parcourt chaque méthode pour afficher ses paramètres
    for (Method method : methods) {
      System.out.println(decalage + "Méthode : " + method.getName());

      // Récupère les types de paramètres
      Class<?>[] params = method.getParameterTypes();
      Type[] gparams = method.getGenericParameterTypes();

      // Vérifie si un paramètre est générique pour choisir entre Basic ou Full
      boolean hasGenericParam = false;
      for (Type gparam : gparams) {
        if (gparam instanceof ParameterizedType) {
          hasGenericParam = true;
          break;
        }
      }

      // Appelle la méthode appropriée
      if (hasGenericParam) {
        afficheParamsFull(params, gparams, decalage + "  ");
      } else {
        afficheParamsBasic(params, decalage + "  ");
      }
    }
  }

  private static void afficheParamsBasic(Class<?>[] params, String decalage) {
	// Affichage du type de chaque param�tre : Ne prend pas en compte l'instanciation de types g�n�riques (variante simple)
	// Code � �crire
    for (Class<?> param : params) {
      // Affiche le type de paramètre avec le décalage
      System.out.println(decalage + param.getSimpleName());
    }
  }

  private static void afficheParamsFull(Class<?>[] params, Type[] gparams, String decalage) {
	// Affichage du type de chaque param�tre : Prend en compte l'instanciation de types g�n�riques (variante plus complexe)  
	// Code � �crire
    for (int i = 0; i < params.length; i++) {
      // Affiche le type de base du paramètre
      System.out.print(decalage + params[i].getSimpleName());

      // Vérifie si le type générique est paramétré (ex : List<String>)
      if (gparams[i] instanceof ParameterizedType) {
        ParameterizedType paramType = (ParameterizedType) gparams[i];
        // Récupère les arguments de type (par ex., <String> pour List<String>)
        Type[] typeArgs = paramType.getActualTypeArguments();
        System.out.print("<");
        for (int j = 0; j < typeArgs.length; j++) {

          if (typeArgs[j] instanceof Class<?>) {
            System.out.print(((Class<?>) typeArgs[j]).getSimpleName());
          } else {
            System.out.print(typeArgs[j].getTypeName());
          }
          // check si on doit
          if (j < typeArgs.length - 1) {
            System.out.print(", ");
          }
        }
        System.out.print(">");
      }

      System.out.println(); // Passe à la ligne suivante après chaque paramètre
    }
   }

  public static void main(String[] args) {
	// Code � �crire : :
	// R�cup�rer le nom de la classe dans 'args'
	// Lancement de l'analyse de la classe
  }
}