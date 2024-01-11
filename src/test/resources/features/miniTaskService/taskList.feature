@taskList
Feature: Check Mini Task API - Task list

  @taskList_createTaskList
  Scenario Outline: Create and edit task list in folder or outside folder
    When I create a task list "<taskListName>", "<inFolder>"
    Then Check status successfully <successStatus>
    And Check the task list name "<taskListName>"
    When I edit the task list "<taskListNameEdit>", "<description>", "<contentRtf>"
    Then Check status successfully <successStatus>
    And Check the task list name "<taskListNameEdit>"
    When I delete the task list
    Then Check status successfully <successStatus>

    Examples:
      | taskListName                        | inFolder | taskListNameEdit                         | description                                                                                                                                                                                                                                | contentRtf | successStatus |
      | Automation task list in folder      | Yes      | Edit automation task list in folder      | Automation desc of task list in folder                                                                                                                                                                                                     | false      | 200           |
      | Automation task list outside folder | No       | Edit automation task list outside folder | # Thẻ h1\n## Thẻ h2\n**Thẻ B**\n*Thẻ i*\n~~thẻ gạch ngang~~\n```\nthẻ <>\n```\n> thẻ ngoặc kép\n\n1. thẻ number đầu dòng\n2. thẻ number đầu dòng\n-  Thẻ dấu chấm đầu dòng [thẻ link rút gọn](https://www.youtube.com/watch?v=yiSXPxI1rMA) | true       | 200           |

  @taskList_duplicateTaskList
  Scenario Outline: Create and duplicate task list in folder or outside folder
    When I create a task list "<taskListName>", "<inFolder>"
    Then Check status successfully <successStatus>
    When I duplicate the task list
    Then Check status successfully <successStatus>
    And Check the duplicated task list
    When I delete the task list
    Then Check status successfully <successStatus>

    Examples:
      | taskListName                                     | inFolder | successStatus |
      | Automation task list to duplicate in folder      | Yes      | 200           |
      | Automation task list to duplicate outside folder | No       | 200           |
