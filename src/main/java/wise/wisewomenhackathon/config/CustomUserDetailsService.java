package wise.wisewomenhackathon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wise.wisewomenhackathon.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var enduser = userRepository.findByUsername(username);
        SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority(enduser.getUserId().toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(userAuthority);
        return new User(enduser.getUsername(), enduser.getPassword(), authorities);
    }

}
