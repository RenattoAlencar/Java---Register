package com.development.project.controller;


import com.development.project.domain.User;
import com.development.project.request.UserPostRequests;
import com.development.project.request.UserPutRequest;
import com.development.project.service.UserService;
import com.development.project.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
@Log4j2

public class UserController {

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<User>> list(Pageable pageable) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(service.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<User>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserPostRequests userPostRequests) {

        return new ResponseEntity<>(service.save(userPostRequests), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity replace(@RequestBody UserPutRequest userPutRequest) {
        service.replace(userPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
