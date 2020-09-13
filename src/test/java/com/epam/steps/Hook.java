package com.epam.steps;

import com.epam.utils.Constants;
import com.epam.utils.providers.DriverProvider;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {
    @Before
    public void setUp() {
        DriverProvider.getInstance().get(Constants.BASE_URL);
    }

    @After
    public void tearDown(){
        DriverProvider.quit();
    }
}
