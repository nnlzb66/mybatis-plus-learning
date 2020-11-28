package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

import java.util.List;

/**
* ${table.comment!} 服务类
*
* @author ${author}
* @date   ${date}
*/
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
    * ${table.comment!}查询列表
    * @param param 根据需要进行传值
    * @return
    */
    List<${entity}> list(${entity} param) throws Exception;

    /**
    * ${table.comment!}详情
    * @param id
    * @return
    */
    ${entity} info(Long id) throws Exception;

    /**
    * ${table.comment!}新增
    * @param param 根据需要进行传值
    * @return
    */
    void add(${entity} param) throws Exception;

    /**
    * ${table.comment!}修改
    * @param param 根据需要进行传值
    * @return
    */
    void modify(${entity} param) throws Exception;

    /**
    * ${table.comment!}删除(单个条目)
    * @param id
    * @return
    */
    void remove(Long id) throws Exception;

    /**
    * 删除(多个条目)
    * @param ids
    * @return
    */
    void removes(List<Long> ids) throws Exception;
}