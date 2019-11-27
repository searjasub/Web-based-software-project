package pro150.intelligenius.diaryapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pro150.intelligenius.diaryapp.model.Profile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;

@RequestMapping(path = "/")
@Controller
public class LoginJpaController {

    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    ProfileJpaRepository profileJpaRepository;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("name");

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public void login(@ModelAttribute("profile") Profile profile, HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        Profile realProfile = profileJpaRepository.findById(profile.getUsername().toLowerCase()).orElse(null);

        HttpSession session = request.getSession();
        if(realProfile != null && profile.getPassword().equals(realProfile.getPassword())) {
            System.out.println(realProfile.getEntries());
            session.setAttribute("username", realProfile.getUsername());
            session.setAttribute("name", realProfile.getName());
            session.setAttribute("entries", realProfile.getEntries());
            response.sendRedirect("/home");
        } else {
            response.sendRedirect("");
        }
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username != null) {
            Profile profile = profileJpaRepository.findById(username).orElse(null);
            session.setAttribute("entries", profile.getEntries());
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            response.sendRedirect("");
        }
    }
}