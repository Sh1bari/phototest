package com.example.phototest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class MainModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    private String contentType;

    @Lob
    private byte[] data;

}
