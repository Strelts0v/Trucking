package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.Client;
import com.itechart.trucking.service.dto.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for the entity {@link Client} and its DTO called {@link ClientDto}.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-22
 */
@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDto clientToClientDto(Client client);
}