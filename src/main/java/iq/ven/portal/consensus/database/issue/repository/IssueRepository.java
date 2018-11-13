package iq.ven.portal.consensus.database.issue.repository;

import iq.ven.portal.consensus.database.issue.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("issueRepository")
public interface IssueRepository extends JpaRepository<Issue, Long> {

    Issue findById(long id);

    Issue findByName(String name);

    List<Issue> findByNameContainingIgnoreCase(String namePart);

}
