import com.para.tranzai.constant.GameEnum;
import com.para.tranzai.para.entity.IssuePageResult;
import com.para.tranzai.para.entity.PageResult;
import com.para.tranzai.para.entity.data.*;
import com.test.TestApplication;
import com.test.server.ParaService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = TestApplication.class)
public class ApiTest {

    @Autowired
    private ParaService paraService;

    /**
     * paraTranz-webapi测试.
     */
    @Test
    void apiTest() {
        String projectId = "967";
        PageResult<Announcement> announcementPageResult = paraService.listAnnouncements(projectId);
        System.out.println("announcementPageResult = " + announcementPageResult);
        PageResult<Application> applicationPageResult = paraService.listApplications(projectId);
        System.out.println("applicationPageResult = " + applicationPageResult);
        List<File> files = paraService.listFiles(projectId);
        System.out.println("files = " + files);
        IssuePageResult<Issue> issueIssuePageResult = paraService.listIssues(projectId, 0);
        System.out.println("issueIssuePageResult = " + issueIssuePageResult);
        PageResult<Project> projectPageResult = paraService.listProjects(GameEnum.STELLARIS);
        System.out.println("projectPageResult = " + projectPageResult);
        PageResult<Task> taskPageResult = paraService.listTasks(projectId);
        System.out.println("taskPageResult = " + taskPageResult);
        PageResult<Terms> termsPageResult = paraService.listTerms(projectId);
        System.out.println("termsPageResult = " + termsPageResult);
        PageResult<History> historyPageResult = paraService.listProjectHistories(projectId);
        System.out.println("historyPageResult = " + historyPageResult);
    }
}
