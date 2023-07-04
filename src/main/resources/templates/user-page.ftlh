<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Table</title>
    <!-- Add the Bootstrap stylesheet -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            padding: 20px;
        }
        .table-container {
            max-width: 600px;
            margin: 0 auto;
        }

        .btn-custom {
            background-color: #007bff;
            color: #fff;
        }
    </style>
</head>
<body>
<div class="table-container">
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Field</th>
            <th scope="col">Value</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">Name</th>
            <td>${user.name}</td>
            <td>
                <form method="get" action="/user/changeName">
                    <button class="btn btn-custom" type="submit">Change</button>
                </form>
            </td>
        </tr>
        <tr>
            <th scope="row">Surname</th>
            <td>${user.surname}</td>
            <td>
                <form method="get" action="/user/changeSurname">
                    <button class="btn btn-custom" type="submit">Change</button>
                </form>
            </td>
        </tr>
        <tr>
            <th scope="row">Email</th>
            <td>${user.email}</td>
            <td>
                <form method="get" action="/user/changeEmail">
                    <button class="btn btn-custom" type="submit">Change</button>
                </form>
            </td>
        </tr>
        <tr>
            <th scope="row">Password</th>
            <td>********</td>
            <td>
                <form method="get" action="/user/changePassword">
                    <button class="btn btn-custom" type="submit">Change</button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
    <br>
    <form method="get" action="/logout/">
        <button class="btn btn-custom" type="submit">logout</button>
    </form>
</div>
<!-- Add the Bootstrap JavaScript library -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>