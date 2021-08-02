package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VacBoardSimRequestDto {
    private Long vacBoardId;
    private String title;
    private int likeCount;
    private int hits;
    private int commentCount;
    private User user;


    @Builder
    public VacBoardSimRequestDto(Long vacBoardId, String title, int likeCount, int hits, int commentCount, User user) {
        this.vacBoardId = vacBoardId;
        this.title = title;
        this.likeCount = likeCount;
        this.hits = hits;
        this.commentCount = commentCount;
        this.user = user;

    }
    
    public static VacBoardSimRequestDto of(VacBoard vacBoard){
        return VacBoardSimRequestDto.builder()
                .vacBoardId(vacBoard.getId())
                .title(vacBoard.getTitle())
                .likeCount(vacBoard.getLikeCount())
                .hits(vacBoard.getHits())
                .commentCount(vacBoard.getCommentCount())
                .user(vacBoard.getUser())
                .build();
    }
    
    public static List<VacBoardSimRequestDto> list(List<VacBoard> boards){
        ArrayList<VacBoardSimRequestDto> VacBoardSimRequestDtos = new ArrayList<>();
        for(VacBoard vacBoard : boards){
            VacBoardSimRequestDtos.add(of(vacBoard));
        }
        return VacBoardSimRequestDtos;
    }
    
}
