package cinema.business.statistics;

import cinema.exceptionhandling.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final Statistics stats;

    @Autowired
    public StatisticsService(Statistics stats) {
        this.stats = stats;
    }

    public StatisticsDTO getStats(String password) {
        if (password == null || !this.validatePassword(password)) {
            throw new WrongPasswordException("The password is wrong!");
        } else {
            return StatisticsDtoMapper.getStatsDTO(this.stats);
        }
     }

    private boolean validatePassword(String password) {
        return this.stats.validatePassword(password);
    }
}
