package pro150.intelligenius.diaryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pro150.intelligenius.diaryapp.model.Profile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public void createProfile(@ModelAttribute("profile") Profile profile, HttpServletRequest request, HttpServletResponse response) throws IOException {
        profile.setUsername(profile.getUsername().toLowerCase());
        profileJpaRepository.save(profile);

        HttpSession session = request.getSession();
        session.setAttribute("username", profile.getUsername());
        session.setAttribute("name", profile.getName());
        session.setAttribute("entries", profile.getEntries());

        response.sendRedirect("/home");
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
    public void createProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../createProfile.jsp").forward(request, response);
    }


    @RequestMapping(path = "/edit", method = RequestMethod.PATCH)
    public void updateProfile(@ModelAttribute("profile") Profile profile,HttpServletRequest request,HttpServletResponse response) throws NoSuchFieldException, IllegalAccessException, IOException {
        HttpSession s = request.getSession();
        Profile p = profileJpaRepository.findById((String) s.getAttribute("username")).orElse(null);
        String newName = profile.getName();
        String newBirthDate = profile.getDateOfBirth();
        if (p != null) {
            p.setName(newName);
            p.setDateOfBirth(newBirthDate);
            profileJpaRepository.save(p);
        }
        response.sendRedirect("/home");
    }


    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public void editProfile(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            request.getRequestDispatcher("../userSettings.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(name = "/getProfiles", method = RequestMethod.GET)
    public List<Profile> getAllProfiles() {
        return profileJpaRepository.findAll();
    }

}

