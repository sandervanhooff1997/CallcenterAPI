import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import domain.models.*;
import domain.models.Auth.Employee;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CallTest {

    private int port = 9999;
    @Test
    public void callConstruct() {
        Date d = new Date();
        Customer cu = new Customer("John", "Doe", new ArrayList());
        Company co = new Company("company1", new ArrayList(), new ArrayList());
        Employee e = new Employee("Employee", "10", "employee@company.nl", new ArrayList(), co);
        List<Subscription> s = new ArrayList();
        Call c = new Call(10, d, cu, e, s);

        assertEquals(c.getDuration(), 10);
        assertEquals(c.getDate(), d);
        assertEquals(c.getCustomer(), cu);
        assertEquals(c.getEmployee(), e);
        assertEquals(c.getSubscriptions(), s);
    }

    @Rule
    public WireMockRule wiremockRule = new WireMockRule(port);

    @Test
    public void CreateCall(){
        Call c = new Call();
        c.setDuration(10);

        WireMock wiremock = new WireMock(port);

        wiremock.register(post(urlEqualTo("/call"))
                .withRequestBody(containing("10"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("")));

        given()
                .port(port)
                .body(c)
                .when().post("/call").then()
                .statusCode(200);
        wiremock.verifyThat(WireMock.postRequestedFor(urlEqualTo("/call")));
    }
}
