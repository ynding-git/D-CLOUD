package com.ynding.cloud.book.service;

import com.ynding.cloud.book.data.BookRepository;
import com.ynding.cloud.common.model.bo.GQuery;
import com.ynding.cloud.book.entity.Book;
import com.ynding.cloud.common.model.bo.ResponsePageBean;
import com.ynding.cloud.common.model.vo.BookVO;
import org.springframework.beans.BeanUtils;
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
import java.util.ArrayList;
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
    public Book save(BookVO bookVO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookVO,book);
        return bookRepository.save(book);
    }

    /**
     * 查询书本列表
     *
     * @param query
     * @return
     */
    public List<BookVO> findList(GQuery query) {
        List<Book> books = bookRepository.findAll(condition(query));
        List<BookVO> bookVOS = new ArrayList<>();
        books.forEach(e -> {
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(e, bookVO);
            bookVOS.add(bookVO);
        });
        return bookVOS;
    }

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    public ResponsePageBean pageList(GQuery query) {
        Pageable pageable =  PageRequest.of(query.getOffset(), query.getLimit(), Sort.Direction.DESC, "id");

        Page<Book> page = bookRepository.findAll(condition(query),pageable);

        List<Book> books = page.getContent();
        List<BookVO> bookVOS = new ArrayList<>();
        books.forEach(e -> {
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(e, bookVO);
            bookVOS.add(bookVO);
        });

        return ResponsePageBean.ok(bookVOS, page.getTotalElements());
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
