package com.dodam.board.Dto;

import com.dodam.board.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninResponseDto {
    private String token;
    private int exprTime;
    private UserEntity user;
}
