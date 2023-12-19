@miniTask
Feature: Check Mini Task API

#0: Không ưu tiên,
#1: Ưu tiên thấp,
#2: Ưu tiên trung bình,
#3: Ưu tiên cao
#Helper.convertDateToTimestamp(nextDay + " " + hour + ":" + minute);
#status
#0: Sẽ thực hiện,(cần làm)
#1: Đang thực hiện,
#2: Đã hoàn thành

  @task_description
  Scenario Outline: Create and edit task with title, content
    When  Enter the description "<description>"
    And   I create a task "<title>", "<workspace_id>"
    Then  Check success status
#    And   Check the task title "<title>"
#    And   Check the task description "<description>"
    When  I edit the task title "<workspace_id>", "<titleEdit>"
    And   I edit the task description "<workspace_id>", "<descriptionEdit>"
    Then  Check success status
    When  I want to view the task detail "<workspace_id>"
    Then  Check success status
#    And   Check the task title "<titleEdit>"
#    And   Check the task description "<descriptionEdit>"
    When  I delete the task "<workspace_id>"
    Then  Check success status
    When  I want to view the task detail "<workspace_id>"
    Then  Check failure status

    Examples:
      | title           | workspace_id    | description        | titleEdit              | descriptionEdit           |
      | Automation task | 581770764199841 | Automation content | Edited automation task | Edited automation content |