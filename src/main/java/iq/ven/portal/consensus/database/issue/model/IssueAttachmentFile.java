package iq.ven.portal.consensus.database.issue.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "attachment_files")
public class IssueAttachmentFile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String uuid;

    private String fileName;

    private String fileType;

    private String fileSize;

    @Lob
    private byte[] data;

    public IssueAttachmentFile() {

    }

    public IssueAttachmentFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public IssueAttachmentFile(String fileName, String fileType, String fileSize, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.data = data;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
}