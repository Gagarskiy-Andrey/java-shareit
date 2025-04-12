package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.validators.Add;
import ru.practicum.shareit.validators.Update;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    public static final String SHARER_USER_ID = "X-Sharer-User-Id";
    @Autowired
    ItemService itemService;

    @GetMapping
    public List<ItemDto> getItemsByOwner(@RequestHeader(SHARER_USER_ID) Long ownerId) {
        return itemService.getItemsByOwner(ownerId);
    }

    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @GetMapping("/search")
    public List<ItemDto> getItemsByText(@RequestParam("text") String text) {
        return itemService.getItemsByText(text);
    }

    @PostMapping
    public ItemDto create(@Validated(Add.class) @RequestBody ItemDto itemDto, @RequestHeader(SHARER_USER_ID) Long userId) {
        return itemService.createItem(itemDto, userId);
    }

    @PatchMapping("/{id}")
    public ItemDto update(@Validated(Update.class) @RequestBody ItemDto newItem, @PathVariable("id") Long itemId,
                          @RequestHeader(SHARER_USER_ID) Long userId) {
        return itemService.updateItem(newItem, itemId, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        itemService.delete(id);
    }
}
