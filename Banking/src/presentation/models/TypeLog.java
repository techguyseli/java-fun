package presentation.models;

import java.util.Arrays;

public enum TypeLog {

    VIREMENT, VERSEMENT, RETRAIT, CREATION;

    public static TypeLog getTypeLog(String value){
        return Arrays.stream(TypeLog.values())
                .filter(T -> T.toString().equals(value))
                .findFirst()
                .orElse(null);
    }

}
