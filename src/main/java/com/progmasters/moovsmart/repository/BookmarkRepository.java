package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("SELECT b FROM Bookmark b WHERE b.property.id = :propertyId AND b.account.id = :accountId")
    Optional<Bookmark> findByForeignKeys(Long accountId, Long propertyId);

    @Query("SELECT b.property.id FROM Bookmark b WHERE b.account.id = :accountId")
    List<Long> getPropertyIdByAccountId(Long accountId);
}
