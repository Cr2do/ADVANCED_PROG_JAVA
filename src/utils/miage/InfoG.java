package utils.miage;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface InfoG {

    String nom();

    String prenom();

    int anneeUniversitaire();

    String module();

    int seanceTD();

}
