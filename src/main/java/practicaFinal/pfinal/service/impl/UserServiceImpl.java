package practicaFinal.pfinal.service.impl;

import practicaFinal.pfinal.model.UserChangePasswordRequest;
import practicaFinal.pfinal.model.UserDetail;
import practicaFinal.pfinal.repository.UserDetailRepository;
import practicaFinal.pfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<UserDetail> newUser(Long id, String username, String password1, String password2){
        if(userDetailRepository.getUserDetailByUserName(username)==null){
            if(password1.equals(password2)) {
                String encodedPassword = passwordEncoder.encode(password1);
                UserDetail tempUser = new UserDetail(id,username, encodedPassword);
                userDetailRepository.insertNewUser(tempUser.getId(), tempUser.getUsername(), tempUser.getPassword());
                return ResponseEntity.ok().body(tempUser);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<UserDetail> updatePassword(UserChangePasswordRequest userChangePasswordRequest) {

        UserDetail userDetail = userDetailRepository.getUserDetailById(userChangePasswordRequest.getId());

        //Logica de negocio
        if(userChangePasswordRequest.getNewPassword().equals(userChangePasswordRequest.getNewPassword2())) {
            userDetail.setPassword(passwordEncoder.encode(userChangePasswordRequest.getNewPassword()));
            userDetailRepository.save(userDetail);
            return ResponseEntity.ok().body(userDetail);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public boolean getUsersAbuelos(Long id){
        List<UserDetail> userDetailAbuelos = userDetailRepository.getUsersAbuelo(id);
        if(userDetailAbuelos.size()!=0)
            return true;
        else
            return false;
    }

    @Override
    public boolean getUsersAyudantes(Long id){
        List<UserDetail> userDetailAyudantes = userDetailRepository.getUsersAyudante(id);
        if(userDetailAyudantes.size()!=0)
            return true;
        else
            return false;
    }

    @Override
    public Long getIdByUsername(String username){
        Long id = userDetailRepository.getIdByUsername(username);
        return id;
    }

}