//package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dao.api;
//
//import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.WeightMeasurementByDateDto;
//import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.WeightMeasurement;
//import org.springframework.data.domain.Page;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.awt.print.Pageable;
//import java.time.LocalDateTime;
//
//public interface IWeightMeasurementDao extends JpaRepository<WeightMeasurement, Long> {
//    WeightMeasurementByDateDto findAllByProfileIdAndDateCreate(LocalDateTime start, LocalDateTime end,
//                                                                 Long id, Pageable pageable);
//    Page<WeightMeasurement> findAllByDateCreateBetweenAndProfileId(LocalDateTime start, LocalDateTime end,
//                                                                     Long id, Pageable pageable);
//    Page<WeightMeasurement> findAll(Pageable pageable);
//}

