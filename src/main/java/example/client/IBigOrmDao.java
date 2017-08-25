package example.client;

import com.reforms.ann.TargetFilter;
import com.reforms.ann.TargetQuery;

public interface IBigOrmDao {

    @TargetQuery("SELECT field_name1, field_name2, field_name3, field_name4, field_name5, " +
                 "field_name6, field_name7, field_name8, field_name9, field_name10 " +
                 "FROM big_orms ")
    BigOrm load(@TargetFilter(columnFilter = true) int[] keepFields);
}
