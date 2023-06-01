package cinema.persistence;

import cinema.business.seat.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends CrudRepository<Seat, SeatId> {

    List<Seat> findByPurchased(boolean purchased);
}