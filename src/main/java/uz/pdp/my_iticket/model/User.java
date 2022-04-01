package uz.pdp.my_iticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import uz.pdp.my_iticket.template.AbsEntity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User extends AbsEntity {
    @Column(nullable = false)
    @NotEmpty
    private String firstName;
    private String lastName;
    @Column(unique = true,nullable = false)
    @Size(min = 2)
    @NotBlank
    private String username;
    @Column(nullable = false)
    @Size(min = 8)
    private String password;
    @Column(unique = true, nullable = false)
    @Email
    private String email;
    @Column(unique = true)
    private String phoneNumber;
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }


}
