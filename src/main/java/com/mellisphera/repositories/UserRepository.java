package com.mellisphera.repositories;

//import com.apiwatch.entities.Login;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mellisphera.entities.User;

@Service
@Repository
public interface UserRepository extends MongoRepository<User,String>{
	
	//User findUserByLogin(Login login);
	User findUserByUsername(String username);
	
	User findUserByEmail(String email);
	//
	boolean existsByUsername(String username);
	
	//
	boolean existsByEmail(String email);
	//
	List<User> findByCreatedAtLike(String todayDate);
}

