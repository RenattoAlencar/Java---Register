package com.development.project.service;

import com.development.project.domain.User;
import com.development.project.exception.BadRequestException;
import com.development.project.mapper.UserMapper;
import com.development.project.repository.UserRepository;
import com.development.project.request.UserPostRequests;
import com.development.project.request.UserPutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Page<User> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<User> findByName(String name) {

        return repository.findByName(name);
    }

    public User findByIdOrThrowBadRequestException(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BadRequestException("user not found!"));

    }

    @Transactional
    public User save(UserPostRequests userPostRequests) {
        return repository.save(UserMapper.INSTANCE.toUser(userPostRequests));
    }

    public void delete(Long id) {
        repository.delete(findByIdOrThrowBadRequestException(id));

    }

    public void replace(UserPutRequest userPutRequest) {
        User saveUser = findByIdOrThrowBadRequestException(userPutRequest.getId());
        User user = UserMapper.INSTANCE.toUser(userPutRequest);
        user.setId(saveUser.getId());
        repository.save(user);
    }
}
