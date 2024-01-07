package gapowork.pages.group;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.List;

import static gapowork.constants.UrlConstants.GET_GROUP_LIST_URL;
import static gapowork.hook.Base.workspace_id;
import static gapowork.pages.auth.AuthActions.access_token;
import static gapowork.pages.auth.AuthActions.user_id;

public class GroupActions {
    public Response groupListRes;

    @Step("Get group list")
    public Response getGroupList () {
        return SerenityRest
                .given()
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .queryParam("limit", 1)
                .when()
                .get(GET_GROUP_LIST_URL);
    }

    @Step("Get a group id")
    public String getGroupId () {
        groupListRes = getGroupList();
        int size = groupListRes.path("data.size()");
        System.out.println("size: " + size);
        String groupId = null;

        for (int i = 0; i < size; i++) {
            groupId = groupListRes.path("data["+i+"].id");
        }

        return groupId;
    }

    @Step("Check target")
    public String checkTarget (String target) {
        if (target.equals("group:")) {
            target = target + getGroupId();
        } else target = target + user_id;

        return target;
    }
}
