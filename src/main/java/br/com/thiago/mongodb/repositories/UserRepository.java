package br.com.thiago.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.thiago.mongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
