package cinema.business.statistics;

import cinema.business.room.Room;
import cinema.business.seat.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Statistics {

    @JsonProperty("current_income")
    private Integer income;
    @JsonProperty("number_of_available_seats")
    private Integer availableSeats;
    @JsonProperty("number_of_purchased_tickets")
    private Integer purchasedTickets;
    private final Room cinemaRoom;


    @Autowired
    public Statistics(Room cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
        this.income = 0;
        this.availableSeats = 81;
        this.purchasedTickets = 0;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets(Integer purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }

    public void purchaseTicket(Seat seat) {
        this.availableSeats--;
        this.purchasedTickets++;
        this.income += seat.getPrice();
    }

    public void returnTicket(Seat seat) {
        this.availableSeats++;
        this.purchasedTickets--;
        this.income -= seat.getPrice();
    }

    public boolean validatePassword(String pswd) {
        String password = "super_secret";
        return Objects.equals(pswd, password);
    }
}
