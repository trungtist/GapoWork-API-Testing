@post
Feature: Check Post API

  @post_textContent
  Scenario Outline: Create and update post with text content
    When I create post with text content "<content>", privacy <privacy> and target "<target>"
    Then Check create successfully <createdStatus>
    And Check message "<createdMessage>"
#    When I want to view the post detail
#    Then Check status successfully <successStatus>
#    When I update post "<contentEdit>"
#    Then Check status successfully <successStatus>
#    When I want to view the post detail
#    Then Check status successfully <successStatus>
#    When I delete the post
#    Then Check status successfully <successStatus>
#    And Check message "<deletedMessage>"

    Examples: Post on the user's timeline
      | content                            | privacy | target | createdMessage          | createdStatus | successStatus | deletedMessage          |
      | Automation post with normal text   | 5       | user:  | Tạo bài viết thành công | 201           | 200           | Xoá bài viết thành công |
      | Automation post with hashtag #text | 5       | user:  | Tạo bài viết thành công | 201           | 200           | Xoá bài viết thành công |
      | Automation post with emoji 😎♌🤔 | 5       | user:  | Tạo bài viết thành công | 201           | 200           | Xoá bài viết thành công |
