package com.lukas8219.pollbe.data.enumeration;

import java.time.LocalDateTime;

public enum PollResultEnum {

    APPROVED,
    REFUSED,
    OCCURRING,
    TIE;

    private static PollResultEnum calculate(Long favor, Long against) {
        if (favor.equals(against)) {
            return TIE;
        }
        if (favor > against) {
            return APPROVED;
        } else {
            return REFUSED;
        }
    }

    public static PollResultEnum calculate(LocalDateTime expiresAt, Long against, Long favor) {
        if (expiresAt.isAfter(LocalDateTime.now())) {
            return PollResultEnum.OCCURRING;
        }
        return PollResultEnum.calculate(favor, against);
    }
}
