package com.example.BankExample.service.impl;

import com.example.BankExample.DTO.*;
import com.example.BankExample.enums.RoleEnum;
import com.example.BankExample.model.Admin;
import com.example.BankExample.model.Agent;
import com.example.BankExample.model.User;
import com.example.BankExample.repository.AdminRepo;
import com.example.BankExample.repository.AgentRepo;
import com.example.BankExample.repository.UserRepo;
import com.example.BankExample.service.AuthenticationService;
import com.example.BankExample.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private AgentRepo agentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDTO registerAsUser(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
        if (this.emailExists(user.getEmail())) {
            log.error("Email {} already taken", user.getEmail());
            throw new RuntimeException("Email already taken");
        }
        if(this.phoneExists(user.getPhoneNumber())){
            throw new RuntimeException("Phone number already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        LocalDate currentData = LocalDate.now();
        user.getAccount().setDateOpened(currentData);
        this.userRepo.save(user);
        user.setPassword(null);
        return this.modelMapper.map(user, UserDTO.class);
    }

    private Boolean emailExists(String email) {
        Optional<Admin> savedAdmin = this.adminRepo.findByEmail(email);
        Optional<User> savedUser = this.userRepo.findByEmail(email);
        Optional<Agent> savedAgent = this.agentRepo.findByEmail(email);
        return savedAdmin.isPresent() || savedUser.isPresent() || savedAgent.isPresent();
    }

    private Boolean phoneExists(long phone){
        Optional<User> savedUser = this.userRepo.findByPhoneNumber(phone);
        return savedUser.isPresent();
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO;
        if (loginRequestDTO.getRole().equals(RoleEnum.ROLE_ADMIN)) {
            loginResponseDTO = this.loginAsAdmin(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        } else if (loginRequestDTO.getRole().equals(RoleEnum.ROLE_AGENT)) {
            loginResponseDTO = this.loginAsAgent(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        } else {
            loginResponseDTO = this.loginAsUser(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        }
        return loginResponseDTO;
    }

    private LoginResponseDTO loginAsAdmin(String email, String password) {
        AdminDTO adminDTO = this.getAdminByEmail(email);
        return this.authenticate(adminDTO, password, adminDTO.getRole());
    }

    private LoginResponseDTO loginAsAgent(String email, String password) {
        AgentDTO agentDTO = this.getAgentByEmail(email);
        return this.authenticate(agentDTO, password, agentDTO.getRole());
    }

    private LoginResponseDTO loginAsUser(String email, String password) {
        UserDTO userDTO = this.getUserByEmail(email);
        userDTO.setAgent(null);
        userDTO.setLoanList(null);
        userDTO.getAccount().setTransactionList(null);
        return this.authenticate(userDTO, password, userDTO.getRole());
    }

    private AdminDTO getAdminByEmail(String email) {
        Admin admin = this.adminRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        return modelMapper.map(admin, AdminDTO.class);
    }

    private AgentDTO getAgentByEmail(String email) {
        Agent agent = this.agentRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Agent not found"));
        return modelMapper.map(agent, AgentDTO.class);
    }

    private UserDTO getUserByEmail(String email) {
        User user = this.userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    private UserDTO getUserByEmailAuthentication(String email) {
        User user = this.userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDTO(user.getEmail(), user.getPassword());
    }

    private AgentDTO getAgentByEmailAuthentication(String email) {
        Agent agent = this.agentRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Agent not found"));
        return new AgentDTO(agent.getEmail(), agent.getPassword());
    }

    private LoginResponseDTO authenticate(PersonDTO personDTO, String rawPassword, RoleEnum role) throws UsernameNotFoundException {
        this.checkPassword(rawPassword, personDTO.getPassword());
        List<SimpleGrantedAuthority> authorities = this.addAuthority(role);
        String accessToken = this.jwtUtil.generateToken(
                personDTO.getEmail(),
                new ArrayList<>(List.of(role.toString()))

        );
        personDTO.setPassword(null);
        return new LoginResponseDTO(accessToken, personDTO);
    }

    private void checkPassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword))
            throw new BadCredentialsException("Password Incorrect");
    }

    private List<SimpleGrantedAuthority> addAuthority(RoleEnum roleEnum) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (Objects.nonNull(roleEnum)) {
            authorities.add(new SimpleGrantedAuthority(roleEnum.toString()));
        }
        return authorities;
    }

    @Override
    public void createAdmin() {
        Admin admin = new Admin();
        admin.setEmail("admin@gmail.com");
        admin.setPassword(this.passwordEncoder.encode("password123"));
        admin.setAddress("Jawalakhel");
        admin.setFirstName("Siron");
        admin.setLastName("Shakya");
        admin.setPhoneNumber(9808765455l);
        this.adminRepo.save(admin);
    }

    @Override
    public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String email, RoleEnum role) {
        if (role.equals(RoleEnum.ROLE_USER)) {
            UserDTO userDTO = this.getUserByEmailAuthentication(email);
            List<SimpleGrantedAuthority> authorities = this.addAuthority(userDTO.getRole());
            return new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword(),
                    authorities);
        } else if (role.equals(RoleEnum.ROLE_AGENT)) {
            AgentDTO agentDTO = this.getAgentByEmailAuthentication(email);
            List<SimpleGrantedAuthority> authorities = this.addAuthority(agentDTO.getRole());
            return new UsernamePasswordAuthenticationToken(agentDTO.getEmail(), agentDTO.getPassword(),
                    authorities);
        } else {
            AdminDTO adminDTO = this.getAdminByEmail(email);
            List<SimpleGrantedAuthority> authorities = this.addAuthority(adminDTO.getRole());
            return new UsernamePasswordAuthenticationToken(adminDTO.getEmail(), adminDTO.getPassword(),
                    authorities);
        }
    }
}
