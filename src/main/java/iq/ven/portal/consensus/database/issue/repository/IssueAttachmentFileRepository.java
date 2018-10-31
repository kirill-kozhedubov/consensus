package iq.ven.portal.consensus.database.issue.repository;

import iq.ven.portal.consensus.database.issue.model.IssueAttachmentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("issueAttachmentFileRepository")
public interface IssueAttachmentFileRepository extends JpaRepository<IssueAttachmentFile, String> {

    IssueAttachmentFile findById(String id);
}
