package cinema.business.returnedticket;

import cinema.business.seat.Seat;
import org.springframework.stereotype.Component;

public class ReturnedTicketDtoMapper {

    public static ReturnedTicketDTO mapToReturnedTicketDto(Seat seat) {
        return new ReturnedTicketDTO(
                seat
        );
    }

}
