package pro150.intelligenius.diaryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pro150.intelligenius.diaryapp.model.Profile;

import java.util.List;

@RestController
@RequestMapping("/users")
public class ProfileRestController {

    @Autowired
    private ProfileJpaRepository profileJpaRepository;

    @RequestMapping(path = "/{name}/{dateOfBirth}", method = RequestMethod.POST)
    public int createStudent(@PathVariable String name, @PathVariable String dateOfBirth) {
        Profile profile = new Profile(0, name, dateOfBirth);
        profileJpaRepository.save(profile);
        return profile.getProfileId();
    }

    @RequestMapping(name = "", method = RequestMethod.GET)
    public List<Profile> getAllUsers() {
        return profileJpaRepository.findAll();
    }

}
