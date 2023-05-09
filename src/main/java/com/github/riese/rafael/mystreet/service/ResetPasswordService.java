package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.ResetPassword;
import com.github.riese.rafael.mystreet.repository.ResetPasswordRepository;
import com.github.riese.rafael.mystreet.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class ResetPasswordService extends ServiceBase<ResetPassword, ResetPasswordRepository> {
    @Resource
    private UserRepository userRepository;
    @Resource
    private MailService mailService;

    protected ResetPasswordService(ResetPasswordRepository resetPasswordRepository) {
        super(resetPasswordRepository);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResetPassword findByToken(String token) {
        var model = repository.findByToken(token);

        if (model.isPresent()) {
            return model.get();
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseEntity<Boolean> resetPassword(String email) throws Exception {
        var user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            ResetPassword reset = new ResetPassword();
            reset.setEmail(email);

            var token = UUID.randomUUID().toString();

            reset.setToken(token);
            repository.save(reset);
            mailService.sendEmail(/*email*/"rafariese@gmail.com", "Reset password link", "Para alterar a senha, acesse o link https://mystreet-frontend.vercel.app/changepassword/" + token);
            return ResponseEntity.ok().body(true);
        }
        throw new RuntimeException("Usuário válido não encontrado com este email!");
    }
}
