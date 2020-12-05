package br.com.udemy.converter.mocks;

import br.com.udemy.data.models.Person;
import br.com.udemy.data.vo.PersonVO;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {
    public Person mockEntity() {
        return mockEntity(0);
    }

    public PersonVO mockVO() {
        return mockVO(0);
    }

    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<>();
        for (int i=0; i<14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonVO> mockVOList() {
        List<PersonVO> persons = new ArrayList<>();
        for (int i=0; i<14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }

    private Person mockEntity(Integer number) {
        Person p = new Person();
        p.setAddress("Address test" + number);
        p.setFirstName("First name test" + number);
        p.setLastName("Last name test" + number);
        p.setGender(((number%2) == 0) ? "M" : "F");
        p.setId(number.longValue());
        return p;
    }

    private PersonVO mockVO(Integer number) {
        PersonVO p = new PersonVO();
        p.setAddress("Address test" + number);
        p.setFirstName("First name test" + number);
        p.setLastName("Last name test" + number);
        p.setGender(((number%2) == 0) ? "M" : "F");
        p.setKey(number.longValue());
        return p;
    }
}
