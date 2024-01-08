@post
Feature: Check Post API

  @post_textContent
  Scenario Outline: Create and update post with text content
    When I create post with text content "<content>", privacy <privacy> and target "<target>"
    Then Check create successfully <createdStatus>
    And Check message "<createdMessage>"
    When I want to view the post detail
    Then Check status successfully <successStatus>
    And Check the post text content
    When I update post "<contentEdit>"
    Then Check status successfully <successStatus>
    When I want to view the post detail
    Then Check status successfully <successStatus>
    And Check the post text content
    When I delete the post
    Then Check status successfully <successStatus>
    And Check message "<deletedMessage>"

    @user_timeline
    Examples: Post on the user's timeline
      | content                            | privacy | target | contentEdit                            | createdMessage          | createdStatus | successStatus | deletedMessage          |
      | Automation post with normal text   | 5       | user:  | Edit content with hashtag #editContent | Tạo bài viết thành công | 201           | 200           | Xóa bài viết thành công |
      | Automation post with hashtag #text | 5       | user:  | Edit content with emoji 😎♌            | Tạo bài viết thành công | 201           | 200           | Xóa bài viết thành công |
      | Automation post with emoji 😎♌🤔   | 5       | user:  | Edit  content with text                | Tạo bài viết thành công | 201           | 200           | Xóa bài viết thành công |

    @group_timeline
    Examples: Post on the group's timeline
      | content                                                | privacy | target | contentEdit                                                  | createdMessage          | createdStatus | successStatus | deletedMessage          |
      | Automation post on the group timeline with normal text | 4       | group: | Edit content on the group timeline with hashtag #editContent | Tạo bài viết thành công | 201           | 200           | Xóa bài viết thành công |

  @post_emptyContent
  Scenario Outline: Create post with empty content
    When I create post with text content "<content>", privacy <privacy> and target "<target>"
    Then Check for incorrect precondition <preconditionFailedStatus>
    And Check message "<message>"

    Examples:
      | content | privacy | target | preconditionFailedStatus | message               |
      |         | 5       | user:  | 412                      | Tham số không hợp lệ. |
      |         | 4       | group: | 412                      | Tham số không hợp lệ. |

  @post_media
  Scenario Outline: Create post with media
    When I create post with medias "<content>", "<type>", "<fileName>", privacy <privacy> and target "<target>"
    Then Check create successfully <createdStatus>
    And Check message "<createdMessage>"
    When I delete the post
    Then Check status successfully <successStatus>
    And Check message "<deletedMessage>"

    Examples:
      | content                             | fileName    | type  | privacy | target | createdMessage          | createdStatus | successStatus | deletedMessage          |
      | Automation post with medias - image | kazuha4.jpg | image | 5       | user:  | Tạo bài viết thành công | 201           | 200           | Xóa bài viết thành công |
      | Automation post with medias - video | vid1.mp4    | video | 5       | user:  | Tạo bài viết thành công | 201           | 200           | Xóa bài viết thành công |
      | Automation post with medias - gif   | giphy.gif   | image | 4       | group: | Tạo bài viết thành công | 201           | 200           | Xóa bài viết thành công |

  @post_invalidMedia
  Scenario Outline: Create post with invalid media
    When I create post with medias "<content>", "<type>", "<fileName>", privacy <privacy> and target "<target>"
    Then Check for incorrect precondition <preconditionFailedStatus>
    And Check message "<message>"

    Examples:
      | content       | fileName    | type  | privacy | target | preconditionFailedStatus | message               |
      | Invalid media | kazuha4.jpg | image | 5       | user:  | 412                      | Tham số không hợp lệ. |

  @post_background
  Scenario Outline: Create post with background
    When I create post with background "<content>", "<type>", privacy <privacy> and target "<target>"
    Then Check create successfully <createdStatus>
    And Check message "<createdMessage>"
    When I want to view the post detail
    Then Check status successfully <successStatus>
    And Check the background post
    When I update post with new background "<contentEdit>", "<type>"
    Then Check status successfully <successStatus>
    When I want to view the post detail
    Then Check status successfully <successStatus>
    And Check the background post
    When I delete the post
    Then Check status successfully <successStatus>
    And Check message "<deletedMessage>"

    Examples:
      | content                                        | type       | privacy | target | contentEdit                     | createdMessage          | createdStatus | successStatus | deletedMessage          |
      | Automation content with background #background | background | 5       | user:  | Edit content background #editBg | Tạo bài viết thành công | 201           | 200           | Xóa bài viết thành công |

  @post_invalidBackground
  Scenario Outline: Create post with invalid background
    When I create post with background "<content>", "<type>", privacy <privacy> and target "<target>"
    Then Check for incorrect precondition <preconditionFailedStatus>
    And Check message "<message>"

    Examples:
      | content            | type       | privacy | target | preconditionFailedStatus | message               |
      | Invalid background | background | 5       | user:  | 412                      | Tham số không hợp lệ. |

  @get_background
  Scenario: Get background post
    When Get bg
    Then Check status successfully 200


