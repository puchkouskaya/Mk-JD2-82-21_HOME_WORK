//package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api;
//
//import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.WeightMeasurementByDateDto;
//import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.WeightMeasurementDto;
//import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.WeightMeasurement;
//import org.springframework.data.domain.Page;
//
//import java.awt.print.Pageable;
//import java.time.LocalDateTime;
//
//public interface IWeightMeasurementService {
//    WeightMeasurementByDateDto findAllByProfileIdAndDateCreate(LocalDateTime start, LocalDateTime end,
//                                                                 Long id, Pageable pageable);
//    Page<WeightMeasurement> getAll(Pageable pageable);
//    WeightMeasurement get(Long id);
//    WeightMeasurement save(WeightMeasurementDto weightMeasurementDto);
//    WeightMeasurement update(WeightMeasurementDto weightMeasurementDto, Long id, LocalDateTime dtUpdate);
//    void delete(Long id, LocalDateTime dtUpdate);
//}
