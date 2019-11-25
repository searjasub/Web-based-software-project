package pro150.intelligenius.diaryapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pro150.intelligenius.diaryapp.model.Profile;

@RequestMapping(path = "/")
@Controller
public class LoginJpaController {

    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    ProfileJpaRepository profileJpaRepository;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(path = "/home", method = RequestMethod.POST)
    public ModelAndView home(@ModelAttribute("profile") Profile profile) {
//        profileJpaRepository.save(profile);
//        Profile profile1
//
//
//        if(){
//
//        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

}
3