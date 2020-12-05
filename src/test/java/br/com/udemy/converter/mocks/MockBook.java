package br.com.udemy.converter.mocks;

import br.com.udemy.data.models.Book;
import br.com.udemy.data.vo.BookVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {
    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVO() {
        return mockVO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> persons = new ArrayList<>();
        for (int i=0; i<14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> persons = new ArrayList<>();
        for (int i=0; i<14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }

    private Book mockEntity(Integer number) {
        Book p = new Book();
        p.setAuthor("Author" + number);
        p.setTitle("Title" + number);
        p.setLaunchDate(new Date());
        p.setPrice(10 + number);
        p.setId(number.longValue());
        return p;
    }

    private BookVO mockVO(Integer number) {
        BookVO p = new BookVO();
        p.setAuthor("Author" + number);
        p.setTitle("Title" + number);
        p.setLaunchDate(new Date());
        p.setPrice(10 + number);
        p.setKey(number.longValue());
        return p;
    }
}
