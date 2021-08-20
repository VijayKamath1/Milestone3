package trident.healthx.milestone3.springoauth3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import trident.healthx.milestone3.springoauth3.domain.security.Authority;
import trident.healthx.milestone3.springoauth3.domain.security.User;
import trident.healthx.milestone3.springoauth3.repository.security.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JpaUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User name : "+username+" not found"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getEnabled(),user.getAccountNonLocked(),user.getAccountNonExpired(),user.getCredentialsNotExpired(),convertToAuthorities(user.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> convertToAuthorities ( Set<Authority> authorities ) {

            if(authorities!=null && authorities.size()>0){
                return authorities.stream().map(Authority::getRole).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
            }else{
                return new HashSet<>();
            }

    }
}
