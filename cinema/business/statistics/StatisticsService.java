package cinema.business.statistics;

import cinema.exceptionhandling.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final Statistics stats;
    private final StatisticsDtoMapper statisticsDtoMapper;

    @Autowired
    public StatisticsService(Statistics stats, StatisticsDtoMapper statisticsDtoMapper) {
        this.stats = stats;
        this.statisticsDtoMapper = statisticsDtoMapper;
    }

    public StatisticsDTO getStats(String password) {
        if (password == null || !this.validatePassword(password)) {
            throw new WrongPasswordException("The password is wrong!");
        } else {
            return this.statisticsDtoMapper.getStatsDTO(this.stats);
        }
     }

    private boolean validatePassword(String password) {
        return this.stats.validatePassword(password);
    }
}
