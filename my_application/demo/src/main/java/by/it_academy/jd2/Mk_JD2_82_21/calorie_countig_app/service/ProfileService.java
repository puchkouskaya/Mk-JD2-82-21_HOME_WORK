package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dao.api.IProfileDao;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProfileService implements IProfileService {
    private IProfileDao profileDao;
    private final UserHolder userHolder;
    private final IUserService userService;

    public ProfileService (IProfileDao profileDao, UserHolder userHolder, IUserService userService) {
        this.profileDao = profileDao;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @Override
    public Profile get(Long id) {
        return profileDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Profile по ID не найден")
        );
    }

    @Override
    public Profile save(Profile profile) {
        profile.setUser(userHolder.getUser());
        LocalDateTime localDateTime = LocalDateTime.now();
        profile.setDateCreate(localDateTime);
        profile.setDateUpdate(profile.getDateUpdate());
        return this.profileDao.save(profile);
    }

    @Override
    public Profile update(Profile profile, Long id) {
        Profile updateProfile = get(id);
        if (updateProfile!=null){
            updateProfile.setHeight(profile.getHeight());
            updateProfile.setWeight(profile.getWeight());
            updateProfile.setDesiredWeight(profile.getDesiredWeight());
            updateProfile.setDateOfBirth(profile.getDateOfBirth());
            updateProfile.setGender(profile.getGender());
            updateProfile.setActivity(profile.getActivity());
            updateProfile.setGoal(profile.getGoal());
            String loginUser = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(loginUser);
            updateProfile.setUser(user);
            LocalDateTime updateDate = LocalDateTime.now();
            updateProfile.setDateUpdate(updateDate);
            return profileDao.save(updateProfile);
        } else {
            throw new IllegalArgumentException("Profile is not found");
        }
    }

    @Override
    public void delete(Long id, LocalDateTime date) {
        Profile profile = get(id);
        if (profile == null) {
            throw new IllegalArgumentException("Profile по ID не найден!");
        }
        profileDao.deleteById(id);
    }
}
