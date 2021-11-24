package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.controller;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.LoginDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }
//    @PostMapping()
//    public ResponseEntity<String> login (@Valid @RequestBody LoginDto loginDto) {
//        String token = getJWTToken(loginDto.getLogin());
//        User user = new User();
//        user.setName(loginDto.getName());
//        user.setLogin(loginDto.getLogin());
//        user.setPassword(loginDto.getPassword());
//        userService.save(user);
//        return new ResponseEntity<>(token, HttpStatus.CREATED);
//    }

//    public User login(@RequestBody LoginDto loginDto) {
//        String token = getJWTToken(loginDto.getLogin());
//        User user = new User();
//        user.setLogin(loginDto.getLogin());
//        user.setPassword(loginDto.getPassword());
//
//        userService.save(loginDto);
//        return user;
//    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
