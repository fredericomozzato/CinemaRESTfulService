package cinema.business;

import org.springframework.stereotype.Component;

@Component
public class StatisticsDtoMapper {

    public StatisticsDTO getStatsDTO(Statistics stats) {
        return new StatisticsDTO(
                stats.getIncome(),
                stats.getAvailableSeats(),
                stats.getPurchasedTickets()
        );
    }

}
