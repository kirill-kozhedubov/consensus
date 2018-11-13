package iq.ven.portal.consensus.database.issue.repository;

import iq.ven.portal.consensus.database.issue.model.IssueAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("issueAttachmentsRepository")
public interface IssueAttachmentsRepository extends JpaRepository<IssueAttachment, Long> {

    IssueAttachment findById(long id);

}
