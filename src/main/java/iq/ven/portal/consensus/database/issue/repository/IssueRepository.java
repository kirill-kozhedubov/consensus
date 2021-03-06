package iq.ven.portal.consensus.database.issue.repository;

import iq.ven.portal.consensus.database.issue.model.Issue;
import iq.ven.portal.consensus.database.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("issueRepository")
public interface IssueRepository extends JpaRepository<Issue, Long> {

    Issue findById(Long id);

    Issue findByName(String name);

    List<Issue> findByNameContainingIgnoreCase(String namePart);

    Issue findByIdAndProject(Long id, Project project);

}
