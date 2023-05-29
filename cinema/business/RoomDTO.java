package cinema.business;

import java.util.List;

public record RoomDTO(int total_rows, int total_columns, List<Seat>available_seats) {
}
