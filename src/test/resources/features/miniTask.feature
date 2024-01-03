@miniTask
Feature: Check Mini Task API

# priority
#   0: Không ưu tiên,
#   1: Ưu tiên thấp,
#   2: Ưu tiên trung bình,
#   3: Ưu tiên cao
# status
#   0: Chưa hoàn thành
#   2: Đã hoàn thành

#  @task_description
#  Scenario Outline: Create and edit task with content
#    When  Enter the description "<description>"
#    And   I create a task "<title>", "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    When  I want to view the task detail "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    And   Check the task title "<title>"
#    And   Check the task description "<description>"
#    When  I edit the task title "<workspace_id>", "<titleEdit>"
#    And   I edit the task description "<workspace_id>", "<descriptionEdit>"
#    Then  Check status successfully <successStatus>
#    When  I want to view the task detail "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    And   Check the task title "<titleEdit>"
#    And   Check the task description "<descriptionEdit>"
#    When  I delete the task "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    When  I want to view the task detail "<workspace_id>"
#    Then  Check for invalid status <failStatus>

#    Examples:
#      | title           | workspace_id    | description        | titleEdit              | descriptionEdit           | successStatus  |  failStatus  |
#      | Automation task | 581770764199841 | Automation content | Edited automation task | Edited automation content | 200            |  400         |

#  @task_priority&status
#  Scenario Outline: Create and edit task with priority, status
#    When  Enter the priority <priority>
#    And   Enter the status <status>
#    And   I create a task "<title>", "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    When  I want to view the task detail "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    And   Check the task priority <priority>
#    And   Check the task status <status>
#    When  I edit the task priority "<workspace_id>", <priorityEdit>
#    And   I edit the task status "<workspace_id>", <statusEdit>
#    Then  Check status successfully <successStatus>
#    When  I want to view the task detail "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    And   Check the task title "<title>"
#    And   Check the task priority <priorityEdit>
#    And   Check the task status <statusEdit>
#    When  I delete the task "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    When  I want to view the task detail "<workspace_id>"
#    Then  Check for invalid status <failStatus>
##
#    Examples:
#      | title                            | workspace_id    | priority | status | priorityEdit | statusEdit | successStatus  |  failStatus  |
#      | Automation task: No priority     | 581770764199841 | 0        | 0      | 3            | 2          | 200            |  400         |
#      | Automation task: Low priority    | 581770764199841 | 1        | 2      | 2            | 0          | 200            |  400         |
#      | Automation task: Medium priority | 581770764199841 | 2        | 0      | 1            | 2          | 200            |  400         |
#      | Automation task: High priority   | 581770764199841 | 3        | 2      | 0            | 0          | 200            |  400         |

#  @task_attachment
#  Scenario Outline: Create and edit task with attachment files
#    When Add attachments "<url>"
#    And  I create a task "<title>", "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    When I want to view the task detail "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    And   Check the task title "<title>"
#    And  Check the task has attachments "<name>"
#    When I continue to add attachment "<new_url>", "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    When I want to view the task detail "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    And   Check the task title "<title>"
#    And  Check the task has attachments "<new_name>"
#    When  I delete the task "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    When  I want to view the task detail "<workspace_id>"
#    Then  Check for invalid status <failStatus>
#
#    Examples:
#      | title                            | workspace_id    | url                                                                     | name                 | new_url                                                                 | new_name                                   | successStatus | failStatus  |
#      | Automation task with attachments | 581770764199841 | src/test/resources/datas/kazuha3.jpg,src/test/resources/datas/vid1.mp4  | kazuha3.jpg,vid1.mp4 | src/test/resources/datas/kazuha4.jpg,src/test/resources/datas/doan.docx | kazuha3.jpg,vid1.mp4,kazuha4.jpg,doan.docx | 200           | 400         |

#  @task_due-date
#  Scenario Outline: Create and edit task with due date
#    When Add due date
#    And I create a task "<title>", "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    When I want to view the task detail "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    And Check the task due date
#    And Check the task title "<title>"
#    When I edit the task due date "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    When I want to view the task detail "<workspace_id>"
#    And Check the task title "<title>"
#    And Check the task due date
#    When  I delete the task "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    When  I want to view the task detail "<workspace_id>"
#    Then  Check for invalid status <failStatus>
#
#    Examples:
#      | title                         | workspace_id    | successStatus | failStatus |
#      | Automation task with due date | 581770764199841 | 200           | 400        |

#  @task_assignee&watcher
#  Scenario Outline: Create and edit with assignee and watcher
#    When I add a assignee "<assignee_key>", "<workspace_id>"
#    And I add a watcher "<watcher_key>", "<workspace_id>"
#    And I create a task "<title>", "<workspace_id>"
#    Then Check status successfully <successStatus>
#    When  I want to view the task detail "<workspace_id>"
#    Then Check the task has assignee from id
#    And Check the task has watcher from id
#    And Check the task title "<title>"
#    When I continue to add assignee "<assignee_newKey>", "<workspace_id>"
#    Then Check status successfully <successStatus>
#    When I continue to add watcher "<watcher_newKey>", "<workspace_id>"
#    Then Check status successfully <successStatus>
#    When I want to view the task detail "<workspace_id>"
#    Then Check status successfully <successStatus>
#    And Check the task title "<title>"
#    Then Check the task has assignee from id
#    And Check the task has watcher from id
#    When  I delete the task "<workspace_id>"
#    Then  Check status successfully <successStatus>
#    When  I want to view the task detail "<workspace_id>"
#    Then  Check for invalid status <failStatus>
#
#    Examples:
#      | title                                     | workspace_id    | assignee_key | watcher_key | assignee_newKey | watcher_newKey  |successStatus | failStatus  |
#      | Automation task with assignee and watcher | 581770764199841 | t            | y           | a               | b               |200           | 400         |
