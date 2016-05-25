<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<footer class="container-fluid">
    <p><span class="fa fa-copyright"></span>2015 <a id="author-link"
                                                    href="https://github.com/Bernardo-MG">Bernardo
        Mart�nez Garrido</a> | Code released under the <a id="license-link"
                                                          href="http://www.opensource.org/licenses/mit-license.php">MIT
        License</a></p>
    <p>v1.0.0 | Check the code at <a href="#" title="GitHub" aria-label="GitHub"><span
            class="fa fa-github" aria-hidden="true"></span></a></p>
</footer>

<spring:url value="/webjars/jquery/2.2.4/jquery.min.js" var="jQueryUrl"/>
<script src="${ jQueryUrl }"></script>
<spring:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js" var="bootstrapUrl"/>
<script src="${ bootstrapUrl }"></script>
<spring:url value="/resources/js/chevron-toggle.js" var="chevronUrl"/>
<script src="${ chevronUrl }"></script>
<spring:url value="/resources/js/smooth-scroll.js" var="smoothScrollUrl"/>
<script src="${ smoothScrollUrl }"></script>
