package com.senpure.chat.data;

import com.senpure.base.AppEvn;
import com.senpure.base.result.Result;
import com.senpure.base.result.ResultHelper;

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
        ResultHelper resultHelper = new ResultHelper();

        resultHelper.onApplicationEvent(null);
        System.out.println(ResultHelper.getKey(1));
    }
}
