package utils;

import java.lang.reflect.Field;

public class GenericToString {

    public String toString (Object o, int profondeur) {

        Field[] fields = o.getClass().getFields();

        for (Field field : fields) {
            String fieldName = field.getName();
        }

        return o.toString();

    }

}
