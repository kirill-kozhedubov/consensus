package iq.ven.portal.consensus.database.issue.repository;

import iq.ven.portal.consensus.database.issue.model.IssueComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("issueCommentsRepository")
public interface IssueCommentsRepository extends JpaRepository<IssueComment, Long> {
}
