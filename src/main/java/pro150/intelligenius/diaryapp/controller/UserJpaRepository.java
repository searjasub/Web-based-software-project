package pro150.intelligenius.diaryapp.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import pro150.intelligenius.diaryapp.model.User;

public interface UserJpaRepository extends JpaRepository<User, Integer> {
}
