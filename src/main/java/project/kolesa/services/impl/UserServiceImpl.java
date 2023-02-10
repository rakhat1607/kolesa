package project.kolesa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.kolesa.dto.UserDto;
import project.kolesa.model.Ad;
import project.kolesa.model.Permission;
import project.kolesa.model.User;
import project.kolesa.repository.PermissionRepository;
import project.kolesa.repository.UserRepository;
import project.kolesa.services.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }

    @Override
    public boolean registerUser(UserDto userDto) {
        if (userDto.getPassword().equals(userDto.getRetypePassword())) {
            User checkUser = userRepository.findByEmail(userDto.getEmail());
            if (checkUser == null) {
                Permission permission = permissionRepository.findByName("ROLE_USER");
                User user = new User();
                user.setEmail(userDto.getEmail());
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                user.setFullName(userDto.getFullName());
                user.setPhoneNumber(userDto.getPhoneNumber());
                List<Permission> permissions = new ArrayList<>();
                permissions.add(permission);
                user.setPermissions(permissions);
                User newUser = userRepository.save(user);
                return newUser.getId() != null;
            }
        }
        return false;
    }

    @Override
    public void makeFavorite(Ad ad) {
        User user = getCurrentUser();
        var favorites = user.getFavorites();
        if (!favorites.contains(ad)) {
            favorites.add(ad);
            user.setFavorites(new HashSet<>());
            userRepository.saveAndFlush(user);
            user.setFavorites(favorites);
            userRepository.save(user);
        }
    }

    @Override
    public void removeFavorite(Ad ad) {
        User user = getCurrentUser();
        var  favorites = user.getFavorites();
        if (favorites.contains(ad)){
            favorites.remove(ad);
            user.setFavorites(new HashSet<>());
            userRepository.saveAndFlush(user);
            user.setFavorites(favorites);
            userRepository.save(user);
        }

    }

    @Override
    public void updatePassword(String oldPassword, String newPassword, String retypeNewPassword) {
        if (newPassword.equals(retypeNewPassword)) {
            User user = getCurrentUser();
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
            }
        }

    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }


    @Override
    public User saveUserData(User user) {
        return userRepository.save(user);
    }
}
