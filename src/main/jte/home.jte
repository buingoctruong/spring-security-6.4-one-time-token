<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background: linear-gradient(135deg, #667eea, #764ba2);
            margin: 0;
            color: white;
            text-align: center;
        }
        .container {
            background: rgba(255, 255, 255, 0.2);
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            backdrop-filter: blur(10px);
        }
        h1 {
            font-size: 2.5em;
        }
        p {
            font-size: 1.2em;
        }
        .button {
            margin-top: 20px;
            padding: 12px 25px;
            background-color: #ff6b6b;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            text-decoration: none;
            font-size: 1em;
            transition: background 0.3s ease;
        }
        .button:hover {
            background-color: #ff4757;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome, My Friend!</h1>
    <p>You have successfully logged in.</p>
    <a href="/logout" class="button">Logout</a>
</div>
</body>
</html>
