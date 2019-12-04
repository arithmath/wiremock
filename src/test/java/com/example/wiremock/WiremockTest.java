package com.example.wiremock;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8888, stubs="classpath:/stubs/*")
public class WiremockTest {
//    @Rule
//    public WireMockRule wm = new WireMockRule(options()
//            .extensions(new ResponseTemplateTransformer(false))
//    );

    @Test
    void test_hoge() throws InterruptedException {
        System.out.println("start");
        System.setProperty("wiremock.value", "hogehoge");
        Thread.sleep(10000);
        System.out.println("change system properties");
        System.setProperty("wiremock.value", "fugafuga");
        Thread.sleep(10000);
    }

    @Test
    void test_hoge2() throws InterruptedException {
        System.out.println("start");
        Thread.sleep(10000);
    }

    public static class ExampleTransformer extends ResponseDefinitionTransformer {

        @Override
        public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource files, Parameters parameters) {
            return new ResponseDefinitionBuilder()
                    .withHeader("MyHeader", "Transformed")
                    .withStatus(200)
                    .withBody("Transformed body")
                    .build();
        }

        @Override
        public String getName() {
            return "example";
        }
    }
}
