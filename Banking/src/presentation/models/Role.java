package presentation.models;

import java.util.Arrays;

public enum Role {
    CLIENT, ADMIN;

    public static Role getRole(String role){
        return Arrays.stream(Role.values())
                .filter(r -> r.toString().equals(role))
                .findFirst()
                .orElse(null);
    }
}
