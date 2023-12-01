package com.dodam.board.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @GetMapping("/")
    public String getBoard(@AuthenticationPrincipal String user_id){
        return "로그인된 사용자는 " + user_id + "입니다.";
    }
}
