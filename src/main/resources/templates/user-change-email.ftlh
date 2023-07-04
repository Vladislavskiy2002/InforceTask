<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Table</title>
    <!-- Add the Bootstrap stylesheet -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>

<body>
<div>
    <form method="post" action="/user/changeEmail">
        <input placeholder="New email" name="newEmail"  value="${user.email}">
        <input type="submit" value="enter">
    </form>
    <tr>OR</tr>
    <form method="get" action="/signUp">
        <button class="btn btn-primary" type="submit">SignUp</button>
    </form>
</div>
</body>