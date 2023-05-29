package cinema.business;

import cinema.persistence.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class RoomService {

    private final SeatRepository seatRepo;
    private final Room cinemaRoom;
    private final SeatDtoMapper seatDtoMapper;
    private final ReturnedTicketDtoMapper returnedTicketDtoMapper;
    private final Statistics stats;

    @Autowired
    public RoomService(SeatRepository seatRepo, Room cinemaRoom, SeatDtoMapper seatDtoMapper, ReturnedTicketDtoMapper returnedTicketDtoMapper, Statistics stats) {
        this.seatRepo = seatRepo;
        this.cinemaRoom = cinemaRoom;
        this.seatDtoMapper = seatDtoMapper;
        this.returnedTicketDtoMapper = returnedTicketDtoMapper;
        this.stats = stats;
    }

    public List<Seat> getAvailableSeats() {
        Iterable<Seat> allSeats = this.seatRepo.findAll();
        return StreamSupport.stream(allSeats.spliterator(), false)
                .filter(seat -> !seat.isPurchased())
                .collect(Collectors.toList());

    }

    public SeatDTO purchaseTicket(SeatRequest request) {
        int row = request.row();
        int column = request.column();

        Seat seat = this.cinemaRoom.getSeat(row, column);
        this.seatRepo.save(this.cinemaRoom.purchase(seat));

        this.stats.purchaseTicket(seat);

        return this.seatDtoMapper.getSeatDTO(seat);
    }

    public ReturnedTicketDTO returnTicket(String token) {
        UUID uuidToken = UUID.fromString(token);
        Seat returnedSeat = this.cinemaRoom.getSeatByToken(uuidToken);
        returnedSeat.setPurchased(false);
        returnedSeat.setToken(null);
        this.seatRepo.save(returnedSeat);

        this.stats.returnTicket(returnedSeat);

        return this.returnedTicketDtoMapper.mapToReturnedTicketDto(returnedSeat);
    }
}
