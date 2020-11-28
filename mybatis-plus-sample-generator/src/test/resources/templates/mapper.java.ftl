package ${package.Mapper};

import ${package.Entity}.${table.entityName};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author ${author}
 * @date   ${date}
 */
public interface ${table.mapperName} extends BaseMapper<${table.entityName}> {

      // 自定义自增主键插入
      public int insertOne(${table.entityName} entity);
}
