<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Table</title>
    <!-- Add the Bootstrap stylesheet -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>

<body>
<div>
    <form action="/user/changePassword" method="post" >
        <input type="password" placeholder="Current Password" name="oldPassword">
        <input type="password" placeholder="New Password" name="newPassword">
        <input type="submit" value="enter">
    </form>
    <#if error?has_content>
        <ul class="text-danger">
            <li>${error.defaultMessage}</li>
        </ul>
    </#if>
    <br>
    <br>
    <form method="get" action="/user/">
        <button class="btn btn-primary" type="submit">back</button>
    </form>
</div>
</body>