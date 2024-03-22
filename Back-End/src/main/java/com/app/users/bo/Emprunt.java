package com.app.users.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emprunt {
    @Id
    private int idReservation;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Livre livre;

}
