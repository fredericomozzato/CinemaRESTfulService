package cinema.business.room;

public class RoomDtoMapper {
    public static RoomDTO mapToDTO(Room room) {
        return new RoomDTO(
                room.getNumberOfRows(),
                room.getSeatsPerRow(),
                room.getSeats()
        );
    }
}
