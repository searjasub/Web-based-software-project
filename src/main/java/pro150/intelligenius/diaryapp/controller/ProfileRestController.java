package pro150.intelligenius.diaryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pro150.intelligenius.diaryapp.model.Profile;

import java.util.List;

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

//    @RequestMapping(path = "", method = RequestMethod.POST)
//    public ModelAndView createStudent(@RequestBody Profile newProfile) {
//        profileJpaRepository.save(newProfile);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("confirmation");
//        return modelAndView;
//    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView createStudent() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createProfile");
        return modelAndView;
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public ModelAndView home(@PathVariable String name){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        return mav;
    }


    @RequestMapping(name = "/getProfiles", method = RequestMethod.GET)
    public List<Profile> getAllProfiles() {
        return profileJpaRepository.findAll();
    }

}
