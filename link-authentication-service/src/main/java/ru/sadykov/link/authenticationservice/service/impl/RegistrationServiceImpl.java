package ru.sadykov.link.authenticationservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sadykov.link.authenticationservice.dto.AuthRequestDto;
import ru.sadykov.link.authenticationservice.exception.UserAlreadyExistsException;
import ru.sadykov.link.authenticationservice.mapper.UserMapper;
import ru.sadykov.link.authenticationservice.service.RegistrationService;
import ru.sadykov.link.common.entity.Role;
import ru.sadykov.link.common.entity.User;
import ru.sadykov.link.common.repository.RoleRepository;
import ru.sadykov.link.common.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void signUp(AuthRequestDto authRequestDto) {
        if (userRepository.existsUserByUserName(authRequestDto.getUserName())) {
            throw new UserAlreadyExistsException();
        }
        User user = userMapper.authRequestToUser(authRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(1L).get());
        user.setRoles(roles);
        userRepository.save(user);
    }
}
