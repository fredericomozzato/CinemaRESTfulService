package cinema.persistence;

import java.io.Serializable;
import java.util.Objects;

public class SeatId implements Serializable {
    private int seatRow;
    private int seatColumn;

    public SeatId() {}

    public SeatId(int seatRow, int seatColumn) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatId seatId = (SeatId) o;
        return seatRow == seatId.seatRow && seatColumn == seatId.seatColumn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatRow, seatColumn);
    }
}
