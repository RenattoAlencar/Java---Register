package com.development.project.mapper;

import com.development.project.domain.User;
import com.development.project.request.UserPostRequests;
import com.development.project.request.UserPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toUser(UserPostRequests userPostRequests);

    public abstract User toUser(UserPutRequest userPutRequest);

}
