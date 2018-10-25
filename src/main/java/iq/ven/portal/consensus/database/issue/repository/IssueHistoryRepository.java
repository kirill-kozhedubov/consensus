package iq.ven.portal.consensus.database.issue.repository;

import iq.ven.portal.consensus.database.issue.model.IssueHistoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("issueHistoryRepository")
public interface IssueHistoryRepository extends JpaRepository<IssueHistoryEntry, Long> {
}
