package edu.t1.data;

public enum JobTitle {
    ANALYST( "Аналитик"),
    ARCHITECT( "Архитектор"),
    ENGINEER( "Инженер"),
    DEVELOPER( "Разработчик");

    private final String jobTitleName;

    JobTitle(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    public String getJobTitleName() {
        return jobTitleName;
    }
}
