package iq.ven.portal.consensus.services.data;

import iq.ven.portal.consensus.database.issue.model.IssueAttachmentFile;
import org.springframework.web.multipart.MultipartFile;

public interface IssueAttachmentFileDataService {
    IssueAttachmentFile saveFile(MultipartFile file);

    IssueAttachmentFile getFile(String fileId);
}
