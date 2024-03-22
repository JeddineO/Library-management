package com.app.users.dto;

import com.app.users.bo.Emprunt;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LivreDTO {

    private int isbn;
    private String nom;
    private boolean disponible;
    private float prix;
    private String auteur;

}
