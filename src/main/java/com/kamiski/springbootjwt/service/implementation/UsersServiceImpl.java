package com.kamiski.springbootjwt.service.implementation;

import com.kamiski.springbootjwt.component.HashGenerate;
import com.kamiski.springbootjwt.controller.form.UserAuthForm;
import com.kamiski.springbootjwt.controller.form.UserForm;
import com.kamiski.springbootjwt.controller.form.UserFormUpdate;
import com.kamiski.springbootjwt.domain.Users;
import com.kamiski.springbootjwt.exception.UserNameException;
import com.kamiski.springbootjwt.repository.UserRepository;
import com.kamiski.springbootjwt.service.UsersService;
import com.kamiski.springbootjwt.validation.RecordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HashGenerate hashGenerate;

    @Autowired
    private RecordValidation recordValidation;

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> findById(Long id) {
        recordValidation.verifyIfUserExistsById(id);
        return userRepository.findById(id);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        recordValidation.verifyIfUsernameAlreadyExists(email);
        return userRepository.findByEmail(email);
    }

    @Override
    public Users create(UserForm userForm) {

        recordValidation.verifyIfUsernameAlreadyExists(userForm.getEmail());

        Users users = Users.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(hashGenerate.generateBCrypt(userForm.getPassword()))
                .validationCode(hashGenerate.generateValidationCode())
                .dateRegister(LocalDateTime.now())
                .isCredentialsNonExpired(true)
                .isNonExpired(true)
                .isNonLocked(false)
                .isEnabled(true)
                .build();

        return userRepository.save(users);
    }

    @Override
    public Users update(UserFormUpdate userFormUpdate) {

        recordValidation.verifyIfUserExistsById(userFormUpdate.getId());

        Users users = Users.builder()
                .name(userFormUpdate.getName())
                .email(userFormUpdate.getEmail())
                .password(userFormUpdate.getPassword())
                .profiles(userFormUpdate.getProfileList())
                .build();

        return userRepository.save(users);

    }

    @Override
    public void deleteById(Long id) {
        recordValidation.verifyIfUserExistsById(id);
        userRepository.deleteById(id);
    }

    @Override
    public ResponseEntity setUserIsValid(UserAuthForm userAuthForm) {

        if(userRepository.existsUserByEmailAndValidationCode(
                userAuthForm.getEmail(),
                userAuthForm.getCode())
        ){
            Users users = userRepository.findByEmail(userAuthForm.getEmail()).get();
            users.setIsNonLocked(true);
            userRepository.save(users);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

}
