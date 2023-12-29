package com.niit.jdp.Product_Service.repository;

import com.niit.jdp.Product_Service.domain.Songs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends MongoRepository<Songs,String> {

}
