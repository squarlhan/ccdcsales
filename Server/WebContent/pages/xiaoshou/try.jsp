<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Struts 2 AJAX - Autocompleter</title>
        <s:head theme="ajax" />
    </head>
    <body>
        <h2>
            Autocompleter
        </h2>
        <s:form action="#">
            <s:textfield label="abc" name="abc" />
            <tr>
                <td class="tdLabel">
                    <label class="label">
                        No AJAX Autocompleter:
                    </label>
                </td>
                <td>
                    <s:autocompleter theme="simple" name="user"
                        list="allcustomers" />
                </td>
            </tr>
            <tr>
                <td class="tdLabel">
                    <label class="label">
                        AJAX Autocompleter:
                    </label>
                </td>
                <td>
                    <s:url id="dataUrl" value="/getcustomers.action" />
                    <s:autocompleter theme="ajax" name="start" href="%{dataUrl}"
                        loadOnTextChange="true" loadMinimumCount="1" indicator="indicator"
                        autoComplete="false" showDownArrow="false" />
                    <img id="indicator" 
                    src="${pageContext.request.contextPath}/images/indicator.gif" 
                    alt="Loading" style="display:none" />
                </td>
            </tr>
        </s:form>
    </body>
</html>