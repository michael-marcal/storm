package com.michaelmarcal.commons.storm.data.isd;

import com.michaelmarcal.commons.storm.data.CsvUtils;
import lombok.experimental.UtilityClass;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class IsdUtils {

    public static List<IsdBean> getIsdData( String filePath ) throws FileNotFoundException{
        List<IsdBean> fileData = CsvUtils.readIsd(filePath);

        /**
         *  Per ISD Documentation, source 7 is "ASOS/AWOS observation merged with USAF SURFACE HOURLY observation".
         *  Eye test shows this being the most "complete."
         **/
        return filterIsdDataForSource("7", fileData);

    }

    private static List<IsdBean> filterIsdDataForSource( String source, List<IsdBean> data ) {
        return data.stream().filter(d-> Objects.equals(d.getDataSourceFlag(), source)).toList();
    }
}
