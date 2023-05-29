package cinema.business;

import org.springframework.stereotype.Component;

@Component
public class SeatDtoMapper {

    public SeatDTO getSeatDTO(Seat seat) {
        return new SeatDTO(
                seat.getToken(),
                seat
        );
    }
}
