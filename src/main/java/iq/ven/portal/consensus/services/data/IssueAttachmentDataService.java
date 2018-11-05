package iq.ven.portal.consensus.services.data;

import iq.ven.portal.consensus.database.issue.model.IssueAttachment;

public interface IssueAttachmentDataService {

    IssueAttachment storeIssueAttachment(IssueAttachment attachment);

    IssueAttachment getIssueAttachment(long issueId);
}
