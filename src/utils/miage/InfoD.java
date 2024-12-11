package utils.miage;

import java.lang.annotation.*;


@Documented
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface InfoD {

    enum ETAT_COMPLETUDE {
        DRAFT_PARTIEL,
        DRAFT_COMPLET,
        VERSION_FINALISE
    }

    ETAT_COMPLETUDE etat_competude() default ETAT_COMPLETUDE.DRAFT_PARTIEL;

    boolean tested() default false;

    boolean generated() default false;

}
