package practicaFinal.pfinal.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import practicaFinal.pfinal.model.UserDetail;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface UserDetailRepository extends CrudRepository<UserDetail, Long> {

    @Query("SELECT TOP 1 * FROM USUARIO WHERE USERNAME= :username")
    UserDetail getUserDetailByUserName(@Param("username") String username);

    @Query("SELECT TOP 1 * FROM USUARIO WHERE ID= :id")
    UserDetail getUserDetailById(@Param("id") Long id);

    @Modifying
    @Query("INSERT INTO USUARIO (ID, USERNAME, PASSWORD) VALUES (:id, :username, :password)")
    void insertNewUser(@Param("id") Long id,
                       @Param("username") String username,
                       @Param("password") String password);

    @Query("SELECT (ID, USERNAME) FROM USUARIO WHERE ID=SOME(SELECT ID FROM ABUELO WHERE ID= :id)")
    List<UserDetail> getUsersAbuelo(@Param("id") Long id);

    @Query("SELECT (ID, USERNAME) FROM USUARIO WHERE ID=SOME(SELECT ID FROM AYUDANTE WHERE ID= :id)")
    List<UserDetail> getUsersAyudante(@Param("id") Long id);

    @Query("SELECT ID FROM USUARIO WHERE USERNAME= :username")
    Long getIdByUsername(@Param("username") String username);
}