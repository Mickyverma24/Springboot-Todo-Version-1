<%@ include file = "common/header.jspf" %>
<%@ include file = "common/navigation.jspf" %>
       <div class = "container">
        <h1>Here is Your Todo</h1>
        <table class = "table">
            <thead>
                <tr>

                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Is Done?</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                   <c:forEach items="${todos}" var="todo">
                                   <tr>

                                       <td>${todo.goal}</td>
                                       <td>${todo.now}</td>
                                       <td>${todo.done}</td>
                                       <td><a href="update-todo?id=${todo.id}"  class="btn btn-primary">Update</a></td>
                                       <td><a href="delete-todo?id=${todo.id}"  class="btn btn-warning">Delete</a></td>
                                   </tr>
                   </c:forEach>
            </tbody>
        </table>
        <a href="add-todo" class="btn btn-success">Add More</a>
<%@ include file = "common/footer.jspf" %>