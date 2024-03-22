package com.app.users.service;

import com.app.users.dto.ClientDTO;

import java.util.List;

public interface IUserService {


    public ClientDTO add(ClientDTO user);
    public ClientDTO remove(int id);
    public ClientDTO update(ClientDTO user, int id);
    public List<ClientDTO> findAll();
    public ClientDTO findUser(int id);
}
