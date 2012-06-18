package com.mtt.test.steps.user;

import cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "classpath:cucumber/features/user/",
                  glue = {"com.mtt.test.steps.common"})
public class CreateUserTest {
}
