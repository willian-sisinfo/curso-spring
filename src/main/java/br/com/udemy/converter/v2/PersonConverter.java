package br.com.udemy.converter.v2;

import br.com.udemy.data.models.Person;
import br.com.udemy.data.vo.v2.PersonVO_V2;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonConverter {

    public PersonVO_V2 convertEntityToVO(Person person) {
        PersonVO_V2 vo = new PersonVO_V2();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthDate(new Date());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        return vo;
    }

    public Person convertVOToEntity(PersonVO_V2 person) {
        Person entity = new Person();
        entity.setId(person.getId());
        //entity.setBirthDate(person.getBirthDate());
        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        return entity;
    }

}
