package cinema.business.room;

import cinema.business.seat.Seat;
import cinema.exceptionhandling.UnavailableTicketException;
import cinema.exceptionhandling.WrongTokenException;
import cinema.persistence.SeatRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class Room {

    @JsonProperty("total_rows")
    private int numberOfRows;
    @JsonProperty("total_columns")
    private int seatsPerRow;
    private List<Seat> seats;
    private final SeatRepository repo;

    @Autowired
    public Room(SeatRepository repo) {
        this.seatsPerRow = 9;
        this.numberOfRows = 9;
        this.seats = new ArrayList<>();
        this.repo = repo;

        for (int i = 1; i <= numberOfRows; i ++) {
            for (int k = 1; k <= seatsPerRow; k++) {
                Seat newSeat = new Seat(i, k);
                this.seats.add(newSeat);
                this.repo.save(newSeat);
            }
        }
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }

    @JsonProperty("available_seats")
    public List<Seat> getSeats() {
        Iterable<Seat> seats = this.repo.findAll();
        return StreamSupport.stream(seats.spliterator(), false)
                .filter(seat -> !seat.isPurchased())
                .collect(Collectors.toList());
    }

    public int getAvailableSeatNumber() {
        return this.getSeats().size();
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Seat getSeat(int row, int column) {
        for (Seat seat : this.seats) {
            if (seat.getSeatRow() == row && seat.getSeatColumn() == column) {
                return seat;
            }
        }
        throw new UnavailableTicketException("The number of a row or a column is out of bounds!");
    }

    public Seat purchase(Seat seat) {

        if (seat.isPurchased()) {
            throw new UnavailableTicketException("The ticket has been already purchased!");
        }

        seat.setPurchased(true);
        seat.setToken(UUID.randomUUID());

        return seat;
    }

    public Seat getSeatByToken(UUID token) {
        Optional<Seat> optionalSeat = this.seats.stream()
                .filter(s -> token.equals(s.getToken()))
                .findFirst();

        return optionalSeat.orElseThrow(() -> new WrongTokenException("Wrong token!"));
    }

}
