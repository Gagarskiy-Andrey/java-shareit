package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.validators.Add;
import ru.practicum.shareit.validators.Update;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    public static final String SHARER_USER_ID = "X-Sharer-User-Id";
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemDto>> getItemsByOwner(@RequestHeader(SHARER_USER_ID) Long ownerId) {
        return ResponseEntity.ok(itemService.getItemsByOwner(ownerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemDto>> getItemsByText(@RequestParam("text") String text) {
        return ResponseEntity.ok(itemService.getItemsByText(text));
    }

    @PostMapping
    public ResponseEntity<ItemDto> create(@Validated(Add.class) @RequestBody ItemDto itemDto, @RequestHeader(SHARER_USER_ID) Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(itemDto, userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemDto> update(@Validated(Update.class) @RequestBody ItemDto newItem, @PathVariable("id") Long itemId,
                                          @RequestHeader(SHARER_USER_ID) Long userId) {
        return ResponseEntity.ok(itemService.updateItem(newItem, itemId, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
