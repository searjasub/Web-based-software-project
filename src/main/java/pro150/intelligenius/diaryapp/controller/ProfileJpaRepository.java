package pro150.intelligenius.diaryapp.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import pro150.intelligenius.diaryapp.model.Profile;

public interface ProfileJpaRepository extends JpaRepository<Profile, Integer> {
}
