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
    public IssueAttachment saveIssueAttachment(IssueAttachment attachment) {
        IssueAttachment issueAttachment = issueAttachmentsRepository.save(attachment);

        return issueAttachment;
    }

}
