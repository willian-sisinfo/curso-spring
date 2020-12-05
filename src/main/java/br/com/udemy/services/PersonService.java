package br.com.udemy.services;

import br.com.udemy.converter.DozerConverter;
import br.com.udemy.converter.v2.PersonConverter;
import br.com.udemy.data.models.Person;
import br.com.udemy.data.vo.PersonVO;
import br.com.udemy.data.vo.v2.PersonVO_V2;
import br.com.udemy.exception.ResourceNotFoundException;
import br.com.udemy.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//spring usa injeção de dependecia dessa classe onde for necessário. Assim não precisa instanciar. Basta usar @Autowired
@Service
public class PersonService {

    @Autowired
    PersonRepository repository;
    @Autowired
    PersonConverter converter;

    public PersonVO create(PersonVO p) {
        var entity = DozerConverter.parseObject(p, Person.class);
        return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
    }

    public PersonVO_V2 createV2(PersonVO_V2 p) {
        var entity = converter.convertVOToEntity(p);
        return converter.convertEntityToVO(repository.save(entity));
    }

    public PersonVO update(PersonVO p) {
        var entity = repository.findById(p.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(p.getFirstName());
        entity.setLastName(p.getLastName());
        entity.setAddress(p.getAddress());
        entity.setGender(p.getGender());
        return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
    }

    public PersonVO_V2 updateV2(PersonVO_V2 p) {
        var entity = repository.findById(p.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(p.getFirstName());
        entity.setLastName(p.getLastName());
        entity.setAddress(p.getAddress());
        entity.setGender(p.getGender());
        return converter.convertEntityToVO(repository.save(entity));
    }

    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        repository.delete(entity);
    }

    public Page<PersonVO> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(this::convertToPersonVO);
    }

    private PersonVO convertToPersonVO(Person person) {
        return DozerConverter.parseObject(person, PersonVO.class);
    }

    public PersonVO findById(Long id) {
        return DozerConverter.parseObject(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not records found for this id")), PersonVO.class);
    }

    @Transactional
    public PersonVO disbalePerson(Long id) {
        repository.disablePerson(id);
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        return DozerConverter.parseObject(entity, PersonVO.class);
    }
}
