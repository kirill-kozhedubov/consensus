package iq.ven.portal.consensus.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository  extends JpaRepository<HistoryEntry, Long> {

    HistoryEntry findHistoryEntriesByEntityId(Long entityId);
}
