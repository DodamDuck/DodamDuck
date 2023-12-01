package com.dodam.board.Repository;

import com.dodam.board.Entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByIdAnduser_pwd(String user_id, String user_pwd);
    boolean existsById(String user_id);

    public UserEntity findByUser_id(String user_id);
}
