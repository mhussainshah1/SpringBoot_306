package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface DirectorRepository extends CrudRepository<Director, Long> {
    Director findByName(String name);
}
