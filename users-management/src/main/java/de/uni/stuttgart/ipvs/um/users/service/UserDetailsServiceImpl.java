package de.uni.stuttgart.ipvs.um.users.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.uni.stuttgart.ipvs.um.form.UserFormModelValidation;
import de.uni.stuttgart.ipvs.um.response.ResultsSet;
import de.uni.stuttgart.ipvs.um.users.dto.UserCreateDTO;
import de.uni.stuttgart.ipvs.um.users.persistence.UserDetailsImpl;
import de.uni.stuttgart.ipvs.um.users.persistence.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserFormModelValidation userFormModelValidation;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository,
                                  UserFormModelValidation userFormModelValidation) {
        this.userRepository = userRepository;
        this.userFormModelValidation = userFormModelValidation;
    }


    public String createUser(UserCreateDTO userCreateDTO) {

        this.userFormModelValidation.validate(userCreateDTO);

        var userDetails = UserDetailsImpl.buildFromDTO(userCreateDTO);
        this.userRepository.save(userDetails);

        return userDetails.getId();
    }

    public ResultsSet findAllUsers() {
        return new ResultsSet<>(this.userRepository.findAllUsers());
    }

    public ResultsSet findUserByUserId(String id) {
        return new ResultsSet<>(this.userRepository.findUserByUserId(id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
