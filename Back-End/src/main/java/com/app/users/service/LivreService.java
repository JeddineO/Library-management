package com.app.users.service;

import com.app.users.bo.Livre;
import com.app.users.dao.LivreDAO;
import com.app.users.dto.LivreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LivreService implements ILivreService{

    @Autowired
    private LivreDAO dao;


    @Override
    public LivreDTO add(LivreDTO livre) {
        livre.setDisponible(true);
        return fromLivre(dao.save(toLivre(livre)));
    }

    @Override
    public LivreDTO remove(int id) {
        Optional<Livre> livre = dao.findById(id);
        if(livre.isPresent())
            dao.deleteById(id);
        else
            throw new RuntimeException("livre not found");
        return  this.fromLivre(livre.get());
    }

    @Override
    public LivreDTO update(LivreDTO livre, int id) {
        Optional<Livre> oldLivre = dao.findById(id);
        if(oldLivre.isPresent()){
            LivreDTO updatedLivre = this.fromLivre(oldLivre.get());
            updatedLivre.setNom(livre.getNom());
            updatedLivre.setAuteur(livre.getAuteur());
            updatedLivre.setPrix(livre.getPrix());
            dao.save(this.toLivre(updatedLivre));
            return updatedLivre;
        }
        else
            throw new RuntimeException("User not found");
    }

    @Override
    public List<LivreDTO> findAll() {
        return  dao.findAll().stream()
                .map(u->fromLivre(u))
                .collect(Collectors.toList());
    }

    @Override
    public LivreDTO findLivre(int id) {
        Optional<Livre> livre = dao.findById(id);
        if (!livre.isEmpty())
            return fromLivre(livre.get());
        return null;
    }

    public Livre toLivre(LivreDTO livre)
    {
        return Livre.builder()
                .nom(livre.getNom())
                .disponible(livre.isDisponible())
                .isbn(livre.getIsbn())
                .prix(livre.getPrix())
                .auteur(livre.getAuteur())
                .build();
    }

    public LivreDTO fromLivre(Livre livre)
    {
        return LivreDTO.builder()
                .nom(livre.getNom())
                .disponible(livre.isDisponible())
                .isbn(livre.getIsbn())
                .auteur(livre.getAuteur())
                .prix(livre.getPrix())
                .build();
    }
}
