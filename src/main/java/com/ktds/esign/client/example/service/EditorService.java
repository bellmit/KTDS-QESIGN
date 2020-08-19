package com.ktds.esign.client.example.service;

import com.ktds.esign.client.example.domain.Board;
import com.ktds.esign.client.example.domain.Image;
import com.ktds.esign.client.example.payload.BoardReq.BoardDto;
import com.ktds.esign.client.example.repository.EditorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EditorService {
    private final EditorRepository editorRepository;

    @Transactional
    public Long savePost(BoardDto boardDto) {
        Board board = boardDto.toEntity();
        editorRepository.save(board).getId();

        boardDto.getImages().forEach(name -> {
            Image image = Image.builder()
                    .name(name)
                    .path("/upload/files/")
                    .build();
            board.addImage(image);
        });

        return board.getId();
    }

}
