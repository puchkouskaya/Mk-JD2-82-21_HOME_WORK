package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.util;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IProfileService;
import org.springframework.stereotype.Component;

@Component
public class ProfileCheck {

    private final UserHolder userHolder;
    private final IProfileService profileService;

    public ProfileCheck(UserHolder userHolder, IProfileService profileService) {
        this.userHolder = userHolder;
        this.profileService = profileService;
    }

    public boolean checkProfile(Long idProfile) {
        try {
            long idUser1 = userHolder.getUser().getId();
            long idUser2 = profileService.get(idProfile).getUser().getId();
            return idUser1 == idUser2;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
