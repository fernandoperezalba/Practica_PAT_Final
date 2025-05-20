package practicaFinal.pfinal.service;

import practicaFinal.pfinal.model.UserChangePasswordRequest;
import practicaFinal.pfinal.model.UserDetail;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserDetail> newUser(Long id, String username, String password1, String password2);
    ResponseEntity<UserDetail> updatePassword(UserChangePasswordRequest userChangePasswordRequest);
    boolean getUsersAbuelos(Long id);
    boolean getUsersAyudantes(Long id);
    Long getIdByUsername(String username);
}
