package practicaFinal.pfinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChangePasswordRequest {

    private Long id;
    private String newPassword;
    private String newPassword2;
}