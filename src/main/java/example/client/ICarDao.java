package example.client;

import com.reforms.ann.TargetQuery;

public interface ICarDao {

    @TargetQuery("SELECT number, color " +
                 "FROM cars " +
                 "WHERE id = :id")
    Car loadCar(int id);
}
