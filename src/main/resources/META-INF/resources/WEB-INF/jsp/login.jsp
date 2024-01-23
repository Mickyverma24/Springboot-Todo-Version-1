<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hello JSP</title>
</head>
<body>
   <h1>Hello Welcome to login page!</h1>
   <pre>${errorMessage}</pre>
      <form action="/login" method="post">
          <label for="name">Name:</label>
          <input type="text" id="name" name="name" required>

          <label for="password">Password:</label>
          <input type="password" id="password" name="password" required>

          <input type="submit" value="Submit">
      </form>

</body>
</html>
