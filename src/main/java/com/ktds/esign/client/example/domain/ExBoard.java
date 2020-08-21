package com.ktds.esign.client.example.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@ToString(exclude = "images")
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@Table(name = "example_board")
public class ExBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition="text")
    private String content;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
    private final List<ExImage> images = new ArrayList<>();

    @Builder
    public ExBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // utility method
    public void addImage(ExImage image) {
        this.images.add(image);
        if (image.getBoard() != this) {
            image.changeBoard(this);
        }
    }

}
