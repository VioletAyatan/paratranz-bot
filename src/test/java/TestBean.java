import com.para.tranzai.TranzaiApplication;
import com.para.tranzai.para.entity.PageResult;
import com.para.tranzai.para.server.ParaService;
import com.para.tranzai.properties.TranzaiProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TranzaiApplication.class)
public class TestBean {

    @Autowired
    private ParaService paraService;
    @Autowired
    private TranzaiProperties tranzaiProperties;

    /*@Test
    void testApi() {
        PageResult<Project> pageResult = paraService.listProjects(GameEnum.STELLARIS);
        System.out.println("pageResult = " + pageResult);

        PageResult<Task> taskPageResult = paraService.listTasks(tranzaiProperties.getProjectId());
        System.out.println("taskPageResult = " + taskPageResult);

        PageResult<History> historyPageResult = paraService.listProjectHistories(tranzaiProperties.getProjectId());
        System.out.println("historyPageResult = " + historyPageResult);

        ProjectOverview projectOverview = paraService.getProjectOverview(tranzaiProperties.getProjectId());
        System.out.println("projectOverview = " + projectOverview);

        PageResult<Announcement> announcementPageResult = paraService.listAnnouncements(tranzaiProperties.getProjectId());
        System.out.println("announcementPageResult = " + announcementPageResult);

        List<File> fileList = paraService.listFiles(tranzaiProperties.getProjectId());
        System.out.println("fileList = " + fileList);

        PageResult<Terms> listTerms = paraService.listTerms(tranzaiProperties.getProjectId());
        System.out.println("listTerms = " + listTerms);

        List<Member> members = paraService.getMembers(tranzaiProperties.getProjectId());
        System.out.println("members = " + members);
    }*/

    @Test
    void testApi2() {
        PageResult<?> pageResult = paraService.listApplications(null, tranzaiProperties.getProjectId(), 0);
        System.out.println("pageResult = " + pageResult);
    }
}
