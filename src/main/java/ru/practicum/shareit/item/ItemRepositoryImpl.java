package ru.practicum.shareit.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exemptions.NotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Slf4j
public class ItemRepositoryImpl implements ItemRepository {
    @Autowired
    private UserService userService;
    private Map<Long, Item> items = new HashMap<>();
    private Long itemId = 1L;


    @Override
    public Item createItem(Item dto) {
        dto.setId(generateId());
        items.put(dto.getId(), dto);
        return dto;
    }

    @Override
    public Item updateItem(Item newItem, Long id) {
        items.put(newItem.getId(), newItem);
        return newItem;
    }

    @Override
    public Item getItemById(Long id) {
        if (!items.containsKey(id)) {
            throw new NotFoundException("Item с указанным id отсутствует");
        }
        return items.get(id);
    }

    @Override
    public List<Item> getItemsByOwner(Long id) {
        return items.values()
                .stream()
                .filter(item -> Objects.equals(item.getOwner(), id))
                .toList();
    }

    @Override
    public List<Item> getItemsByText(String text) {
        return items.values()
                .stream()
                .filter(item -> !text.isBlank() &&
                        item.getName().toUpperCase().contains(text)
                        && Objects.equals(item.getAvailable(), true))
                .toList();
    }

    @Override
    public void delete(Long id) {
        items.remove(id);
    }

    private long generateId() {
        return itemId++;
    }
}
