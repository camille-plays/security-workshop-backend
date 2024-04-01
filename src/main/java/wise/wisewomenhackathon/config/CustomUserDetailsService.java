package wise.wisewomenhackathon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wise.wisewomenhackathon.model.UserEntity;
import wise.wisewomenhackathon.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
        return new User(user.getUsername(), user.getPassword(), getUserAuthority(user));
    }


    private Collection<GrantedAuthority> getUserAuthority(UserEntity user) {
        SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority(user.getUserId().toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(userAuthority);
        return authorities;
    }
}
