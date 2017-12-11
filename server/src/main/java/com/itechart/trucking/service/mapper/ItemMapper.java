package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.Item;
import com.itechart.trucking.service.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for the entity {@link Item} and its DTO called {@link ItemDto}.
 *
 * @author blink7
 * @version 1.1
 * @since 2017-12-11
 */
@Mapper
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    @Mapping(target = "unitCode", source = "unit.unitCode")
    ItemDto itemToItemDto(Item item);
}