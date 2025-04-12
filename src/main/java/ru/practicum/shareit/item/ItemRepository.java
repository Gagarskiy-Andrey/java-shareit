package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository {
    Item createItem(Item dto);

    Item updateItem(Item newItem, Long id);

    Item getItemById(Long id);

    List<Item> getItemsByOwner(Long id);

    List<Item> getItemsByText(String text);

    void delete(Long id);
}
