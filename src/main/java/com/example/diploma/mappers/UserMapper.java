package com.example.diploma.mappers;

import com.example.diploma.entities.UserEntity;
import com.example.diploma.dto.requests.UserRequestDto;
import com.example.diploma.dto.responses.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserEntity toEntity(UserRequestDto userRequestDto) {
        return UserEntity.builder()
                .login(userRequestDto.getLogin())
                .password(userRequestDto.getPassword())
                .build();
    }

    public UserResponseDto toDto(UserEntity userEntity){
        return UserResponseDto.builder()
                .id(userEntity.getId())
                .login(userEntity.getLogin())
                .password(userEntity.getPassword())
                .build();
    }

}
