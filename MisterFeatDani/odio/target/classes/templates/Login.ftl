<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">

</head>
<body>

<!-- Contenedor de login -->
<div class="login-container">
    <h2>Bienvenido</h2>
    <p>Por favor, inicia sesión para continuar</p>
    <form action="/interfaz" method="POST">
        <div class="input-group">
            <label for="usuario">Usuario</label>
            <input type="text" id="usuario" name="usuario" required>
        </div>

        <div class="input-group">
            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="options">
            <label><input type="checkbox" name="remember"> Recuérdame</label>
            <a href="#">¿Olvidaste tu contraseña?</a>
        </div>

        <button type="submit">Ingresar</button>
    </form>

    <p>¿No tienes cuenta todavía? <a href="registro">Registrarse</a></p>
</div>
</body>
</html>
