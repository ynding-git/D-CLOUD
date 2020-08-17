package com.ynding.cloud.person.data;

import com.ynding.cloud.person.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author dyn
 * @version 2020/1/20
 */
public interface UserRepository extends MongoRepository<User,Long> {

    User findByUsername(String username);
}
