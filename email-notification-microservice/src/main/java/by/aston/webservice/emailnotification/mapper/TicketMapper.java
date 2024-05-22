package by.aston.webservice.emailnotification.mapper;

import by.aston.webservice.emailnotification.dto.TicketDto;
import by.aston.webservice.emailnotification.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TicketMapper {

        TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

        Ticket toTicket(TicketDto ticketDto);

        TicketDto toTicketDto(Ticket ticket);
}
