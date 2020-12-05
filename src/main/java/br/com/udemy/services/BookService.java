package br.com.udemy.services;

import br.com.udemy.converter.DozerConverter;
import br.com.udemy.data.models.Book;
import br.com.udemy.data.vo.BookVO;
import br.com.udemy.exception.ResourceNotFoundException;
import br.com.udemy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//spring usa injeção de dependecia dessa classe onde for necessário. Assim não precisa instanciar. Basta usar @Autowired
@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public BookVO create(BookVO b) {
        var entity = DozerConverter.parseObject(b, Book.class);
        return DozerConverter.parseObject(repository.save(entity), BookVO.class);
    }

    public BookVO update(BookVO b) {
        var entity = repository.findById(b.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setPrice(b.getPrice());
        entity.setAuthor(b.getAuthor());
        entity.setTitle(b.getTitle());
        entity.setLaunchDate(b.getLaunchDate());
        return DozerConverter.parseObject(repository.save(entity), BookVO.class);
    }


    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        repository.delete(entity);
    }

    public Page<BookVO> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(this::convertToBookVO);
    }

    private BookVO convertToBookVO(Book book) {
        return DozerConverter.parseObject(book, BookVO.class);
    }

    public BookVO findById(Long id) {
        return DozerConverter.parseObject(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not records found for this id")), BookVO.class);
    }
}
