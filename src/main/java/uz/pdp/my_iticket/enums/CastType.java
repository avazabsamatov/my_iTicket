package uz.pdp.my_iticket.enums;

import java.util.Arrays;
import java.util.Optional;

public enum CastType {
    CAST_ACTOR("actor"),
    CAST_DIRECTOR("director"),
    CAST_OTHER("other");

    final String displayName;

    CastType(String displayName) {
        this.displayName = displayName;
    }

    public static CastType getCastDisplayType(String displayName){
        Optional<CastType> castType1 = Arrays.stream(CastType.values()).findFirst().filter(castType -> castType.displayName.equals(displayName.toLowerCase()));
        return castType1.orElse(CastType.CAST_OTHER);

    }
}
