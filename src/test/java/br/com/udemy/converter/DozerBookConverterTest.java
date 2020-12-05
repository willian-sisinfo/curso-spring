package br.com.udemy.converter;

import br.com.udemy.converter.mocks.MockBook;
import br.com.udemy.data.models.Book;
import br.com.udemy.data.vo.BookVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DozerBookConverterTest {
    MockBook inputObject;

    @Before
    public void setUp() {
        inputObject = new MockBook();
    }

    @Test
    public void parseEntityToVOtest() {
        BookVO output = DozerConverter.parseObject(inputObject.mockEntity(), BookVO.class);
        Assert.assertEquals(Long.valueOf(0L), output.getKey());
        Assert.assertEquals("Title0", output.getTitle());
        Assert.assertEquals("Author0", output.getAuthor());
        Assert.assertEquals(10.00, output.getPrice(), 0.5);
    }

    @Test
    public void parseEntityListToVOListtest() {
        List<BookVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), BookVO.class);
        BookVO outputZero = outputList.get(0);

        Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
        Assert.assertEquals("Title0", outputZero.getTitle());
        Assert.assertEquals("Author0", outputZero.getAuthor());
        Assert.assertEquals(10.00, outputZero.getPrice(), 0.5);

        BookVO outputSeven = outputList.get(7);

        Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
        Assert.assertEquals("Title7", outputSeven.getTitle());
        Assert.assertEquals("Author7", outputSeven.getAuthor());
        Assert.assertEquals(17.00, outputSeven.getPrice(), 0.5);

        BookVO outputTwelve = outputList.get(12);

        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        Assert.assertEquals("Title12", outputTwelve.getTitle());
        Assert.assertEquals("Author12", outputTwelve.getAuthor());
        Assert.assertEquals(22.00, outputTwelve.getPrice(), 0.5);
    }

    @Test
    public void parseVOToEntitytest() {
        Book output = DozerConverter.parseObject(inputObject.mockVO(), Book.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("Title0", output.getTitle());
        Assert.assertEquals("Author0", output.getAuthor());
        Assert.assertEquals(10.00, output.getPrice(), 0.5);
    }

    @Test
    public void parserVOListToEntityListtest() {
        List<Book> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Book.class);
        Book outputZero = outputList.get(0);

        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("Title0", outputZero.getTitle());
        Assert.assertEquals("Author0", outputZero.getAuthor());
        Assert.assertEquals(10.00, outputZero.getPrice(), 0.5);

        Book outputSeven = outputList.get(7);

        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("Title7", outputSeven.getTitle());
        Assert.assertEquals("Author7", outputSeven.getAuthor());
        Assert.assertEquals(17.00, outputSeven.getPrice(), 0.5);

        Book outputTwelve = outputList.get(12);

        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("Title12", outputTwelve.getTitle());
        Assert.assertEquals("Author12", outputTwelve.getAuthor());
        Assert.assertEquals(22.00, outputTwelve.getPrice(), 0.5);
    }
}
