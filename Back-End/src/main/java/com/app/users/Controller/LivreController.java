package com.app.users.Controller;

import com.app.users.dto.LivreDTO;
import com.app.users.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class LivreController {

    @Autowired
    private LivreService livreService;

    @GetMapping("/livres")
    public List<LivreDTO> getLivres() {
        return livreService.findAll();
    }

    @PostMapping("/livre")
    public LivreDTO createLivre(@RequestBody LivreDTO livre) {
        return livreService.add(livre);
    }

    @PutMapping("/livre/{id}")
    public LivreDTO updateLivre(@PathVariable(name = "id") int id, @RequestBody LivreDTO livre) {
        return livreService.update(livre, id);
    }

    @DeleteMapping("/livre/{id}")
    public LivreDTO deleteLivre(@PathVariable int id) {
        return livreService.remove(id);
    }
}
