package de.uni.stuttgart.ipvs.um.users.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.uni.stuttgart.ipvs.um.response.ResultsSet;
import de.uni.stuttgart.ipvs.um.users.dto.UserCreateDTO;
import de.uni.stuttgart.ipvs.um.users.persistence.UserDetailsImpl;
import de.uni.stuttgart.ipvs.um.users.persistence.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String createUser(UserCreateDTO userCreateDTO) {

        var userDetails = UserDetailsImpl.buildFromDTO(userCreateDTO);

        this.userRepository.save(userDetails);

        return userDetails.getId();
    }

    public ResultsSet findAllUsers() {

        return new ResultsSet<>(this.userRepository.findAllUsers());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
