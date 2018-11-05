package iq.ven.portal.consensus.database.issue.repository;

import iq.ven.portal.consensus.database.issue.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("issueRepository")
public interface IssueRepository extends JpaRepository<Issue, Long> {

    Issue findById(long id);

    Issue findByName(String name);

    Issue findByIssueKey(String issueKey);



}
