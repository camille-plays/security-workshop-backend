package wise.wisewomenhackathon.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name="salt")
    @NotNull
    private String salt;

    @Column(name = "password_hash")
    @NotNull
    private String passwordHash;

    public User() {
    }

    public User(String username, String salt, String passwordHash) {
        this.username = username;
        this.salt = salt;
        this.passwordHash = passwordHash;
    }
}