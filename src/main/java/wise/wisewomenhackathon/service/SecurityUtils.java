package wise.wisewomenhackathon.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtils {

    public Long getUserIdFromToken() {
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (authorities.stream().findFirst().isEmpty()) {
            throw new RuntimeException("Provided token does not contain authorities");
        }
        return Long.parseLong(authorities.stream().findFirst().get().toString());
    }
}
