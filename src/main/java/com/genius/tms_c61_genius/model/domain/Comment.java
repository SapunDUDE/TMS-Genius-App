package com.genius.tms_c61_genius.model.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@EqualsAndHashCode(exclude={"song","user"})
@ToString(exclude = {"song","user"})
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_seq")
    @SequenceGenerator(name = "comments_seq", sequenceName = "comment_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "comment_content")
    private String content;

    @Column(name = "rating")
    private float rating;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Song song;
}
