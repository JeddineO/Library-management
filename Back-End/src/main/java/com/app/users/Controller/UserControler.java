package com.app.users.Controller;


import com.app.users.dto.ClientDTO;
import com.app.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserControler {

    @Autowired
    private UserService service;


    @GetMapping("/users")
    public List<ClientDTO> getUsers()
    {
        return service.findAll();
    }

    @PostMapping("/user")
    public ClientDTO creteuser(@RequestBody ClientDTO user)
    {
        return service.add(user);
    }

    @PutMapping("/user/{id}")
    public ClientDTO updateUser(@PathVariable(name="id") int id, @RequestBody ClientDTO user) {
        return  service.update(user, id);
    }

    @DeleteMapping("/user/{id}")
    public ClientDTO deleteUser(@PathVariable int id) {
        return service.remove(id);
    }


}
