package pro150.intelligenius.diaryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pro150.intelligenius.diaryapp.model.Profile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profiles")
public class ProfileRestController {

    @Autowired
    private ProfileJpaRepository profileJpaRepository;

    @RequestMapping(path = "/create-profile", method = RequestMethod.POST)
    public void createProfile(@ModelAttribute("profile") Profile profile, HttpServletResponse response) throws IOException {
        profileJpaRepository.save(profile);

        response.sendRedirect("/home");
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("home");
//        return modelAndView;
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public ModelAndView getProfileByName(@PathVariable String name) {
        ModelAndView mav = new ModelAndView();
        Profile profile = profileJpaRepository.findById(name).orElse(null);
        mav.addObject("profile", profile);
        mav.setViewName("showProfile");
        return mav;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView createProfile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createProfile");
        return modelAndView;
    }


    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public ModelAndView updateProfile(Principal principal, @ModelAttribute("update") Profile updates) throws NoSuchFieldException, IllegalAccessException {

        Profile profile = profileJpaRepository.findById(principal.getName()).orElse(null);
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

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public ModelAndView displayProfileEdit(Principal principal){
        Profile current = profileJpaRepository.findById(principal.getName()).orElse(null);
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
