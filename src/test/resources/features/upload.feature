@upload
Feature: Check Upload API

  @upload_media
  Scenario Outline: Check upload media
    When I upload media "<fileName>" "<type>"
    Then Check status successfully <successStatus>

    Examples:
      | fileName                             | type  | successStatus |
      | src/test/resources/datas/kazuha3.jpg | image | 200           |
      | src/test/resources/datas/vid1.mp4    | video | 200           |
