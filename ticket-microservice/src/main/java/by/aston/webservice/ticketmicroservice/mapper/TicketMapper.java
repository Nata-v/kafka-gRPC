package by.aston.webservice.ticketmicroservice.mapper;

import by.aston.webservice.ticketmicroservice.dto.TicketDto;
import by.aston.webservice.ticketmicroservice.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    Ticket toTicket(TicketDto ticketDto);

    TicketDto toTicketDto(Ticket ticket);

}
