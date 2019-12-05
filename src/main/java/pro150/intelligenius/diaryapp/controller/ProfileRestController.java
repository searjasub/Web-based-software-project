package pro150.intelligenius.diaryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pro150.intelligenius.diaryapp.model.Profile;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@RestController
@RequestMapping("/profiles")
public class ProfileRestController {

    private static final String salt = "LaundrySauce";
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    @Autowired
    private ProfileJpaRepository profileJpaRepository;

    @RequestMapping(path = "/create-profile", method = RequestMethod.POST)
    public void createProfile(@ModelAttribute("profile") Profile profile, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = profile.getUsername().toLowerCase();
        Profile p = profileJpaRepository.findById(username).orElse(null);
        HttpSession session = request.getSession();

        if(p == null) {
            boolean wasError = false;
            profile.setUsername(username);
            String hashedPassword = hashPassword(profile.getPassword()).orElse(null);
            profile.setPassword(hashedPassword);
            if(hashedPassword != null) {
                profileJpaRepository.save(profile);
            } else {
                session.setAttribute("error", "There was an error.");
            }


            if(!wasError) {
                session.setAttribute("username", profile.getUsername());
                session.setAttribute("name", profile.getName());
                session.setAttribute("entries", profile.getEntries());
    
                response.sendRedirect("/home");
            }
        } else {
            session.setAttribute("error", "Username is taken, please try again.");
            response.sendRedirect("");
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public void createProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../createProfile.jsp").forward(request, response);
    }

    @RequestMapping(path = "/edit", method = RequestMethod.PATCH)
    public void updateProfile(@ModelAttribute("profile") Profile profile,HttpServletRequest request,HttpServletResponse response) throws NoSuchFieldException, IllegalAccessException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username != null) {
            Profile p = profileJpaRepository.findById(username).orElse(null);
            String newName = profile.getName();
            String newBirthDate = profile.getDateOfBirth();
            String city = profile.getCity();
            String state = profile.getState();
            if (p != null) {
                if(newName != null && !newName.trim().isEmpty()) {
                    p.setName(newName);
                }
                if(newBirthDate != null && !newBirthDate.trim().isEmpty()) {
                    p.setDateOfBirth(newBirthDate);
                }
                if(city != null && !city.trim().isEmpty()) {
                    p.setCity(city);
                }
                if(state != null && !state.trim().isEmpty()) {
                    p.setState(state);
                }
                profileJpaRepository.save(p);
            }
            response.sendRedirect("/home");
        } else {
            session.setAttribute("error", "You must login.");
            response.sendRedirect("../");
        }
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public void editProfile(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");

            if(username != null) {
                request.getRequestDispatcher("../userSettings.jsp").forward(request, response);
            } else {
                session.setAttribute("error", "You must login.");
                response.sendRedirect("../");
            }

        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private static Optional<String> hashPassword(String password) {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }
    }
}

