package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.calculations.api;

import java.time.LocalDateTime;

public interface ITimeService {
    LocalDateTime getDate(int day);
    LocalDateTime getEndOfDate(LocalDateTime date);
    LocalDateTime detStartOfDate(LocalDateTime endOfDate);
}
