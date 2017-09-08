package com.switchvov.test;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by jap on 2017/7/7.
 */
public class DateTest3 {
    @Test
    public void testDate1() throws ParseException {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat1.parse("20170501");
        while (true) {
            if (startDate.after(new Date())) {
                break;
            }
            Date addDays = DateUtils.addDays(startDate,1);
            // genCandidateSource(dateFormat1, dateFormat2, startDate, addDays);
            genCandidateCount(dateFormat1, dateFormat2, startDate, addDays);
            startDate = addDays;
        }
    }

    private void genCandidateCount(SimpleDateFormat dateFormat1, SimpleDateFormat dateFormat2, Date startDate, Date addDays) {
        StringBuilder builder = new StringBuilder();
        builder.append("insert into table teststg_baza_candidate_count_log  PARTITION (dt='");
        builder.append(dateFormat1.format(startDate));
        builder.append("')\n");
        builder.append("select  UUID() id,\n");
        builder.append("owner_id as open_id ,\n");
        builder.append("count(id) count,\n");
        builder.append("'");
        builder.append(dateFormat2.format(startDate));
        builder.append(" 00:00:00' created,\n");
        builder.append("'00000000-0000-0000-0000-000000000002' creator \n");
        builder.append("union_id \n");
        builder.append("from tbd_resume\n");
        builder.append("where  source_created < '");
        builder.append(dateFormat2.format(addDays));
        builder.append(" 00:00:00'\n");
        builder.append("group by owner_id, union_id;\n");
        System.out.println(builder.toString());
    }

    private void genCandidateSource(SimpleDateFormat dateFormat1, SimpleDateFormat dateFormat2, Date startDate, Date addDays) {
        StringBuilder builder = new StringBuilder();
        builder.append("insert into table baza_candidate_source_count_log  PARTITION (dt='");
        builder.append(dateFormat1.format(startDate));
        builder.append("')\n");
        builder.append("select  concat(owner_id,source_from,'");
        builder.append(dateFormat2.format(startDate));
        builder.append(" 00:00:00')  id,\n");
        builder.append("owner_id ,\n");
        builder.append("source_from source,\n");
        builder.append("count(id) count,\n");
        builder.append("'");
        builder.append(dateFormat2.format(startDate));
        builder.append(" 00:00:00' created,\n");
        builder.append("'00000000-0000-0000-0000-000000000002' creator \n");
        builder.append("from tbd_resume\n");
        builder.append("where source_created >= '");
        builder.append(dateFormat2.format(startDate));
        builder.append(" 00:00:00' and source_created < '");
        builder.append(dateFormat2.format(addDays));
        builder.append(" 00:00:00'\n");
        builder.append("group by owner_id,source_from;\n");
        System.out.println(builder.toString());
    }
}
