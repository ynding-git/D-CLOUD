package com.ynding.cloud.book.service;

import com.ynding.cloud.book.data.BookRepository;
import com.ynding.cloud.common.model.bo.GQuery;
import com.ynding.cloud.common.model.entity.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findByReader(String reader) {

        return bookRepository.findByReader(reader);
    }

    @Transactional(readOnly = false)
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    /**
     * 查询书本列表
     *
     * @param query
     * @return
     */
    public List<Book> findList(GQuery query) {
        List<Book> books = bookRepository.findAll(condition(query));
        return books;
    }

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    public Page<Book> pageList(GQuery query) {
        Pageable pageable =  PageRequest.of(query.getOffset(), query.getLimit(), Sort.Direction.DESC, "id");

        Page<Book> page = bookRepository.findAll(condition(query),pageable);

        return page;
    }


    /**
     * 查询条件
     * @param query
     * @return
     */
    private Specification<Book> condition(GQuery query) {

        return (Root<Book> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction();
            if (query.get("author") != null)
                predicate.getExpressions().add(cb.equal(root.get("author"), query.get("author")));
            if (query.get("reader") != null)
                predicate.getExpressions().add(cb.equal(root.get("reader"), query.get("reader")));
            if (query.get("description") != null)
                predicate.getExpressions().add(cb.like(root.get("description"), "%" + query.get("description") + "%"));
            if (query.get("title") != null)
                predicate.getExpressions().add(cb.like(root.get("title"), "%" + query.get("title") + "%"));

            return predicate;
        };
    }
}
