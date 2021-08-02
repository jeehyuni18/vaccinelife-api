package com.vaccinelife.vaccinelifeapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity
@Setter
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "vacBoardId")
    private VacBoard vacBoard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public Comment(String comment, VacBoard vacBoard, User user) {
        this.comment = comment;
        this.vacBoard = vacBoard;
        this.user = user;
    }


}
