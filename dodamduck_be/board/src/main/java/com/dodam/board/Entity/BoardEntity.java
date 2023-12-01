package com.dodam.board.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="board")
@Table(name="board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //기본키 생성
    private int boardNum;
    private String boardTitle;
    private String boardContent;
    private String boardImg;
    private String boardWriterId;
    private String boardWriterNick;
    private String boardWriterProfile;
    private String boardWriterDate;
    private int board_hits;
    private int board_likeHits;
    private int board_commentHits;

}
