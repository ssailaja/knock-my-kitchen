package com.recipeworld.knockmykitchen.models.data;

import com.recipeworld.knockmykitchen.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Long> {
    // User findByName(String userName);
}
