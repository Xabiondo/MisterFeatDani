<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro</title>

<link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">

    <style>
        body {
            margin: 0;
            font-family: 'Press Start 2P', cursive;
            background-color: #101820;
            color: #ffffff;
            padding-bottom: 50px;
        }

        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #000;
            padding: 15px 25px;
            border-bottom: 3px solid #FFD700;
        }

        .navbar a {
            text-decoration: none;
            color: #FFD700;
            margin: 0 10px;
            font-size: 10px;
        }

        .main {
            padding: 30px 20px;
            text-align: center;
        }

        h2 {
            color: #FFD700;
            margin-bottom: 30px;
        }

        .form-container {
            background-color: #1e1e2e;
            border: 2px solid #FFD700;
            border-radius: 10px;
            padding: 30px;
            width: 90%;
            max-width: 400px;
            margin: 0 auto;
            box-shadow: 0 0 10px #FFD700aa;
        }

        label {
            display: block;
            margin-top: 15px;
            font-size: 10px;
        }

        input[type="text"],
        input[type="password"] {
            width: 90%;
            padding: 8px;
            margin-top: 5px;
            border-radius: 8px;
            border: none;
            font-family: 'Press Start 2P', cursive;
            font-size: 10px;
        }

        button {
            margin-top: 20px;
            background-color: #FFD700;
            color: #000;
            border: none;
            padding: 10px 20px;
            border-radius: 8px;
            font-size: 10px;
            cursor: pointer;
            font-family: 'Press Start 2P', cursive;
            transition: all 0.3s ease;
        }

        button:hover {
            background-color: #000;
            color: #FFD700;
            box-shadow: 0 0 10px #00d9ff;
        }

        .register-link {
            margin-top: 20px;
            font-size: 10px;
        }

        .register-link a {
            color: #FFD700;
            text-decoration: underline;
        }
    </style>
</head>
<body>

<!-- Navbar igual al de login -->
<div class="navbar">
    <div><strong style="color: #FFD700;">FantasyFútbol</strong></div>
    <div>
        <a href="/">Inicio</a>
        <a href="/register">Registrarse</a>
    </div>
</div>

<!-- Contenido principal -->
<div class="main">
    <h2>Registro</h2>
    <div class="form-container">
        <p>Por favor, completa el siguiente formulario para crear una cuenta</p>
        <form action="/register" method="POST">
            <label for="usuario">Nombre de usuario</label>
            <input type="text" id="usuario" name="usuario" required>

            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Registrar</button>
        </form>

        <div class="register-link">
            ¿Ya tienes una cuenta? <a href="/">Iniciar sesión</a>
        </div>
    </div>
</div>

</body>
</html>