@project
Feature: Check Mini Task API - Project

  @project_name
  Scenario Outline: Create and edit project with name
    When I create a project with just name "<name>"
    Then Check status successfully <successStatus>
    When I get the project info
    Then Check status successfully <successStatus>
    And Check the project name "<name>"
    When I edit the project name "<name_edit>"
    Then Check status successfully <successStatus>
    When I get the project info
    Then Check status successfully <successStatus>
    And Check the project name "<name_edit>"
    When I delete the project
    Then Check status successfully <successStatus>

    Examples:
      | name                    | name_edit                    | successStatus |
      | Automation project name | Automation edit project name | 200           |

  @project_invalidName
  Scenario Outline: Create and edit project with invalid name
    When I create a project with just name "<invalidName>"
    Then Check for invalid status <failStatus>

    Examples:
      | invalidName                                                                                           | failStatus |
      | Automation project with invalid name Automation project with invalid name Automation project with inv | 400        |

  @project_addMember
  Scenario Outline: Create a project with member
    When I add members "<memberKey>"
    Then Check status successfully <successStatus>
    When I create a project with members "<name>", "<memberType>"
    Then Check status successfully <successStatus>
    When I get the project info
    Then Check status successfully <successStatus>
    And Check the project has members
    When I delete the project
    Then Check status successfully <successStatus>

    Examples:
      | name                            | memberType | memberKey | successStatus |
      | Automation project with members | member     | a,b,c,d   | 200           |

  @project_addThread
  Scenario Outline: Create a project with thread
    When I add threads "<threadKey>"
    Then Check status successfully <successStatus>
    When I create a project with members "<name>", "<memberType>"
    Then Check status successfully <successStatus>
    When I get the project info
    Then Check status successfully <successStatus>
    And Check the project has members
    When I delete the project
    Then Check status successfully <successStatus>

    Examples:
      | name                            | memberType | threadKey  | successStatus |
      | Automation project with threads | thread     | ohhh,Hoàng | 200           |

  @project_addDepartment
  Scenario Outline: Create a project with department
    When I add departments "<departmentKey>"
    Then Check status successfully <successStatus>
    When I create a project with members "<name>", "<memberType>"
    Then Check status successfully <successStatus>
    When I get the project info
    Then Check status successfully <successStatus>
    And Check the project has members
    When I delete the project
    Then Check status successfully <successStatus>

    Examples:
      | name                                | memberType | departmentKey | successStatus |
      | Automation project with departments | department | a s,hi        | 200           |

  @project_addRole
  Scenario Outline: Create a project with role
    When I add roles "<roleKey>"
    Then Check status successfully <successStatus>
    When I create a project with members "<name>", "<memberType>"
    Then Check status successfully <successStatus>
    When I get the project info
    Then Check status successfully <successStatus>
    And Check the project has members
    When I delete the project
    Then Check status successfully <successStatus>

    Examples:
      | name                          | memberType | roleKey | successStatus |
      | Automation project with roles | role       | abc,m   | 200           |

  @project_allInfo
  Scenario Outline: Create project with all information
    When I add members "<memberKey>"
    Then Check status successfully <successStatus>
    When I add threads "<threadKey>"
    Then Check status successfully <successStatus>
    When I add departments "<departmentKey>"
    Then Check status successfully <successStatus>
    When I add roles "<roleKey>"
    Then Check status successfully <successStatus>
    When I create a project with members "<name>", "<memberType>"
    Then Check status successfully <successStatus>
    When I get the project info
    Then Check status successfully <successStatus>
    And Check the project has members
    When I delete the project
    Then Check status successfully <successStatus>

    Examples:
      | name                                    | memberKey | threadKey  | departmentKey | roleKey | memberType | successStatus |
      | Automation project with all information | d,e,f,g   | ohhh,Hoàng | a,b           | abc,m   | all        | 200           |

  @project_createFolder
  Scenario Outline: Create folder after create project
    When I create a project with just name "<projectName>"
    Then Check status successfully <successStatus>
    When I create a folder "<folderName>"
    Then Check status successfully <successStatus>
    And Check the folder name "<folderName>"
    When I edit the folder name "<folderNameEdit>"
    Then Check status successfully <successStatus>
    When I duplicate the folder
    Then Check status successfully <successStatus>
    And Check the duplicated folder id
    When I delete the folder
    Then Check status successfully <successStatus>

    Examples:
      | projectName                   | folderName        | folderNameEdit         | successStatus |
      | Automation project has folder | Automation folder | Edit automation folder | 200           |

