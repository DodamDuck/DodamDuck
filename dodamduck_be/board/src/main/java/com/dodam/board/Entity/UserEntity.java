package com.dodam.board.Entity;

import com.dodam.board.Dto.SignupDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="user")
@Table(name="user")
public class UserEntity {

    @Id
    private String user_id;
    private String user_pwd;
    private String user_name;
    private String user_nickname;
    private String user_address; //주소
    private String user_address2; //세부 주소
    private String user_profile_img;

    /*private String user_signupdate;
    private String user_withdrawdate;
    private int user_level;*/

    public UserEntity(SignupDto dto){
        this.user_id = dto.getUser_id();
        this.user_pwd = dto.getUser_pwd();
        this.user_name = dto.getUser_name();
        this.user_nickname = dto.getUser_nickname();
        this.user_address = dto.getUser_Address() + " " + dto.getUser_Address2();
    }

}
