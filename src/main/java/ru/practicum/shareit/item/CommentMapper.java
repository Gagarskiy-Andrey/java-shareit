package ru.practicum.shareit.item;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.shareit.item.dto.CommentDtoRequest;
import ru.practicum.shareit.item.dto.CommentDtoResponse;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "item", source = "item")
    @Mapping(target = "text", source = "dto.text")
    @Mapping(target = "owner", source = "owner")
    Comment mapDtoToComment(CommentDtoRequest dto, User owner, Item item);

    @Mapping(target = "id", source = "comment.id")
    @Mapping(target = "item", source = "comment.item")
    @Mapping(target = "text", source = "comment.text")
    @Mapping(target = "authorName", source = "comment.owner.name")
    @Mapping(target = "created", source = "comment.created")
    CommentDtoResponse mapCommentToDto(Comment comment);

    List<CommentDtoResponse> mapCommentToDto(List<Comment> comments);
}