package iq.ven.portal.consensus.controllers.issues.attachments;


import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.controllers.issues.attachments.payload.UploadFileResponse;
import iq.ven.portal.consensus.database.issue.model.IssueAttachmentFile;
import iq.ven.portal.consensus.services.data.IssueAttachmentDataService;
import iq.ven.portal.consensus.services.data.IssueAttachmentFileDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class IssueAttachmentsResourceController extends AbstractController{

    private static final Logger logger = LoggerFactory.getLogger(IssueAttachmentsResourceController.class);

    @Autowired
    private IssueAttachmentFileDataService issueAttachmentFileDataService;

    @Autowired
    private IssueAttachmentDataService issueAttachmentDataService;


    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        IssueAttachmentFile dbFile = issueAttachmentFileDataService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        IssueAttachmentFile dbFile = issueAttachmentFileDataService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}