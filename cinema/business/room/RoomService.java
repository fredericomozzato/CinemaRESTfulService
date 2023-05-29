package cinema.business.room;

import cinema.business.returnedticket.ReturnedTicketDTO;
import cinema.business.returnedticket.ReturnedTicketDtoMapper;
import cinema.business.seat.Seat;
import cinema.business.seat.SeatDTO;
import cinema.business.seat.SeatDtoMapper;
import cinema.business.seat.SeatRequest;
import cinema.business.statistics.Statistics;
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
    private final Statistics stats;

    @Autowired
    public RoomService(SeatRepository seatRepo, Room cinemaRoom, SeatDtoMapper seatDtoMapper, Statistics stats) {
        this.seatRepo = seatRepo;
        this.cinemaRoom = cinemaRoom;
        this.seatDtoMapper = seatDtoMapper;
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

        return ReturnedTicketDtoMapper.mapToReturnedTicketDto(returnedSeat);
    }
}
