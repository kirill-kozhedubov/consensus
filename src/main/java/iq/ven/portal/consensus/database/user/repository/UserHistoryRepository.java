package iq.ven.portal.consensus.database.user.repository;

import iq.ven.portal.consensus.database.user.model.UserHistoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userHistoryRepository")
public interface UserHistoryRepository  extends JpaRepository<UserHistoryEntry, Long> {
}
