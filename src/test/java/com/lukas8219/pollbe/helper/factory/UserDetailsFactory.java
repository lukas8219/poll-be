package com.lukas8219.pollbe.helper.factory;

import com.lukas8219.pollbe.data.domain.PollUserDetails;

public class UserDetailsFactory {

    private UserDetailsFactory(){
    }

    public static PollUserDetails of(Long id, String email) {
        return new PollUserDetails(id, email, "1234");
    }
}
