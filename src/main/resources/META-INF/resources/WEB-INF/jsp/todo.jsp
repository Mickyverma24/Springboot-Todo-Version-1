    <%@ include file = "common/header.jspf" %>
    <%@ include file = "common/navigation.jspf" %>
    <div class="container">
        <h1>Here Add your new todo here</h1>
        <form:form method="post" modelAttribute="todo">
        <fieldset class = "mb-3">
                <form:label path = "goal">Goal</form:label>
                <form:input type = "text" path = "goal" required = "required"/>
                <form:errors path = "goal" cssClass ="text-warning"/>
        </fieldset>
        <fieldset class = "mb-3">
                        <form:label path = "now">Date</form:label>
                        <form:input type = "text" path = "now" required = "required"/>
                        <form:errors path = "now" cssClass ="text-warning"/>
        </fieldset>
                <form:input type = "hidden" path = "id"/>
                <form:input type = "hidden" path = "done"/>
                <input type = "submit" class = "btn btn-success"/>
        </form:form>
    <%@ include file = "common/footer.jspf" %>
    <script type ="text/javascript">
        $('#now').datepicker({format:'yyyy-mm-dd'});
    </script>

