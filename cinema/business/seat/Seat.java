package cinema.business.seat;

import cinema.persistence.SeatId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.UUID;

@JsonPropertyOrder({
        "row",
        "column",
        "price"
})

@Entity
@IdClass(SeatId.class)
@Table(name = "seats")
public class Seat {

    @Id
    @JsonProperty("row")
    private int seatRow;
    @Id
    @JsonProperty("column")
    private int seatColumn;
    @JsonIgnore
    @Column(name = "purchased", columnDefinition = "DEFAULT false")
    private Boolean purchased;
    @Column(name = "price")
    private Integer price;
    @JsonIgnore
    @Column(name = "token")
    private UUID token;

    public Seat() {}

    public Seat(int seatRow, int seatColumn) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.purchased = false;
        this.price = seatRow <= 4 ? 10 : 8;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public boolean isPurchased() {
        return purchased;
    }


    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
