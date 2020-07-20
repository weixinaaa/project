package com.huiyou.msfw.mapper;

import com.huiyou.msfw.model.Partner;
import com.huiyou.msfw.model.PartnerExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

public interface PartnerMapper extends BaseMapper<Partner, PartnerExample, Long> {
    @Delete("DELETE FROM partner WHERE id = #{id}")
    int delPartner(@Param("id")Integer id);

    List<Partner> getPartner(Partner partner);

}