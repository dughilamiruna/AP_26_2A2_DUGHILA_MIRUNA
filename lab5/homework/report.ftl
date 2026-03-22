<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Report - ${catalogName}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; }
        h1 { color: #2c3e50; }
        .item { border: 1px solid #ddd; padding: 15px; margin-bottom: 15px; border-radius: 5px; }
        .item-title { font-size: 18px; font-weight: bold; color: #34495e; }
        .tags { font-size: 14px; color: #7f8c8d; }
    </style>
</head>
<body>
    <h1>Catalog Report: ${catalogName}</h1>

    <hr>

    <#list items as item>
        <div class="item">
            <div class="item-title">${item.title} (ID: ${item.id})</div>
            <div><strong>Location:</strong> <a href="${item.location}" target="_blank">${item.location}</a></div>

            <div class="tags">
                <strong>Tags:</strong>
                <#if item.tags?has_content>
                    <ul>
                    <#list item.tags as key, value>
                        <li>${key}: ${value}</li>
                    </#list>
                    </ul>
                <#else>
                    Nu există tag-uri.
                </#if>
            </div>
        </div>
    </#list>

</body>
</html>