package com.ktds.esign.client.example.domain;
import com.ktds.esign.common.audit.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "example_images")
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Image(String name, String path) {
        this.name = name;
        this.path = path;
    }

    // utility method
    public void changeBoard(Board board) {
        this.board = board;
        if (!board.getImages().contains(this)) {
            board.getImages().add(this);
        }
    }


}