package com.app.users.bo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Entity
public class Client {
    @Id
    private String cin;
    private String nom;
    private String prenom;
    @OneToMany(mappedBy = "client")
    private List<Emprunt> emprunts;
}
