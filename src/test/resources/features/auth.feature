@authentication
Feature: Check Authentication API

  @existent_email
  Scenario Outline: Check existent email
    When I want to check this email "<email>"
    Then Check status successfully

    Examples:
      | email                 |
      | hnt12082001@gmail.com |

  @non-existent_email
  Scenario Outline: Check non-existent email
    When I want to check this email "<email>"
    Then Check for invalid status

    Examples:
      | email                  |
      | hnt12082001@gmail.com1 |

  @existent_phone-number
  Scenario Outline: Check existent phone number
    When I want to check this phone number "<phone_number>"
    Then Check status successfully

    Examples:
      | phone_number  |
      | 0977620025    |

  @non-existent_phone-number
  Scenario Outline: Check non-existent phone number
    When I want to check this phone number "<phone_number>"
    Then Check for invalid status

    Examples:
    | phone_number  |
    | 09776200255   |

  @login_with_email_&_password
  Scenario Outline: Login with email and password
    When I login with "<email>" and "<password>"
    Then Check status successfully

    Examples:
    | email                  | password     |
    | hnt12082001@gmail.com  | Trung1208@gp |

  @login_with_phone-number_and_password
  Scenario Outline: Login with phone number and password
    When I login with "<phone_number>", "<password>"
    Then Check status successfully

    Examples:
      | phone_number  | password    |
      | 0977620025    | Trung1@gapo |