package iq.ven.portal.consensus.database;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "base")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Base implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date updatedDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoryEntry> history;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<HistoryEntry> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryEntry> history) {
        this.history = history;
    }

    @PrePersist
    protected void onCreate() {

    }

    @PreUpdate
    protected void onUpdate() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
