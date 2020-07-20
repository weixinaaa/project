package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.CarRental;
import com.huiyou.msfw.model.CarRentalExample;
import java.util.List;

import com.huiyou.msfw.model.Role;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CarRentalMapper extends BaseMapper<CarRental, CarRentalExample, Long> {
    /**
     * 查询
     * @param实体类CarRental封装到map里做参数类型
     * @return CarRental集合
     */
    List<CarRental> selCarRental(CarRental carRental);

    /**
     * 逻辑删除(可单，可多)
     * @param id
     */
    void delCarRental(Integer[] id);


    /**
     * 查询车辆
     * @param id 车辆id
     * @return
     */
    @Select("SELECT * FROM car_rental WHERE id = #{id}")
    List<CarRental> selCarRentalById(@Param("id") Long id);

    Integer updateCarRental(CarRental carRental);
}