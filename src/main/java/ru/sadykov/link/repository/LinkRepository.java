package ru.sadykov.link.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sadykov.link.model.Link;

@Repository
public interface LinkRepository extends JpaRepository<Link, String> {

    @Modifying
    @Query(value = "UPDATE link SET visits = ? WHERE short_link = ?", nativeQuery = true)
    void updateVisit(Integer visits, String shortLink);

}
