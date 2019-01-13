package org.domain.converter;

import org.domain.dto.CommentDto;
import org.domain.model.Product;
import org.domain.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class CommentConverter {

    @Autowired
    private CommentRepository commentRepository;

    public List<CommentDto> toCommentDto(Product product) {
        List<CommentDto> commentDtos = new LinkedList<>();
        commentRepository.findByProductId(product.getId())
                .forEach(comment -> commentDtos.add(new CommentDto(
                        comment.getId(), comment.getText(),
                        comment.getConsumer().getName()
                )));
        return commentDtos;
    }
}
