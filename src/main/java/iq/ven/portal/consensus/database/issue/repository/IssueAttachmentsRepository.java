package iq.ven.portal.consensus.database.issue.repository;

import iq.ven.portal.consensus.database.issue.model.IssueAttachment;
import iq.ven.portal.consensus.database.issue.model.IssueAttachmentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("issueAttachmentsRepository")
public interface IssueAttachmentsRepository extends JpaRepository<IssueAttachment, Long> {

    IssueAttachmentFile findById(long id);

}
