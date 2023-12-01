package com.dodam.board.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    private String user_id;
    private String user_pwd;
    private String user_name;
    private String user_nickname;
    private String user_Address;
    private String user_Address2;
    private String user_profileImg;
}
