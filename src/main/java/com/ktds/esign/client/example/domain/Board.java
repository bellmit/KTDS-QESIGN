package com.ktds.esign.client.example.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "example_board")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition="text")
    private String content;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // utility method
    public void addImage(Image image) {
        this.images.add(image);
        if (image.getBoard() != this) {
            image.changeBoard(this);
        }
    }

}
