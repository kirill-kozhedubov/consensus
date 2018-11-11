package iq.ven.portal.consensus.services.data.impl;

import iq.ven.portal.consensus.database.issue.model.IssueAttachmentFile;
import iq.ven.portal.consensus.database.issue.repository.IssueAttachmentFileRepository;
import iq.ven.portal.consensus.services.data.IssueAttachmentFileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class IssueAttachmentFileDataServiceImpl implements IssueAttachmentFileDataService {

    @Autowired
    private IssueAttachmentFileRepository issueAttachmentFileRepository;


    public IssueAttachmentFile saveFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
               throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            IssueAttachmentFile issueAttachmentFile = new IssueAttachmentFile(fileName, file.getContentType(), file.getBytes());

            return issueAttachmentFileRepository.save(issueAttachmentFile);
        } catch (IOException ex) {
            throw new RuntimeException("Could not save file " + fileName + ". Please try again!", ex);
        }
    }

    public IssueAttachmentFile getFile(String fileId) {
        IssueAttachmentFile issueAttachmentFile = issueAttachmentFileRepository.findByUuid(fileId);
        if (issueAttachmentFile == null) {
            throw new RuntimeException("File not found with id " + fileId);
        }

        return issueAttachmentFile;
    }

}
