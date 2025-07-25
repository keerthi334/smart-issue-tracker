package com.keerthi.smartissuetracker.controller;

import com.keerthi.smartissuetracker.dto.IssueRequestDto;
import com.keerthi.smartissuetracker.dto.IssueResponseDto;
import com.keerthi.smartissuetracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping
    public IssueResponseDto createIssue(@RequestBody IssueRequestDto dto,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        return issueService.createIssue(dto, userDetails.getUsername());
    }

    @GetMapping
    public List<IssueResponseDto> getAllIssues() {
        return issueService.getAllIssues();
    }

    @PutMapping("/{id}")
    public IssueResponseDto updateStatus(@PathVariable Long id,
                                         @RequestParam String status) {
        return issueService.updateIssueStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public String deleteIssue(@PathVariable Long id) {
        issueService.deleteIssue(id);
        return "Issue deleted successfully.";
    }
}
