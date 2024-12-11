package utils.miage;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

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

//    affichePackageDeLaClasse(cl, decalage);
//
//    afficheEnTeteClasse(cl, decalage);
//
//    System.out.println(decalage + "// Inner Classes");
//    afficheInnerClasses(cl, decalage);
//
//    System.out.println(decalage + "// Champs");
//    afficheAttributs(cl, decalage);
//
//    System.out.println("\n" + decalage + "// Constructeurs");
//    afficheConstructeurs(cl, decalage);
//
//    System.out.println(decalage + "\n" + decalage + "// M�thodes");
//    afficheMethodes(cl, decalage);

    System.out.println(decalage + "\n" + decalage + "// Annotations Analyses");
    analyserAnnotation(cl);
    // L'accolade fermante de fin de classe !
    // System.out.println(decalage + "}");
  }

  public static Class<?> getClasse(String nomClasse) throws ClassNotFoundException {
          // Retourne la classe dont le nom est pass� en param�tre
	  // Code � �crire
    System.out.println(" Class : " + nomClasse);
    return Class.forName(nomClasse);
  }

  public static void affichePackageDeLaClasse(Class<?> cl, String decalage) {
	 // Affiche le package de la classe : package...
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

    displayAnnotations(cl.getAnnotations(), decalage);

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
        displayAnnotations(field.getAnnotations(), decalage);
      } else {
        // Appelle afficheTypeBasic pour afficher le type de base
        afficheTypeBasic(field.getType(), decalage);
        displayAnnotations(field.getAnnotations(), decalage);
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
      displayAnnotations(cl.getAnnotations(), decalage);
    } else {
      System.out.println( decalage + cl.getTypeName());
      displayAnnotations(cl.getAnnotations(), decalage);
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

  public static void displayAnnotations(Annotation[] annot, String decalage) {
    if (annot.length > 0) {
      System.out.println("// Annotation");
      for (Annotation annotation : annot) {
        System.out.println("@" + annotation.annotationType().getName() + decalage);
      }
    }
  }


  public static void analyserAnnotation(Class<?> cls) {

    List<Class<?>> classes2024 = new ArrayList<>();
    List<Field> fields2024 = new ArrayList<>();
    List<Method> methods2024 = new ArrayList<>();
    List<Field> fieldsTD1And2 = new ArrayList<>();
    List<Method> nonFinalizedMethods2024 = new ArrayList<>();

    // Vérifier les annotations de classe pour l'année 2024
    if (cls.isAnnotationPresent(InfoG.class)) {
      InfoG classAnnotation = cls.getAnnotation(InfoG.class);
      if (classAnnotation.anneeUniversitaire() == 2024) {
        classes2024.add(cls);
      }
    }

    // La liste des champs de l’année universitaire 2024
    for (Field field : cls.getDeclaredFields()) {
      if (field.isAnnotationPresent(InfoG.class)) {
        InfoG fieldAnnotation = field.getAnnotation(InfoG.class);
        if (fieldAnnotation.anneeUniversitaire() == 2024) {
          fields2024.add(field);
        }
      }
    }

    // La liste des champs de l’année universitaire 2024 pendant la seance TD1 et TD2
    for (Field field : cls.getDeclaredFields()) {
      if (field.isAnnotationPresent(InfoG.class)) {
        InfoG fieldAnnotation = field.getAnnotation(InfoG.class);
        if (fieldAnnotation.anneeUniversitaire() == 2024) {
          if (fieldAnnotation.seanceTD() == 1 || fieldAnnotation.seanceTD() == 2) {
            fieldsTD1And2.add(field);
          }
        }
      }
    }

    // La liste des méthodes de l’année universitaire 2024
    for (Method method : cls.getDeclaredMethods()) {
      if (method.isAnnotationPresent(InfoG.class)) {
        InfoG methodAnnotation = method.getAnnotation(InfoG.class);
        if (methodAnnotation.anneeUniversitaire() == 2024) {
          methods2024.add(method);
        }
      }
    }

    // La liste des méthodes non finalisées créées lors de l’année universitaire 2024
    // TODO : un peu dubitatif sur ce point en ce qui concerne le check sur l'année car une methode n'as pas de nom prenom so à voir
    for (Method method : cls.getDeclaredMethods()) {
      if (method.isAnnotationPresent(InfoG.class)) {
        InfoG methodAnnotationG = method.getAnnotation(InfoG.class);
        if (methodAnnotationG.anneeUniversitaire() == 2024) {
          if (method.isAnnotationPresent(InfoD.class)) {
            InfoD methodAnnotationD = method.getAnnotation(InfoD.class);
            if (methodAnnotationD.etat_competude() != InfoD.ETAT_COMPLETUDE.VERSION_FINALISE) {
              nonFinalizedMethods2024.add(method);
            }
          }
        }
      }
    }

    // Affichage des résultats
    System.out.println("Année universitaire 2024");
    System.out.println("Classes annotées : " + classes2024);
    System.out.println("Champs annotés : " + fields2024);
    System.out.println("Champs des TD 1 et 2 : " + fieldsTD1And2);
    System.out.println("Méthodes non finalisées : " + nonFinalizedMethods2024);

    // Calculs des ratios
    int totalMethods2024 = methods2024.size();
    int finalizedMethods = 0;
    int draftPartialMethods = 0;
    int generatedMethods = 0;
    int testedMethods = 0;


    for (Method method : methods2024) {
      if (method.isAnnotationPresent(InfoD.class)) {
        InfoD methodAnnotation = method.getAnnotation(InfoD.class);

        if (methodAnnotation.etat_competude() == InfoD.ETAT_COMPLETUDE.VERSION_FINALISE) {
          finalizedMethods++;
        } else if (methodAnnotation.etat_competude() == InfoD.ETAT_COMPLETUDE.DRAFT_PARTIEL) {
          draftPartialMethods++;
        }

        if (methodAnnotation.generated()) {
          generatedMethods++;
        }

        if (methodAnnotation.tested()) {
          testedMethods++;
        }
      }
    }

    int nonGeneratedMethods = totalMethods2024 - generatedMethods;

    System.out.println("totalMethods2024 " + totalMethods2024);
    System.out.println("finalizedMethods " + finalizedMethods);
    System.out.println("draftPartialMethods " + draftPartialMethods);
    System.out.println("generatedMethods " + generatedMethods);
    System.out.println("testedMethods " + testedMethods);

    System.out.println("***********************************************");



    System.out.println("totalMethods2024 " + totalMethods2024);
    System.out.println("finalizedMethods " + finalizedMethods);
    System.out.println("draftPartialMethods " + draftPartialMethods);
    System.out.println("generatedMethods " + generatedMethods);
    System.out.println("testedMethods " + testedMethods);

    System.out.println("***********************************************");


    System.out.println("Ratio finalisées / total : " + (totalMethods2024 > 0 ? (double) finalizedMethods / totalMethods2024 : 0));
    System.out.println("Ratio draft partiel / total : " + (totalMethods2024 > 0 ? (double) draftPartialMethods / totalMethods2024 : 0));
    System.out.println("Ratio générées / total : " + (totalMethods2024 > 0 ? (double) generatedMethods / totalMethods2024 : 0));
    System.out.println("Ratio finalisées / non générées : " + (nonGeneratedMethods > 0 ? (double) finalizedMethods / nonGeneratedMethods : 0));
    System.out.println("Ratio testées / non générées : " + (nonGeneratedMethods > 0 ? (double) testedMethods / nonGeneratedMethods : 0));

  }

  public static void main(String[] args) {
	// Code � �crire : :
	// R�cup�rer le nom de la classe dans 'args'
	// Lancement de l'analyse de la classe
  }
}