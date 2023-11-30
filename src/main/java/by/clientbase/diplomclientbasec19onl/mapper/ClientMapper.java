package by.clientbase.diplomclientbasec19onl.mapper;

import by.clientbase.diplomclientbasec19onl.dto.ClientDTO;
import by.clientbase.diplomclientbasec19onl.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO clientToDto(Client client);

    Client dtoToClient (ClientDTO clientDTO);

    Client updateClientDtoToClient (ClientDTO clientDTO, long id);
}
