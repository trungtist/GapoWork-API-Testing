@authentication
Feature: Check Authentication API

  @TC01_existent_email
  Scenario Outline: Check existent email
    When I want to check this email "<email>"
    Then Check status successfully <successStatus>


    Examples:
      | email                 | successStatus |
      | hnt12082001@gmail.com | 200           |

  @TC02_non-existent_email
  Scenario Outline: Check non-existent email
    When I want to check this email "<email>"
    Then Check for not found status <notFoundStatus>
    And Check message "<message>"

    Examples:
      | email             | notFoundStatus | message                                                                                                             |
      | hnt1208@gmail.com | 404            | Email của bạn chưa đăng ký GapoWork. Vui lòng sử dụng chức năng Đăng ký tài khoản hoặc liên hệ CSKH để được hỗ trợ. |

  @TC03_existent_phone-number
  Scenario Outline: Check existent phone number
    When I want to check this phone number "<phone_number>"
    Then Check status successfully <successStatus>

    Examples:
      | phone_number | successStatus |
      | 0977620025   | 200           |

  @TC04_non-existent_phone-number
  Scenario Outline: Check non-existent phone number
    When I want to check this phone number "<phone_number>"
    Then Check for not found status <notFoundStatus>
    And Check message "<message>"

    Examples:
      | phone_number | notFoundStatus | message                                                                    |
      | 0977620026   | 404            | Số điện thoại chưa đăng ký GapoWork. Vui lòng liên hệ CSKH để được hỗ trợ. |

  @TC05_login_with_validEmail
  Scenario Outline: Login with valid email
    When  I want to check this email "<email>"
    Then Check status successfully <successStatus>
    When I login with "<email>" and "<password>"
    Then Check status successfully <successStatus>

    Examples:
      | email                 | password     | successStatus |
      | hnt12082001@gmail.com | Trung1208@gp | 200           |

  @TC06_login_with_invalidEmail
  Scenario Outline: Login with invalid email
    When I login with "<email>" and "<password>"
    Then Check for invalid status <failStatus>

    Examples:
      | email                | password     | failStatus |
      | hnt12082001@gmail.co | Trung1208@gp | 400        |

  @TC07_login_with_validPhone-number
  Scenario Outline: Login with valid phone number
    When I want to check this phone number "<phone_number>"
    Then Check status successfully <successStatus>
    When I login with "<phone_number>", "<password>"
    Then Check status successfully <successStatus>

    Examples:
      | phone_number | password    | successStatus |
      | 0977620025   | Trung1@gapo | 200           |

  @TC08_login_with_invalidPhone-number
  Scenario Outline: Login with invalid phone-number
    When I login with "<phone_number>", "<password>"
    Then Check for invalid status <failStatus>
    And Check message "<message>"

    Examples:
      | phone_number | password     | failStatus | message                                                                                                    |
      | 0977620026   | Trung1208@gp | 400        | Số điện thoại không tồn tại trên hệ thống. Bạn vui lòng chọn phương thức đăng ký để đăng ký tài khoản mới! |