package com.dodam.board.Service;

import com.dodam.board.Dto.ResponseDto;
import com.dodam.board.Dto.SigninDto;
import com.dodam.board.Dto.SigninResponseDto;
import com.dodam.board.Dto.SignupDto;
import com.dodam.board.Entity.UserEntity;
import com.dodam.board.Repository.UserRepository;
import com.dodam.board.Security.TokenProvider;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseDto<?> signup(SignupDto dto){
        String user_id = dto.getUser_id();
        String user_pwd = dto.getUser_pwd();

        //id 중복 확인
        try{
            if(userRepository.existsById(user_id))
                return ResponseDto.setFailed("Existed Id!");
        }catch (Exception error){
            return ResponseDto.setFailed("Database Error!");
        }

        // UserEntity 생성
        UserEntity userEntity = new UserEntity(dto);

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user_pwd);
        userEntity.setUser_pwd(passwordEncoder.encode(user_pwd));

        //DB에 Entity 저장
        try {
            userRepository.save(userEntity);
        }catch (Exception error){
            return ResponseDto.setFailed("Database Error!");
        }

        //성공시 success response 반환
        return ResponseDto.setSuccess("Signup Success!",null);
    }
    public ResponseDto<SigninResponseDto> signin(SigninDto dto){
        String user_id = dto.getUser_id();
        String user_pwd = dto.getUser_pwd();

        UserEntity userEntity = null;
        try{
            userEntity = userRepository.findByUser_id(user_id);
            // 잘못된 아이디
            if( userEntity == null ) return ResponseDto.setFailed("Login Failed");
            // 잘못된 패스워드
            if (passwordEncoder.matches(user_pwd, userEntity.getUser_pwd()))
                return ResponseDto.setFailed("Login Failed");
        }catch (Exception error){
            return ResponseDto.setFailed("Database Error");
        }

        userEntity.setUser_pwd("");

        String token = tokenProvider.create(user_id);
        int exprTime = 3600000;

        SigninResponseDto signinResponseDto = new SigninResponseDto(token, exprTime, userEntity);
        return ResponseDto.setSuccess("Signin Success", signinResponseDto);
    }
}
