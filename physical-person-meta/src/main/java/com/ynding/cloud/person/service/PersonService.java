package com.ynding.cloud.person.service;

import com.ynding.cloud.common.model.bo.GQuery;
import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.common.model.entity.book.Book;
import com.ynding.cloud.common.model.entity.person.Person;
import com.ynding.cloud.common.model.entity.person.User;
import com.ynding.cloud.person.data.PersonRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

/**
 * @author ynding
 */
@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findList(GQuery query) {
        List<Person> persons = personRepository.findAll(condition(query));
        return persons;
    }

    /**
     * 查询条件
     *
     * @param query
     * @return
     */
    private Specification<Person> condition(GQuery query) {

        return (root, cq, cb) -> {
            Predicate predicate = cb.conjunction();
            if (query.get("sex") != null) {
                predicate.getExpressions().add(cb.equal(root.get("sex"), query.get("sex")));
            }
            if (query.get("name") != null) {
                predicate.getExpressions().add(cb.like(root.get("name"), "%" + query.get("name") + "%"));
            }
            return predicate;
        };
    }


}
