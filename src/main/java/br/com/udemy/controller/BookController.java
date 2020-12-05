package br.com.udemy.controller;

import br.com.udemy.data.vo.BookVO;
import br.com.udemy.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/book/v1")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PagedModel<BookVO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "15") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler assembler
    )  {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));
        Page<BookVO> vos = service.findAll(pageable);
        vos
                .stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel())
                );
        return new ResponseEntity<>(assembler.toModel(vos), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public BookVO findById(@PathVariable(value="id") Long id)  {
        BookVO vo = service.findById(id);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookVO create(@RequestBody BookVO book)  {
        BookVO vo = service.create(book);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookVO update(@RequestBody BookVO book)  {
        BookVO vo = service.update(book);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value="id") Long id)  {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
