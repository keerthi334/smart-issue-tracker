package com.keerthi.smartissuetracker.service;

import java.util.*;
import com.keerthi.smartissuetracker.dto.UserRequestDto;
import com.keerthi.smartissuetracker.dto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);
    List<UserResponseDto> getAllUsers();
}