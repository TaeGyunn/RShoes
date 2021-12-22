package resell.shoes.RShoes.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import resell.shoes.RShoes.dto.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class User implements UserDetails {

    private Long userNo;       // 유저번호
    private String id;      // 아이디
    private String pw;      // 비밀번호
    private String userName;    // 이름
    private String email;   // 이메일
    private String phone;   // 핸드폰번호
    private String bank;    // 은행명
    private String account;   // 계좌번호
    private Role role;      // 권한



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        SimpleGrantedAuthority simple = new SimpleGrantedAuthority(this.role.getValue());
        roles.add(0, simple);
        return roles;
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
