package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.Item;
import com.itechart.trucking.service.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for the entity {@link Item} and its DTO called {@link ItemDto}.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-23
 */
@Mapper
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemDto itemToItemDto(Item item);
}