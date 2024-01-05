package gapowork.constants;

public interface UrlConstants {

    // Auth service
    String CHECK_EMAIL_URL = "/auth/v3.0/check-email";
    String CHECK_PHONE_NUMBER_URL = "/auth/v3.0/check-phone-number";
    String LOGIN_URL = "/auth/v3.0/login";

    // Mini task service
    String CREATE_TASK_URL = "/mini-task/v1.0/tasks";

    String GET_TASK_LIST_URL = "/mini-task/v1.0/tasks";

    String EDIT_TASK_URL = "/mini-task/v1.0/tasks/{taskId}";

    String VIEW_TASK_DETAIL_URL = "/mini-task/v1.0/tasks/{taskId}";

    String DELETE_TASK_URL = "/mini-task/v1.0/tasks/{taskId}";

    // Upload service
    String UPLOAD_FILE_URL = "https://upload.gapowork.vn/media/v1.0/files";

    // Search service
    String SEARCH_USER_IN_WORKSPACE = "/search/v2.0/search-user-invite-workspace";

    // Post service
    String CREATE_POST_URL = "/post/v1.0/posts";
    String UPDATE_POST_URL = "/post/v1.0/posts/{id}";
}
