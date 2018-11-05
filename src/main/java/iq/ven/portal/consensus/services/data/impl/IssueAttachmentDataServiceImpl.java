package iq.ven.portal.consensus.services.data.impl;

import iq.ven.portal.consensus.database.issue.model.IssueAttachment;
import iq.ven.portal.consensus.database.issue.repository.IssueAttachmentsRepository;
import iq.ven.portal.consensus.services.data.IssueAttachmentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueAttachmentDataServiceImpl implements IssueAttachmentDataService {


    @Autowired
    IssueAttachmentsRepository issueAttachmentsRepository;

    @Override
    public IssueAttachment storeIssueAttachment(IssueAttachment attachment) {
        IssueAttachment issueAttachment = issueAttachmentsRepository.save(attachment);

        return issueAttachment;
    }

    @Override
    public IssueAttachment getIssueAttachment(String issueKey) {
        IssueAttachment issueAttachment = issueAttachmentsRepository.findByIssue_IssueKey(issueKey);


        return null;
    }
}
