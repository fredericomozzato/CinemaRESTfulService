package cinema.business.statistics;

public class StatisticsDtoMapper {
    public static StatisticsDTO getStatsDTO(Statistics stats) {
        return new StatisticsDTO(
                stats.getIncome(),
                stats.getAvailableSeats(),
                stats.getPurchasedTickets()
        );
    }
}
