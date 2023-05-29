package cinema.business.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomDtoMapper {

    @Autowired
    RoomService service;

    public RoomDtoMapper(RoomService service) {
        this.service = service;
    }

    public RoomDTO mapToDTO(Room room) {
        return new RoomDTO(
                room.getNumberOfRows(),
                room.getSeatsPerRow(),
                this.service.getAvailableSeats()
        );
    }

}
