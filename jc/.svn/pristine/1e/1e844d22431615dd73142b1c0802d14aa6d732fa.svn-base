<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <link rel="stylesheet" href="${basePath}/css/jorgchart/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/jorgchart/css/jquery.jOrgChart.css"/>
    <link rel="stylesheet" href="${basePath}/css/jorgchart/css/custom.css"/>
    <link href="${basePath}/css/jorgchart/css/prettify.css" type="text/css" rel="stylesheet" />

    <script type="text/javascript" src="${basePath}/js/jorgchart/prettify.js"></script>
    
    <!-- jQuery includes -->
    <script type="text/javascript" src="${basePath}/js/jorgchart/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jorgchart/jquery-ui.min.js"></script>
    
    <script src="${basePath}/js/jorgchart/jquery.jOrgChart.js"></script>

    <script>
    jQuery(document).ready(function() {
        $("#org").jOrgChart({
            chartElement : '#chart',
            dragAndDrop  : true
        });
    });
    </script>
  </head>

  <body>

    
    <ul id="org" style="display:none">${orgChartStr}</ul>
    <div id="chart" class="orgChart"></div>
</body>
</html>