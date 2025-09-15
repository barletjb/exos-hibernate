<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<HTML>
<head>
    <link rel="stylesheet" href="style.css"
</head>

<BODY>

<div class="row">
    <div class ="column">
    Liste Hommes: <BR/>
    <TABLE>
    <c:forEach var = "joueur" items="${listeHommes}">
    		<TR>
            	<TD>${joueur.prenom}</TD><TD>${joueur.nom}</TD>
    		</TR>
          	</c:forEach>
    </TABLE>
    Liste Femmes: <BR/>
        <TABLE>
        <c:forEach var = "joueur" items="${listefemmes}">
        		<TR>
                	<TD>${joueur.prenom}</TD><TD>${joueur.nom}</TD>
        		</TR>
              	</c:forEach>
        </TABLE>
    </div>
</div>


<script>(function(){function c(){var b=a.contentDocument||a.contentWindow.document;if(b){var d=b.createElement('script');d.innerHTML="window.__CF$cv$params={r:'97f05ac2ef48bb1e',t:'MTc1Nzg1NzgzOS4wMDAwMDA='};var a=document.createElement('script');a.nonce='';a.src='/cdn-cgi/challenge-platform/scripts/jsd/main.js';document.getElementsByTagName('head')[0].appendChild(a);";b.getElementsByTagName('head')[0].appendChild(d)}}if(document.body){var a=document.createElement('iframe');a.height=1;a.width=1;a.style.position='absolute';a.style.top=0;a.style.left=0;a.style.border='none';a.style.visibility='hidden';document.body.appendChild(a);if('loading'!==document.readyState)c();else if(window.addEventListener)document.addEventListener('DOMContentLoaded',c);else{var e=document.onreadystatechange||function(){};document.onreadystatechange=function(b){e(b);'loading'!==document.readyState&&(document.onreadystatechange=e,c())}}}})();</script></BODY>

</HTML>
