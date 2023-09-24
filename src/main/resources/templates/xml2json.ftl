<#assign data = xml['child::node()']>
{
[
<#assign x = 0>
<#list xml.["data/employee"] as emp>
    <#if x gte 1 >
    "employee": {
    "id": ${emp.id},
    "name": "${emp.name}",
    "location": "${emp.location}"
    }
    </#if>
    <#assign x++>
</#list>
]
}