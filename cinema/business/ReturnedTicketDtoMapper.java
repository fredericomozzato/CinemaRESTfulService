package cinema.business;

import org.springframework.stereotype.Component;

@Component
public class ReturnedTicketDtoMapper {

    public ReturnedTicketDTO mapToReturnedTicketDto(Seat seat) {
        return new ReturnedTicketDTO(
                seat
        );
    }

}
