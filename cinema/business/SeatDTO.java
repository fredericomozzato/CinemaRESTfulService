package cinema.business;

import org.springframework.stereotype.Component;

import java.util.UUID;


public record SeatDTO(UUID token, Seat ticket) {
}
