package com.ktds.esign.client.example.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@ToString(exclude = "board")
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@Table(name = "example_images")
public class ExImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private ExBoard board;

    @Builder
    public ExImage(String name, String path) {
        this.name = name;
        this.path = path;
    }

    // utility method
    public void changeBoard(ExBoard board) {
        this.board = board;
        if (!board.getImages().contains(this)) {
            board.getImages().add(this);
        }
    }

}