package com.senpure.chat.data;

import com.senpure.base.AppEvn;
import com.senpure.base.result.Result;
import com.senpure.base.result.ResultHelper;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * ResultTest
 *
 * @author senpure
 * @time 2019-01-29 10:05:42
 */
public class ResultTest {

    public static void main(String[] args) {

        AppEvn.markClassRootPath();
        ResultHelper.results.add(new Result());
        ResultHelper.syncResults();

        System.out.println(ResultHelper.getKey(1));

        String str = MessageFormat.format(ResultHelper.getMessage(404, Locale.CHINA), "/snowflake/dispatch2");
        System.out.println(str);

    }
}
