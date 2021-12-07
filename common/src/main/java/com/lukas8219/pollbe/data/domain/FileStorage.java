package com.lukas8219.pollbe.data.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FileStorage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "folder")
    private String folder;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "uploaded_at")
    private String uploadedAt;

}
