@authentication
Feature: Check Authentication API

  @existent_email
  Scenario Outline: Check existent email
    When I want to check this email "<email>"
    Then Check status successfully <successStatus>


    Examples:
      | email                 | successStatus |
      | hnt12082001@gmail.com | 200           |

  @non-existent_email
  Scenario Outline: Check non-existent email
    When I want to check this email "<email>"
    Then Check for invalid status <failStatus>

    Examples:
      | email                  | failStatus |
      | hnt12082001@gmail.com1 | 400        |

  @existent_phone-number
  Scenario Outline: Check existent phone number
    When I want to check this phone number "<phone_number>"
    Then Check status successfully <successStatus>

    Examples:
      | phone_number  | successStatus |
      | 0977620025    | 200           |

  @non-existent_phone-number
  Scenario Outline: Check non-existent phone number
    When I want to check this phone number "<phone_number>"
    Then Check for invalid status <failStatus>

    Examples:
    | phone_number  | failStatus |
    | 09776200255   | 400        |

  @login_with_email_&_password
  Scenario Outline: Login with email and password
    When  I want to check this email "<email>"
    And I login with "<email>" and "<password>"
    Then Check status successfully <successStatus>

    Examples:
    | email                  | password     | successStatus |
    | hnt12082001@gmail.com  | Trung1208@gp | 200           |

  @login_with_phone-number_and_password
  Scenario Outline: Login with phone number and password
    When I login with "<phone_number>", "<password>"
    Then Check status successfully <failStatus>

    Examples:
      | phone_number  | password    | failStatus |
      | 0977620025    | Trung1@gapo | 400        |