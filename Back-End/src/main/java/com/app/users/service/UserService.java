package com.app.users.service;

import com.app.users.bo.Client;
import com.app.users.dao.UserDAO;
import com.app.users.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserDAO dao;


    @Override
    public ClientDTO add(ClientDTO user) {
        return fromUser(dao.save(toUser(user)));
    }
    @Override
    public ClientDTO remove(int id) {
            Optional<Client> user = dao.findById(id);
            if(user.isPresent())
                dao.deleteById(id);
            else
                throw new RuntimeException("User not found");

        return  this.fromUser(user.get());
    }
    @Override
    public ClientDTO update(ClientDTO newUserDTO, int id) {
        Optional<Client> userOld = dao.findById(id);
        if(userOld.isPresent()){
            ClientDTO userUpdated = this.fromUser(userOld.get());
            userUpdated.setNom(newUserDTO.getNom());
            dao.save(this.toUser(userUpdated));
            return userUpdated;
        }
        else
            throw new RuntimeException("User not found");
    }


    @Override
    public List<ClientDTO> findAll() {
        return  dao.findAll().stream()
                .map(u->fromUser(u))
                .collect(Collectors.toList());
    }


    @Override
    public ClientDTO findUser(int id) {
        Optional<Client> user = dao.findById(id);
        if (!user.isEmpty())
            return fromUser(user.get());
        return null;
    }


    public Client toUser(ClientDTO userDTO)
    {
        return Client.builder()
                .nom(userDTO.getNom())
                .prenom(userDTO.getPrenom())
                .build();
    }

    public ClientDTO fromUser(Client user)
    {
        return ClientDTO.builder()
                .prenom(user.getPrenom())
                .nom(user.getNom())
                .build();
    }
}
