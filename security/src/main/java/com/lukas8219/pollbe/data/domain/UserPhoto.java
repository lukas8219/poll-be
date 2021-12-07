package com.lukas8219.pollbe.data.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_photos")
@Getter
@Setter
public class UserPhoto extends FileStorage {

    @OneToOne
    private User user;

}
