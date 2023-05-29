package cinema.business.seat;

import java.util.UUID;

public record SeatDTO(UUID token, Seat ticket) {
}
