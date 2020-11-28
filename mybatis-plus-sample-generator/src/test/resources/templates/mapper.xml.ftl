<#function transType javaType>
   <#local result = "">
   <#switch javaType>
   <#case "BigDecimal"><#local result = "java.math.BigDecimal"><#break>
   <#case "Date"><#local result = "java.util.Date"><#break>
   <#default> <#local result = javaType>
   </#switch>
   <#return result>
</#function>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.entityName}Mapper">

    <resultMap  id="BaseResultMap" type="${package.Entity}.${table.entityName}">
    <#list table.commonFields as value>
       <result column="${value.propertyName}" property="${value.capitalName}"  jdbcType="${value.columnType}" javaType="${transType(value.propertyType)}"></result>
    </#list>
    <#list table.fields as value>
       <result column="${value.propertyName}" property="${value.capitalName}"  jdbcType="${value.columnType}" javaType="${transType(value.propertyType)}"></result>
    </#list>
    </resultMap>


    <!-- 自定义自增主键插入 -->
    <insert id="insertOne" parameterType="${package.Entity}.${table.entityName}">
        <selectKey resultType="Long" keyColumn="id" keyProperty="id">
            select max(id)
            from
            ${table.name}
        </selectKey>
        insert into ${table.name}
        (
     <#list table.fields as value>
      <#if value_index = table.fields?size-1>
           ${value.columnName}
         <#else>
           ${value.columnName},
      </#if>
     </#list>

        ) values
        (
    <#list table.fields as value>
     <#if value_index = table.fields?size-1>
          <#noparse>#{</#noparse>${value.capitalName}<#noparse>}</#noparse>
        <#else>
          <#noparse>#{</#noparse>${value.capitalName}<#noparse>},</#noparse>
     </#if>
    </#list>
        )
    </insert>


</mapper>