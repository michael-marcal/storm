package com.michaelmarcal.commons.storm.data.isd;

import com.michaelmarcal.commons.storm.TestHelpers;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsdUtilsTest {

    @Test
    public void test_getIsdData() throws FileNotFoundException {
        TestHelpers testHelpers = new TestHelpers();
        List<IsdBean> data;

        data = IsdUtils.getIsdData(testHelpers.getResourceFilePath("isdSample.csv"));

        assertEquals(7, data.size());
        assertEquals(1, data.stream().map(IsdBean::getDataSourceFlag).collect(Collectors.toSet()).size());
    }
}
