package com.app.users.service;

import com.app.users.dto.LivreDTO;

import java.util.List;

public interface ILivreService {

    public LivreDTO add(LivreDTO user);
    public LivreDTO remove(int id);
    public LivreDTO update(LivreDTO user, int id);
    public List<LivreDTO> findAll();
    public LivreDTO findLivre(int id);
}
