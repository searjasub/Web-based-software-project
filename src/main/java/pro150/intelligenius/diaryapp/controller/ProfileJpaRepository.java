package pro150.intelligenius.diaryapp.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro150.intelligenius.diaryapp.model.Profile;

public interface ProfileJpaRepository extends JpaRepository<Profile, Integer> {

    Profile findByName(@Param(value = "searchText") String searchText);

    Profile findByProfileId(int id);

}
