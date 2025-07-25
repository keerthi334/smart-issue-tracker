package com.keerthi.smartissuetracker.service;

import com.keerthi.smartissuetracker.dto.IssueRequestDto;
import com.keerthi.smartissuetracker.dto.IssueResponseDto;
import com.keerthi.smartissuetracker.entity.Issue;
import com.keerthi.smartissuetracker.entity.User;
import com.keerthi.smartissuetracker.repository.IssueRepository;
import com.keerthi.smartissuetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public IssueResponseDto createIssue(IssueRequestDto dto, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Issue issue = new Issue();
        issue.setTitle(dto.getTitle());
        issue.setDescription(dto.getDescription());
        issue.setStatus("OPEN");
        issue.setCreatedAt(LocalDateTime.now());
        issue.setUser(user);

        Issue saved = issueRepository.save(issue);

        IssueResponseDto response = new IssueResponseDto();
        response.setId(saved.getId());
        response.setTitle(saved.getTitle());
        response.setDescription(saved.getDescription());
        response.setStatus(saved.getStatus());

        return response;
    }

    @Override
    public List<IssueResponseDto> getAllIssues() {
        List<Issue> issues = issueRepository.findAll();

        return issues.stream().map(issue -> {
            IssueResponseDto dto = new IssueResponseDto();
            dto.setId(issue.getId());
            dto.setTitle(issue.getTitle());
            dto.setDescription(issue.getDescription());
            dto.setStatus(issue.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public IssueResponseDto updateIssueStatus(Long id, String status) {
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        issue.setStatus(status);
        Issue updated = issueRepository.save(issue);

        IssueResponseDto dto = new IssueResponseDto();
        dto.setId(updated.getId());
        dto.setTitle(updated.getTitle());
        dto.setDescription(updated.getDescription());
        dto.setStatus(updated.getStatus());

        return dto;
    }

    @Override
    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);
    }
}
