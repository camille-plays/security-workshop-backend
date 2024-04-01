package wise.wisewomenhackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.wisewomenhackathon.model.UserEntity;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAll();
    Optional<UserEntity> findByUserId(Long userId);
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    UserEntity save(UserEntity user);
    void deleteById(Long userId);
}
