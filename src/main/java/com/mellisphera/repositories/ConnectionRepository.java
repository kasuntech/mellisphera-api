package com.mellisphera.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mellisphera.entities.Apiary;
import com.mellisphera.entities.Connection;

@Service
@Repository
public interface ConnectionRepository  extends MongoRepository<Connection ,String> {
	
	public List<Connection> findConnectionByIdUsername(String idUsername);
	
	public List<Connection> findByconnectionDateBetween(Date start, Date end);
}
