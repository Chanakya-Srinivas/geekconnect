package com.studentassist.geekconnect.responsemodel;

import java.util.ArrayList;
import java.util.List;

public class SubmissionListResponseModel {
    private AssignmentResponseModel assignmentDetails;

    private List<AssignmentSubmissionResponseModel> userList;

    public SubmissionListResponseModel(AssignmentResponseModel assignmentDetails) {
        this.assignmentDetails = assignmentDetails;
        this.userList = new ArrayList<>();
    }

    public AssignmentResponseModel getAssignmentDetails() {
        return assignmentDetails;
    }

    public void setAssignmentDetails(AssignmentResponseModel assignmentDetails) {
        this.assignmentDetails = assignmentDetails;
    }

    public List<AssignmentSubmissionResponseModel> getUserList() {
        return userList;
    }

    public void setUserList(List<AssignmentSubmissionResponseModel> userList) {
        this.userList = userList;
    }
}
