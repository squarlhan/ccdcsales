<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>

<link href="/Server/css/css.css" rel="stylesheet" type="text/css" />
<link href="/Server/css/main.css" rel="stylesheet" type="text/css" />
<p>
  <table width="150" border=0 cellpadding=0 cellspacing=0>
    <tr >
      <th class="panelTitle" bgcolor="#CCCCCC" height="25">
        <font color=ffffff><decorator:title default="侧边栏" /></font>
      </th>
    </tr>
    <tr>
      <td class="panelBody">
        <decorator:body/>
      </td>
    </tr>
  </table>
</p>