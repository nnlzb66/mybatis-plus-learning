using System;

<#function transType javaType>
   <#local result = "">
   <#switch javaType>
   <#case "Long"><#local result = "long"><#break>
   <#case "Integer"><#local result = "int"><#break>
   <#case "Boolean"><#local result = "bool"><#break>
   <#case "BigDecimal"><#local result = "decimal"><#break>
   <#case "String"><#local result = "string"><#break>
   <#case "Float"><#local result = "float"><#break>
   <#case "Double"><#local result = "double"><#break>
   <#case "Short"><#local result = "short"><#break>
   <#case "Byte"><#local result = "byte"><#break>
   <#default> <#local result = javaType>
   </#switch>

   <#return result>
</#function>

namespace ShgFICCDataAnaClient.Model.DailyReport
{


    /// <summary>
    /// ${table.comment}
    /// </summary>
    public class ${entity}Model : ShgModelBase
    {


        <#list table.commonFields as value>
          <#if value.comment!="">
        // ${value.comment}
          </#if>
        private  ${transType(value.propertyType)}  _${value.columnName};
        </#list>
        <#list table.fields as value>
          <#if value.comment!="">
        // ${value.comment}
          </#if>
        private  ${transType(value.propertyType)}  _${value.columnName};
        </#list>


        <#list table.commonFields as value>
        public  ${transType(value.propertyType)}  ${value.capitalName}  { get { return _${value.columnName}; } set { _${value.columnName} = value; } };
        </#list>
        <#list table.fields as value>
        public  ${transType(value.propertyType)}  ${value.capitalName}  { get { return _${value.columnName}; } set { _${value.columnName} = value; } }
        </#list>
    }

}
