package utils.annotations;

import java.lang.annotation.Documented;

@Deprecated
public class Empty {

    int emptyVar;

    @Deprecated
    String emptyVar2;

    @Deprecated
    public Empty(int emptyVar, String emptyVar2) {
        this.emptyVar = emptyVar;
        this.emptyVar2 = emptyVar2;
    }





}
