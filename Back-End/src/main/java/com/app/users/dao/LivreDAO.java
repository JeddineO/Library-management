package com.app.users.dao;

import com.app.users.bo.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreDAO extends JpaRepository<Livre,Integer> {
}
