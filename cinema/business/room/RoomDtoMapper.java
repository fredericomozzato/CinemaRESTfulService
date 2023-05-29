package cinema.business.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class RoomDtoMapper {

    public static RoomDTO mapToDTO(Room room) {
        return new RoomDTO(
                room.getNumberOfRows(),
                room.getSeatsPerRow(),
                room.getSeats()
        );
    }

}
