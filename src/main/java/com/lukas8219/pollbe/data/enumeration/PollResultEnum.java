package com.lukas8219.pollbe.data.enumeration;

import java.time.LocalDateTime;

public enum PollResultEnum {

    APPROVED,
    REFUSED,
    OCCURRING,
    TIE;

    public static PollResultEnum calculate(LocalDateTime expiresAt, Long against, Long favor) {
        if (expiresAt.isAfter(LocalDateTime.now())) {
            return PollResultEnum.OCCURRING;
        }

        if(expiresAt.isBefore(LocalDateTime.now())){
            if (favor.equals(against)) {
                return TIE;
            }
            if (favor > against) {
                return APPROVED;
            } else {
                return REFUSED;
            }
        } else {
            return null;
        }
    }
}
