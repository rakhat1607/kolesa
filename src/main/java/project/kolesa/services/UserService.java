package project.kolesa.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import project.kolesa.dto.UserDto;
import project.kolesa.model.Ad;
import project.kolesa.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean registerUser(UserDto userDto);

    void makeFavorite(Ad ad);
    void removeFavorite(Ad ad);

    void updatePassword(String oldPassword, String newPassword, String retypeNewPassword);
    User getCurrentUser();
    User saveUserData(User user);

}
