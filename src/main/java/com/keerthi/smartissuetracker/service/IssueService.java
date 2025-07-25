package com.keerthi.smartissuetracker.service;

import com.keerthi.smartissuetracker.dto.IssueRequestDto;
import com.keerthi.smartissuetracker.dto.IssueResponseDto;

import java.util.List;

public interface IssueService {
    IssueResponseDto createIssue(IssueRequestDto dto, String userEmail);
    List<IssueResponseDto> getAllIssues();
    IssueResponseDto updateIssueStatus(Long id, String status);
    void deleteIssue(Long id);
}
