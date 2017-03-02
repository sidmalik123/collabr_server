package collabr.api;

import collabr.core.Match;

public class MatchDTO {

    int userId;

    int projectId;

    public MatchDTO(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
