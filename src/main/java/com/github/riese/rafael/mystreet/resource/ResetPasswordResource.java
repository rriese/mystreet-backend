package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.ResetPassword;
import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.service.ResetPasswordService;
import com.github.riese.rafael.mystreet.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/changepassword")
public class ResetPasswordResource {
    @Resource
    private ResetPasswordService resetPasswordService;
    @Resource
    private UserService userService;
    @Resource
    private PasswordEncoder encoder;

    @GetMapping("/{token}")
    public ResponseEntity<ResetPassword> getDataByToken(@PathVariable String token) {
        var data = resetPasswordService.findByToken(token);

        if (data != null) {
            return ResponseEntity.ok().body(data);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/sendemail/{email}")
    public ResponseEntity<Boolean> resetPassword(@PathVariable String email) throws Exception {
        return resetPasswordService.resetPassword(email);
    }

    @PostMapping("/{email}/{token}")
    public ResponseEntity<Boolean> changePassword(@PathVariable String email, @PathVariable String token, @RequestBody User user) throws Exception {
        var data = resetPasswordService.findByToken(token);

        if (data != null && data.getEmail().equals(email)) {
            var userOpt = userService.findByEmail(email);

            if (userOpt.isPresent()) {
                User newUser = userOpt.get();
                newUser.setPassword(encoder.encode(user.getPassword()));
                userService.updateUserPassword(newUser);
            }
            return ResponseEntity.ok().body(true);
        }
        throw new RuntimeException("Dados inválidos ao alterar senha, tente novamente.");
    }

}
