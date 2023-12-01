package com.dodam.board.Controller;

import com.dodam.board.Dto.ResponseDto;
import com.dodam.board.Dto.SigninDto;
import com.dodam.board.Dto.SigninResponseDto;
import com.dodam.board.Dto.SignupDto;
import com.dodam.board.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired AuthService authService;

    @PostMapping("/signup")
    public ResponseDto<?> signup(@RequestBody SignupDto requestBody){
        return authService.signup(requestBody);
    }
    @PostMapping("/login")
    public ResponseDto<SigninResponseDto> login(@RequestBody SigninDto requestBody){
        return authService.signin(requestBody);
    }
}
