package com.michaelmarcal.commons.storm;

import com.michaelmarcal.commons.storm.data.isd.IsdBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TestHelpers {

    public String getResourceFilePath(String resourceName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        return file.getAbsolutePath();
    }

    public List<IsdBean> getTestIsdList( ) throws FileNotFoundException {
        return new CsvToBeanBuilder<IsdBean>(new FileReader( this.getResourceFilePath("isdSample.csv") ))
                .withType(IsdBean.class).build().parse();
    }

    public IsdBean getTestIsdBean() throws FileNotFoundException{
        return this.getTestIsdList().get(1);
    }
}
