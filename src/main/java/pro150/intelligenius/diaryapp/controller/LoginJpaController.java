package pro150.intelligenius.diaryapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pro150.intelligenius.diaryapp.model.Profile;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@RequestMapping(path = "/")
@Controller
public class LoginJpaController {

    private static final String salt = "LaundrySauce";
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    ProfileJpaRepository profileJpaRepository;

    public static boolean verifyPassword(String password, String key) {
        Optional<String> optEncrypted = hashPassword(password);
        if (!optEncrypted.isPresent()) return false;
        return optEncrypted.get().equals(key);
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

    @RequestMapping(path = "", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("name");
        request.getSession().removeAttribute("city");
        request.getSession().removeAttribute("birthday");

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public void login(@ModelAttribute("profile") Profile profile, HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        Profile realProfile = profileJpaRepository.findById(profile.getUsername().toLowerCase()).orElse(null);

        HttpSession session = request.getSession();

        if (realProfile != null) {
            boolean isCorrect = verifyPassword(profile.getPassword(), realProfile.getPassword());

            if (isCorrect) {
                session.setAttribute("username", realProfile.getUsername());
                response.sendRedirect("/home");
            } else {
                request.getSession().setAttribute("error", "Invalid username or password");
                response.sendRedirect("");
            }
        } else {
            request.getSession().setAttribute("error", "Invalid username or password");
            response.sendRedirect("");
        }
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            Profile profile = profileJpaRepository.findById(username).orElse(null);
            session.setAttribute("entries", profile.getEntries());
            session.setAttribute("name", profile.getName());
            session.setAttribute("city", profile.getCity());
            session.setAttribute("birthday", profile.getDateOfBirth());
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            session.setAttribute("error", "You must login.");
            response.sendRedirect("");
        }
    }
}