package ${package.Entity};

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
${table.comment}
@author ${author}
@date   ${cfg.createTime}
*/
@Data
public class ${entity} {

    <#list table.commonFields as value>
      <#if value.comment!="">
      // ${value.comment}
      </#if>
      private  ${value.propertyType}  ${value.capitalName};
    </#list>
    <#list table.fields as value>
      <#if value.comment!="">
      // ${value.comment}
      </#if>
      private  ${value.propertyType}  ${value.capitalName};
    </#list>

}