package presentation.models;

import java.util.Arrays;

public enum Sexe {

    HOMME, FEMME;

    public static Sexe getSexe(String sexe){
        return Arrays.stream(Sexe.values())
                .filter(s -> s.toString().equals(sexe))
                .findFirst()
                .orElse(null);
    }
}
