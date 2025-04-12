package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exemptions.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ItemMapper itemMapper;


    @Override
    public ItemDto createItem(ItemDto itemDto, Long userId) {
        userRepository.findById(userId);
        Item item = itemMapper.toItem(itemDto);
        item.setOwner(userId);
        log.info("Сохраняем вещь: {} для пользователя с ID {}", itemDto, userId);
        itemRepository.createItem(item);
        return itemMapper.toItemDto(item);
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto, Long itemId, Long userId) {
        Item item = itemRepository.getItemById(itemId);
        if (!item.getOwner().equals(userId)) {
            throw new NotFoundException("Обновляемая вещь с id = " + itemId + " не принадлежит " +
                    "указанному пользователю с id = " + userId);
        }
        Item newItem = itemMapper.toItem(itemDto);
        if (newItem.getName() != null) {
            item.setName(newItem.getName());
        }
        if (newItem.getDescription() != null) {
            item.setDescription(newItem.getDescription());
        }
        if (newItem.getAvailable() != null) {
            item.setAvailable(newItem.getAvailable());
        }
        if (newItem.getRequest() != null) {
            item.setRequest(newItem.getRequest());
        }
        itemRepository.updateItem(item, itemId);
        return itemMapper.toItemDto(item);
    }

    @Override
    public ItemDto getItemById(Long itemId) {
        ItemDto itemDto = itemMapper.toItemDto(itemRepository.getItemById(itemId));
        return itemDto;
    }

    @Override
    public List<ItemDto> getItemsByOwner(Long ownerId) {
        List<ItemDto> itemsByOwner = itemRepository.getItemsByOwner(ownerId)
                .stream()
                .map(itemMapper::toItemDto)
                .toList();
        return itemsByOwner;
    }

    @Override
    public List<ItemDto> getItemsByText(String text) {
        List<ItemDto> itemByText = itemRepository.getItemsByText(text)
                .stream()
                .map(itemMapper::toItemDto)
                .toList();
        return itemByText;
    }

    @Override
    public void delete(Long id) {
        itemRepository.delete(id);
    }
}
