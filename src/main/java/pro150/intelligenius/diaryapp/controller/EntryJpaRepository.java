package pro150.intelligenius.diaryapp.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import pro150.intelligenius.diaryapp.model.Entry;

public interface EntryJpaRepository extends JpaRepository<Entry,String> {
}
