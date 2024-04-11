package lt.ca.javau9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau9.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
