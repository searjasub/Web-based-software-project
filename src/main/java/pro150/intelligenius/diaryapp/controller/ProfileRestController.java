package pro150.intelligenius.diaryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pro150.intelligenius.diaryapp.model.Profile;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profiles")
public class ProfileRestController {

    @Autowired
    private ProfileJpaRepository profileJpaRepository;

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public ModelAndView home(@PathVariable String name){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        return mav;
    }

    @RequestMapping(path = "/{name}/{dateOfBirth}", method = RequestMethod.POST)

    public ModelAndView createStudent(@PathVariable String name, @PathVariable String dateOfBirth) {
        Profile profile = new Profile(0, name, dateOfBirth);
        profileJpaRepository.save(profile);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("confirmation");
        return modelAndView;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView createStudent() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createProfile");
        return modelAndView;
    }

    public Profile getProfileById(@PathVariable int id){
        return profileJpaRepository.findByProfileId(id);
    }


    @RequestMapping(path = "/edit/{name}", method = RequestMethod.POST)
    public ModelAndView editProfileInfo(@PathVariable String name, @RequestBody Map<String, Object> updates) throws NoSuchFieldException, IllegalAccessException {

        Profile profile = profileJpaRepository.findByName(name);
        Profile p = getProfileById(profile.getProfileId());
        Class<?> pType = p.getClass();

        for (String propertyName : updates.keySet()){
            Field field = pType.getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(p, updates.get(propertyName));
            field.setAccessible(false);
        }

        profileJpaRepository.save(p);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("profile", p);
        modelAndView.setViewName("userSettingsSaved");
        return  modelAndView;
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public ModelAndView home(@PathVariable String name){
        Profile current = profileJpaRepository.findByName(name);
        ModelAndView mav = new ModelAndView();
        mav.addObject("id", current.getProfileId());
        mav.addObject("oldName", current.getName());
        mav.setViewName("userSettings");
        return mav;
    }


    @RequestMapping(name = "/getProfiles", method = RequestMethod.GET)
    public List<Profile> getAllProfiles() {
        return profileJpaRepository.findAll();
    }

}
