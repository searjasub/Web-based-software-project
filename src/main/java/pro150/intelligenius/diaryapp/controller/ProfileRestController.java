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

    @RequestMapping(path = "/create-profile", method = RequestMethod.POST)
    public ModelAndView createProfile(@ModelAttribute("profile") Profile profile) {
        profileJpaRepository.save(profile);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("confirmation");
        return modelAndView;
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public ModelAndView getProfileByName(@PathVariable String name) {
        ModelAndView mav = new ModelAndView();
        System.out.println("before get profile");
        Profile profile = profileJpaRepository.findById(name).orElse(null);
        System.out.println(profile.getName());
        mav.addObject("profile", profile);
        mav.setViewName("showProfile");
        return mav;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView createStudent() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createProfile");
        return modelAndView;
    }


    @RequestMapping(path = "/edit/{name}", method = RequestMethod.POST)
    public ModelAndView updateProfile(@PathVariable String name, @ModelAttribute("update") Profile updates) throws NoSuchFieldException, IllegalAccessException {

        Profile profile = profileJpaRepository.findById(name).orElse(null);
        assert profile != null;

        profile.setName(updates.getName());
        profile.setDateOfBirth(updates.getDateOfBirth());
//        Class<?> pType = profile.getClass();
//
//        for (String propertyName : updates.keySet()){
//            Field field = pType.getDeclaredField(propertyName);
//            field.setAccessible(true);
//            field.set(profile, updates.get(propertyName));
//            field.setAccessible(false);
//        }

        profileJpaRepository.save(profile);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("profile", profile);
        modelAndView.setViewName("userSettingsSaved");
        return  modelAndView;
    }

    @RequestMapping(path = "/edit/{name}", method = RequestMethod.GET)
    public ModelAndView displayProfileEdit(@PathVariable String name){
        Profile current = profileJpaRepository.findById(name).orElse(null);
        ModelAndView mav = new ModelAndView();
        mav.addObject("oldName", current.getName());
        mav.setViewName("userSettings");
        return mav;
    }

    @RequestMapping(name = "/getProfiles", method = RequestMethod.GET)
    public List<Profile> getAllProfiles() {
        return profileJpaRepository.findAll();
    }

}
