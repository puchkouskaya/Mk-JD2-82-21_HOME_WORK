package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.controller;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final IProfileService profileService;

    public ProfileController(IProfileService profileService) {
        this.profileService = profileService;
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Profile> get(@PathVariable Long id) {
        try {
            Profile profile = profileService.get(id);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Profile> save(@RequestBody Profile profile) {
        try {
            Profile profile1 = profileService.save(profile);
            return new ResponseEntity<>(profile1, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<Profile> update(@PathVariable Long id, @RequestBody Profile profile,
                                          @PathVariable (name = "dt_update") LocalDateTime dateUpdate) {
        Profile profile1 = profileService.update(profile, id);
        return new ResponseEntity<>(profile1, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable (name = "id") long id,
                                             @PathVariable (name = "dt_update") long dateUpdate){

        try {
            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateUpdate), ZoneId.systemDefault());
            profileService.delete(id, date);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
