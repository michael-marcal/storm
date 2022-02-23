package com.michaelmarcal.commons.storm.data;

import com.michaelmarcal.commons.storm.data.isd.IsdBean;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvUtilsTest {

    @Test
    public void test_readIsd() {
        try{
            List<IsdBean> data = CsvUtils.readIsd(getResourceFilePath("isdSample.csv"));
            assertEquals(10, data.size());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private String getResourceFilePath(String resourceName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        return file.getAbsolutePath();
    }
}
