package br.com.udemy.converter;

import br.com.udemy.converter.mocks.MockPerson;
import br.com.udemy.data.models.Person;
import br.com.udemy.data.vo.PersonVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DozerConverterTest {
    MockPerson inputObject;

    @Before
    public void setUp() {
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToVOtest() {
        PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);
        Assert.assertEquals(Long.valueOf(0L), output.getKey());
        Assert.assertEquals("First name test0", output.getFirstName());
        Assert.assertEquals("Last name test0", output.getLastName());
        Assert.assertEquals("Address test0", output.getAddress());
        Assert.assertEquals("M", output.getGender());
    }

    @Test
    public void parseEntityListToVOListtest() {
        List<PersonVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), PersonVO.class);
        PersonVO outputZero = outputList.get(0);

        Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
        Assert.assertEquals("First name test0", outputZero.getFirstName());
        Assert.assertEquals("Last name test0", outputZero.getLastName());
        Assert.assertEquals("Address test0", outputZero.getAddress());
        Assert.assertEquals("M", outputZero.getGender());

        PersonVO outputSeven = outputList.get(7);

        Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
        Assert.assertEquals("First name test7", outputSeven.getFirstName());
        Assert.assertEquals("Last name test7", outputSeven.getLastName());
        Assert.assertEquals("Address test7", outputSeven.getAddress());
        Assert.assertEquals("F", outputSeven.getGender());

        PersonVO outputTwelve = outputList.get(12);

        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        Assert.assertEquals("First name test12", outputTwelve.getFirstName());
        Assert.assertEquals("Last name test12", outputTwelve.getLastName());
        Assert.assertEquals("Address test12", outputTwelve.getAddress());
        Assert.assertEquals("M", outputTwelve.getGender());
    }

    @Test
    public void parseVOToEntitytest() {
        Person output = DozerConverter.parseObject(inputObject.mockVO(), Person.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("First name test0", output.getFirstName());
        Assert.assertEquals("Last name test0", output.getLastName());
        Assert.assertEquals("Address test0", output.getAddress());
        Assert.assertEquals("M", output.getGender());
    }

    @Test
    public void parserVOListToEntityListtest() {
        List<Person> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Person.class);
        Person outputZero = outputList.get(0);

        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("First name test0", outputZero.getFirstName());
        Assert.assertEquals("Last name test0", outputZero.getLastName());
        Assert.assertEquals("Address test0", outputZero.getAddress());
        Assert.assertEquals("M", outputZero.getGender());

        Person outputSeven = outputList.get(7);

        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("First name test7", outputSeven.getFirstName());
        Assert.assertEquals("Last name test7", outputSeven.getLastName());
        Assert.assertEquals("Address test7", outputSeven.getAddress());
        Assert.assertEquals("F", outputSeven.getGender());

        Person outputTwelve = outputList.get(12);

        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("First name test12", outputTwelve.getFirstName());
        Assert.assertEquals("Last name test12", outputTwelve.getLastName());
        Assert.assertEquals("Address test12", outputTwelve.getAddress());
        Assert.assertEquals("M", outputTwelve.getGender());
    }
}
