package cinema.business.seat;


public class SeatDtoMapper {
    public static SeatDTO getSeatDTO(Seat seat) {
        return new SeatDTO(
                seat.getToken(),
                seat
        );
    }
}
