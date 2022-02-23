package com.michaelmarcal.commons.storm.data;

import com.michaelmarcal.commons.storm.data.isd.IsdBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.experimental.UtilityClass;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@UtilityClass
public class CsvUtils {

    public static List<IsdBean> readIsd(String filePath ) throws FileNotFoundException {
        return new CsvToBeanBuilder<IsdBean>(new FileReader(filePath))
                .withType(IsdBean.class).build().parse();
    }
}
