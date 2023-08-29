package com.example.BankExample.model;

import com.example.BankExample.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String fileName;

    @OneToOne(mappedBy = "image")
    private User user;

    public Image(String fileName) {
        this.fileName = fileName;
    }

    public Image(String fileName, User user) {
        this.fileName = fileName;
        this.user = user;
    }
}
