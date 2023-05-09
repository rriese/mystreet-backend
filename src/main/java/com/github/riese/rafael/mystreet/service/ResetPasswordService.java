package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.ResetPassword;
import com.github.riese.rafael.mystreet.repository.ResetPasswordRepository;
import com.github.riese.rafael.mystreet.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteTokensByEmail(String email) {
        var model = repository.findByEmail(email);

        if (model.isPresent()) {
            for (ResetPassword reset : model.get()) {
                super.delete(reset.getId());
            }
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Boolean> resetPassword(String email) throws Exception {
        var user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            ResetPassword reset = new ResetPassword();
            reset.setEmail(email);

            var token = UUID.randomUUID().toString();

            reset.setToken(token);
            repository.save(reset);
            mailService.sendEmail(/*email*/"rafariese@gmail.com", "Link para alteração de senha MyStreet", "Para alterar a senha, acesse o link https://mystreet-frontend.vercel.app/changepassword/"/*"Para alterar a senha, acesse o link http://localhost:3000/changepassword/"*/ + token);
            return ResponseEntity.ok().body(true);
        }
        throw new RuntimeException("Usuário válido não encontrado com este email!");
    }
}
