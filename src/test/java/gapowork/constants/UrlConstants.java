package gapowork.constants;

public interface UrlConstants {
    // Auth service
    String CHECK_EMAIL_URL = "/auth/v3.0/check-email";
    String CHECK_PHONE_NUMBER_URL = "/auth/v3.0/check-phone-number";
    String LOGIN_URL = "/auth/v3.0/login";

    // Mini task service
    String CREATE_PROJECT_URL = "/mini-task/v1.0/projects";
    String EDIT_PROJECT_URL = "/mini-task/v1.0/projects/{projectId}";
    String GET_PROJECT_INFO_URL = "/mini-task/v1.0/projects/{projectId}";
    String GET_PROJECT_LIST_URL = "/mini-task/v1.0/projects";
    String DELETE_PROJECT_URL = "/mini-task/v1.0/projects/{projectId}";

    String CREATE_TASK_URL = "/mini-task/v1.0/tasks";
    String GET_TASK_LIST_URL = "/mini-task/v1.0/tasks";
    String EDIT_TASK_URL = "/mini-task/v1.0/tasks/{taskId}";
    String VIEW_TASK_DETAIL_URL = "/mini-task/v1.0/tasks/{taskId}";
    String DELETE_TASK_URL = "/mini-task/v1.0/tasks/{taskId}";

    String CREATE_FOLDER_URL = "/mini-task/v1.0/folders";
    String EDIT_FOLDER_URL = "/mini-task/v1.0/folders/{folderId}";
    String DELETE_FOLDER_URL = "/mini-task/v1.0/folders/{folderId}";
    String DUPLICATE_FOLDER_URL = "/mini-task/v1.0/folders/{folderId}/duplicate";

    String CREATE_TASK_LIST_URL = "/mini-task/v1.0/task-lists";
    String EDIT_TASK_LIST_URL = "/mini-task/v1.0/task-lists/{taskListId}";
    String DUPLICATE_TASK_LIST_URL = "/mini-task/v1.0/task-lists/{taskListId}/duplicate";
    String DELETE_TASK_LIST_URL = "/mini-task/v1.0/task-lists/{taskListId}";

    // Upload service
    String UPLOAD_FILE_URL = "https://upload.gapowork.vn/media/v1.0/files";
    String UPLOAD_IMAGE_URL = "https://upload.gapowork.vn/media/v1.0/images";
    String UPLOAD_VIDEO_URL = "https://upload.gapowork.vn/media/v1.0/videos";

    // Search service
    String SEARCH_USER_IN_WORKSPACE = "/search/v2.0/search-user-invite-workspace";
    String SEARCH_CHAT_CONVERSATION_URL = "/search/v2.0/search-chat-conversations";
    String SEARCH_DEPARTMENT_URL = "/search/v2.0/search-department";
    String SEARCH_ROLE_URL = "/search/v2.0/search-role";

    // Post service
    String CREATE_POST_URL = "/post/v1.0/posts";
    String UPDATE_POST_URL = "/post/v1.0/posts/{id}";
    String GET_POST_DETAIL_URL = "/post/v1.0/posts/{id}";
    String DELETE_POST_URL = "/post/v1.0/posts/{id}";
    String GET_BACKGROUND_POST_URL = "/sticker/v1.3/post/background";

    // Group service
    String GET_GROUP_LIST_URL = "/group/v1.1/groups";
}
